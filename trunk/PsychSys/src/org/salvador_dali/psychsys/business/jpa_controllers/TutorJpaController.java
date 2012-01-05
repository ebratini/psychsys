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
import org.salvador_dali.psychsys.business.validators.exceptions.IllegalOrphanException;
import org.salvador_dali.psychsys.business.validators.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.entities.Tutor;
import org.salvador_dali.psychsys.model.entities.TutorEstudiante;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class TutorJpaController implements Serializable {

    public TutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutor tutor) {
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

    public void edit(Tutor tutor) throws IllegalOrphanException, NonexistentEntityException, Exception {
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
                if (findTutor(id) == null) {
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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

    public List<Tutor> findTutorEntities() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor findTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
