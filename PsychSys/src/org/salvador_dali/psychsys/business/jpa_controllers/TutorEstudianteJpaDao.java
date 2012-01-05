/*
 * The MIT License
 *
 * Copyright 2011 Edwin Bratini <edwin.bratini@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.salvador_dali.psychsys.business.jpa_controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.business.exceptions.PreexistingEntityException;
import org.salvador_dali.psychsys.model.TutorEstudianteDao;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.Tutor;
import org.salvador_dali.psychsys.model.entities.TutorEstudiante;
import org.salvador_dali.psychsys.model.entities.TutorEstudiantePK;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class TutorEstudianteJpaDao extends JpaDao implements TutorEstudianteDao {

    public TutorEstudianteJpaDao() {
        super(TutorEstudiante.class);
    }

    public TutorEstudianteJpaDao(Map properties) {
        super(TutorEstudiante.class, properties);
    }
    
    public void persist(TutorEstudiante tutorEstudiante) throws PreexistingEntityException, Exception {
        if (tutorEstudiante.getTutorEstudiantePK() == null) {
            tutorEstudiante.setTutorEstudiantePK(new TutorEstudiantePK());
        }
        tutorEstudiante.getTutorEstudiantePK().setTutId(tutorEstudiante.getTutor().getTutId());
        tutorEstudiante.getTutorEstudiantePK().setEstId(tutorEstudiante.getEstudiante().getEstId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor tutor = tutorEstudiante.getTutor();
            if (tutor != null) {
                tutor = em.getReference(tutor.getClass(), tutor.getTutId());
                tutorEstudiante.setTutor(tutor);
            }
            Estudiante estudiante = tutorEstudiante.getEstudiante();
            if (estudiante != null) {
                estudiante = em.getReference(estudiante.getClass(), estudiante.getEstId());
                tutorEstudiante.setEstudiante(estudiante);
            }
            em.persist(tutorEstudiante);
            if (tutor != null) {
                tutor.getTutorEstudianteCollection().add(tutorEstudiante);
                tutor = em.merge(tutor);
            }
            if (estudiante != null) {
                estudiante.getTutorEstudianteCollection().add(tutorEstudiante);
                estudiante = em.merge(estudiante);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findById(tutorEstudiante.getTutorEstudiantePK()) != null) {
                throw new PreexistingEntityException("TutorEstudiante " + tutorEstudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof TutorEstudiante) {
            try {
                persist((TutorEstudiante) entity);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(TutorEstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(TutorEstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void update(TutorEstudiante tutorEstudiante) throws NonexistentEntityException, Exception {
        tutorEstudiante.getTutorEstudiantePK().setTutId(tutorEstudiante.getTutor().getTutId());
        tutorEstudiante.getTutorEstudiantePK().setEstId(tutorEstudiante.getEstudiante().getEstId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TutorEstudiante persistentTutorEstudiante = em.find(TutorEstudiante.class, tutorEstudiante.getTutorEstudiantePK());
            Tutor tutorOld = persistentTutorEstudiante.getTutor();
            Tutor tutorNew = tutorEstudiante.getTutor();
            Estudiante estudianteOld = persistentTutorEstudiante.getEstudiante();
            Estudiante estudianteNew = tutorEstudiante.getEstudiante();
            if (tutorNew != null) {
                tutorNew = em.getReference(tutorNew.getClass(), tutorNew.getTutId());
                tutorEstudiante.setTutor(tutorNew);
            }
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getEstId());
                tutorEstudiante.setEstudiante(estudianteNew);
            }
            tutorEstudiante = em.merge(tutorEstudiante);
            if (tutorOld != null && !tutorOld.equals(tutorNew)) {
                tutorOld.getTutorEstudianteCollection().remove(tutorEstudiante);
                tutorOld = em.merge(tutorOld);
            }
            if (tutorNew != null && !tutorNew.equals(tutorOld)) {
                tutorNew.getTutorEstudianteCollection().add(tutorEstudiante);
                tutorNew = em.merge(tutorNew);
            }
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                estudianteOld.getTutorEstudianteCollection().remove(tutorEstudiante);
                estudianteOld = em.merge(estudianteOld);
            }
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                estudianteNew.getTutorEstudianteCollection().add(tutorEstudiante);
                estudianteNew = em.merge(estudianteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TutorEstudiantePK id = tutorEstudiante.getTutorEstudiantePK();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The tutorEstudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> E update(E entity) {
        if (entity instanceof TutorEstudiante) {
            TutorEstudiante tutorEstudiante = (TutorEstudiante) entity;
            try {
                update(tutorEstudiante);
                return (E) tutorEstudiante;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(TutorEstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(TutorEstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return null;
        }
    }
    
    public void remove(TutorEstudiantePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TutorEstudiante tutorEstudiante;
            try {
                tutorEstudiante = em.getReference(TutorEstudiante.class, id);
                tutorEstudiante.getTutorEstudiantePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutorEstudiante with id " + id + " no longer exists.", enfe);
            }
            Tutor tutor = tutorEstudiante.getTutor();
            if (tutor != null) {
                tutor.getTutorEstudianteCollection().remove(tutorEstudiante);
                tutor = em.merge(tutor);
            }
            Estudiante estudiante = tutorEstudiante.getEstudiante();
            if (estudiante != null) {
                estudiante.getTutorEstudianteCollection().remove(tutorEstudiante);
                estudiante = em.merge(estudiante);
            }
            em.remove(tutorEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof TutorEstudiante) {
            TutorEstudiante tutorEstudiante = (TutorEstudiante) entity;
            try {
                remove(tutorEstudiante.getTutorEstudiantePK());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(TutorEstudianteJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<TutorEstudiante> getTutorEstudianteByTutId(int tutId) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("TutorEstudiante.findByTutId");
            q.setParameter("tutId", tutId);
            return (List<TutorEstudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<TutorEstudiante> getTutorEstudianteByEstId(int estId) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("TutorEstudiante.findByEstId");
            q.setParameter("estId", estId);
            return (List<TutorEstudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public TutorEstudiante getTutorEstudiante(Tutor tutor, Estudiante estudiante) {
        EntityManager entityManager = getEntityManager();
        try {
            String strQuery = "SELECT te FROM TutorEstudiante te WHERE te.tutor := tesTutor";
            strQuery += " AND te.estudiante := tesEstudiante";
            Query q = entityManager.createQuery(strQuery);
            q.setParameter("primerNombre", tutor);
            q.setParameter("primerApellido", estudiante);
            return (TutorEstudiante) q.getSingleResult();
        } finally {
            entityManager.close();
        }
    }
}
