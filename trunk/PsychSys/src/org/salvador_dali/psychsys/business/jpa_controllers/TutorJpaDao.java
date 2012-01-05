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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.IllegalOrphanException;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.TutorDao;
import org.salvador_dali.psychsys.model.entities.Tutor;
import org.salvador_dali.psychsys.model.entities.TutorEstudiante;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class TutorJpaDao extends JpaDao implements TutorDao {

    public TutorJpaDao() {
        super(Tutor.class);
    }

    public TutorJpaDao(Map props) {
        super(Tutor.class, props);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Tutor) {
            Tutor tutor = (Tutor) entity;
            if (tutor.getTutorEstudianteCollection() == null) {
                tutor.setTutorEstudianteCollection(new ArrayList<TutorEstudiante>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Collection<TutorEstudiante> attachedTutorEstudianteCollection = new ArrayList<TutorEstudiante>();
                for (TutorEstudiante tutorEstudianteCollectionTutorEstudianteToAttach : tutor.getTutorEstudianteCollection()) {
                    tutorEstudianteCollectionTutorEstudianteToAttach = em.getReference(tutorEstudianteCollectionTutorEstudianteToAttach.getClass(), tutorEstudianteCollectionTutorEstudianteToAttach.getTutorEstudiantePK());
                    attachedTutorEstudianteCollection.add(tutorEstudianteCollectionTutorEstudianteToAttach);
                }
                tutor.setTutorEstudianteCollection(attachedTutorEstudianteCollection);
                em.persist(tutor);
                for (TutorEstudiante tutorEstudianteCollectionTutorEstudiante : tutor.getTutorEstudianteCollection()) {
                    Tutor oldTutorOfTutorEstudianteCollectionTutorEstudiante = tutorEstudianteCollectionTutorEstudiante.getTutor();
                    tutorEstudianteCollectionTutorEstudiante.setTutor(tutor);
                    tutorEstudianteCollectionTutorEstudiante = em.merge(tutorEstudianteCollectionTutorEstudiante);
                    if (oldTutorOfTutorEstudianteCollectionTutorEstudiante != null) {
                        oldTutorOfTutorEstudianteCollectionTutorEstudiante.getTutorEstudianteCollection().remove(tutorEstudianteCollectionTutorEstudiante);
                        oldTutorOfTutorEstudianteCollectionTutorEstudiante = em.merge(oldTutorOfTutorEstudianteCollectionTutorEstudiante);
                    }
                }
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }

    public void update(Tutor tutor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getTutId());
            Collection<TutorEstudiante> tutorEstudianteCollectionOld = persistentTutor.getTutorEstudianteCollection();
            Collection<TutorEstudiante> tutorEstudianteCollectionNew = tutor.getTutorEstudianteCollection();
            List<String> illegalOrphanMessages = null;
            for (TutorEstudiante tutorEstudianteCollectionOldTutorEstudiante : tutorEstudianteCollectionOld) {
                if (!tutorEstudianteCollectionNew.contains(tutorEstudianteCollectionOldTutorEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TutorEstudiante " + tutorEstudianteCollectionOldTutorEstudiante + " since its tutor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TutorEstudiante> attachedTutorEstudianteCollectionNew = new ArrayList<TutorEstudiante>();
            for (TutorEstudiante tutorEstudianteCollectionNewTutorEstudianteToAttach : tutorEstudianteCollectionNew) {
                tutorEstudianteCollectionNewTutorEstudianteToAttach = em.getReference(tutorEstudianteCollectionNewTutorEstudianteToAttach.getClass(), tutorEstudianteCollectionNewTutorEstudianteToAttach.getTutorEstudiantePK());
                attachedTutorEstudianteCollectionNew.add(tutorEstudianteCollectionNewTutorEstudianteToAttach);
            }
            tutorEstudianteCollectionNew = attachedTutorEstudianteCollectionNew;
            tutor.setTutorEstudianteCollection(tutorEstudianteCollectionNew);
            tutor = em.merge(tutor);
            for (TutorEstudiante tutorEstudianteCollectionNewTutorEstudiante : tutorEstudianteCollectionNew) {
                if (!tutorEstudianteCollectionOld.contains(tutorEstudianteCollectionNewTutorEstudiante)) {
                    Tutor oldTutorOfTutorEstudianteCollectionNewTutorEstudiante = tutorEstudianteCollectionNewTutorEstudiante.getTutor();
                    tutorEstudianteCollectionNewTutorEstudiante.setTutor(tutor);
                    tutorEstudianteCollectionNewTutorEstudiante = em.merge(tutorEstudianteCollectionNewTutorEstudiante);
                    if (oldTutorOfTutorEstudianteCollectionNewTutorEstudiante != null && !oldTutorOfTutorEstudianteCollectionNewTutorEstudiante.equals(tutor)) {
                        oldTutorOfTutorEstudianteCollectionNewTutorEstudiante.getTutorEstudianteCollection().remove(tutorEstudianteCollectionNewTutorEstudiante);
                        oldTutorOfTutorEstudianteCollectionNewTutorEstudiante = em.merge(oldTutorOfTutorEstudianteCollectionNewTutorEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutor.getTutId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
        if (entity instanceof Tutor) {
            Tutor tutor = (Tutor) entity;
            try {
                update(tutor);
                return (E) tutor;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(TutorJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(TutorJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(TutorJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return null;
        }
    }
    
    public void remove(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getTutId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TutorEstudiante> tutorEstudianteCollectionOrphanCheck = tutor.getTutorEstudianteCollection();
            for (TutorEstudiante tutorEstudianteCollectionOrphanCheckTutorEstudiante : tutorEstudianteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutor (" + tutor + ") cannot be destroyed since the TutorEstudiante " + tutorEstudianteCollectionOrphanCheckTutorEstudiante + " in its tutorEstudianteCollection field has a non-nullable tutor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Tutor) {
            try {
                remove(((Tutor) entity).getTutId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(TutorJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(TutorJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Tutor getTutorByDNI(String dni) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Tutor.findByTutDni");
            q.setParameter("tutDni", dni);
            return (Tutor) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Tutor> getTutoresByPrimerApellido(String primerApellido) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Tutor.findByTutPrimerApellido");
            q.setParameter("tutPrimerApellido", primerApellido);
            return (List<Tutor>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Tutor> getTutoresByPrimerNombre(String primerNombre) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Tutor.findByTutPrimerNombre");
            q.setParameter("tutPrimerNombre", primerNombre);
            return (List<Tutor>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Tutor> getTutoresByStatus(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Tutor.findByTutStatus");
            q.setParameter("tutStatus", status);
            return (List<Tutor>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
