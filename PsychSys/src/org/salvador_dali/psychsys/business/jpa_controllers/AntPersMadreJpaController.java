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
import org.salvador_dali.psychsys.business.validators.exceptions.PreexistingEntityException;
import org.salvador_dali.psychsys.model.entities.AntPersMadre;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import java.util.ArrayList;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class AntPersMadreJpaController implements Serializable {

    public AntPersMadreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AntPersMadre antPersMadre) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        HistoriaClinica historiaClinicaOrphanCheck = antPersMadre.getHistoriaClinica();
        if (historiaClinicaOrphanCheck != null) {
            AntPersMadre oldAntPersMadreOfHistoriaClinica = historiaClinicaOrphanCheck.getAntPersMadre();
            if (oldAntPersMadreOfHistoriaClinica != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HistoriaClinica " + historiaClinicaOrphanCheck + " already has an item of type AntPersMadre whose historiaClinica column cannot be null. Please make another selection for the historiaClinica field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoriaClinica historiaClinica = antPersMadre.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica = em.getReference(historiaClinica.getClass(), historiaClinica.getHicId());
                antPersMadre.setHistoriaClinica(historiaClinica);
            }
            em.persist(antPersMadre);
            if (historiaClinica != null) {
                historiaClinica.setAntPersMadre(antPersMadre);
                historiaClinica = em.merge(historiaClinica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAntPersMadre(antPersMadre.getHicId()) != null) {
                throw new PreexistingEntityException("AntPersMadre " + antPersMadre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AntPersMadre antPersMadre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AntPersMadre persistentAntPersMadre = em.find(AntPersMadre.class, antPersMadre.getHicId());
            HistoriaClinica historiaClinicaOld = persistentAntPersMadre.getHistoriaClinica();
            HistoriaClinica historiaClinicaNew = antPersMadre.getHistoriaClinica();
            List<String> illegalOrphanMessages = null;
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                AntPersMadre oldAntPersMadreOfHistoriaClinica = historiaClinicaNew.getAntPersMadre();
                if (oldAntPersMadreOfHistoriaClinica != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HistoriaClinica " + historiaClinicaNew + " already has an item of type AntPersMadre whose historiaClinica column cannot be null. Please make another selection for the historiaClinica field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (historiaClinicaNew != null) {
                historiaClinicaNew = em.getReference(historiaClinicaNew.getClass(), historiaClinicaNew.getHicId());
                antPersMadre.setHistoriaClinica(historiaClinicaNew);
            }
            antPersMadre = em.merge(antPersMadre);
            if (historiaClinicaOld != null && !historiaClinicaOld.equals(historiaClinicaNew)) {
                historiaClinicaOld.setAntPersMadre(null);
                historiaClinicaOld = em.merge(historiaClinicaOld);
            }
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                historiaClinicaNew.setAntPersMadre(antPersMadre);
                historiaClinicaNew = em.merge(historiaClinicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = antPersMadre.getHicId();
                if (findAntPersMadre(id) == null) {
                    throw new NonexistentEntityException("The antPersMadre with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AntPersMadre antPersMadre;
            try {
                antPersMadre = em.getReference(AntPersMadre.class, id);
                antPersMadre.getHicId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The antPersMadre with id " + id + " no longer exists.", enfe);
            }
            HistoriaClinica historiaClinica = antPersMadre.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica.setAntPersMadre(null);
                historiaClinica = em.merge(historiaClinica);
            }
            em.remove(antPersMadre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AntPersMadre> findAntPersMadreEntities() {
        return findAntPersMadreEntities(true, -1, -1);
    }

    public List<AntPersMadre> findAntPersMadreEntities(int maxResults, int firstResult) {
        return findAntPersMadreEntities(false, maxResults, firstResult);
    }

    private List<AntPersMadre> findAntPersMadreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AntPersMadre.class));
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

    public AntPersMadre findAntPersMadre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AntPersMadre.class, id);
        } finally {
            em.close();
        }
    }

    public int getAntPersMadreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AntPersMadre> rt = cq.from(AntPersMadre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
