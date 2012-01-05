/*
 * The MIT License
 *
 * Copyright 2012 Edwin Bratini <edwin.bratini@gmail.com>.
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.salvador_dali.psychsys.business.validators.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.business.validators.exceptions.PreexistingEntityException;
import org.salvador_dali.psychsys.model.entities.Tutor;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.TutorEstudiante;
import org.salvador_dali.psychsys.model.entities.TutorEstudiantePK;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class TutorEstudianteJpaController implements Serializable {

    public TutorEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TutorEstudiante tutorEstudiante) throws PreexistingEntityException, Exception {
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
            if (findTutorEstudiante(tutorEstudiante.getTutorEstudiantePK()) != null) {
                throw new PreexistingEntityException("TutorEstudiante " + tutorEstudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TutorEstudiante tutorEstudiante) throws NonexistentEntityException, Exception {
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
                if (findTutorEstudiante(id) == null) {
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

    public void destroy(TutorEstudiantePK id) throws NonexistentEntityException {
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

    public List<TutorEstudiante> findTutorEstudianteEntities() {
        return findTutorEstudianteEntities(true, -1, -1);
    }

    public List<TutorEstudiante> findTutorEstudianteEntities(int maxResults, int firstResult) {
        return findTutorEstudianteEntities(false, maxResults, firstResult);
    }

    private List<TutorEstudiante> findTutorEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TutorEstudiante.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TutorEstudiante findTutorEstudiante(TutorEstudiantePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TutorEstudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TutorEstudiante> rt = cq.from(TutorEstudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
