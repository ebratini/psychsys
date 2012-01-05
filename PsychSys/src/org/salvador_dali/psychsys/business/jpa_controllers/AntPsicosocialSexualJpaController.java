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
import org.salvador_dali.psychsys.model.entities.AntPsicosocialSexual;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import java.util.ArrayList;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class AntPsicosocialSexualJpaController implements Serializable {

    public AntPsicosocialSexualJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AntPsicosocialSexual antPsicosocialSexual) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        HistoriaClinica historiaClinicaOrphanCheck = antPsicosocialSexual.getHistoriaClinica();
        if (historiaClinicaOrphanCheck != null) {
            AntPsicosocialSexual oldAntPsicosocialSexualOfHistoriaClinica = historiaClinicaOrphanCheck.getAntPsicosocialSexual();
            if (oldAntPsicosocialSexualOfHistoriaClinica != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HistoriaClinica " + historiaClinicaOrphanCheck + " already has an item of type AntPsicosocialSexual whose historiaClinica column cannot be null. Please make another selection for the historiaClinica field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoriaClinica historiaClinica = antPsicosocialSexual.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica = em.getReference(historiaClinica.getClass(), historiaClinica.getHicId());
                antPsicosocialSexual.setHistoriaClinica(historiaClinica);
            }
            em.persist(antPsicosocialSexual);
            if (historiaClinica != null) {
                historiaClinica.setAntPsicosocialSexual(antPsicosocialSexual);
                historiaClinica = em.merge(historiaClinica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAntPsicosocialSexual(antPsicosocialSexual.getHicId()) != null) {
                throw new PreexistingEntityException("AntPsicosocialSexual " + antPsicosocialSexual + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AntPsicosocialSexual antPsicosocialSexual) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AntPsicosocialSexual persistentAntPsicosocialSexual = em.find(AntPsicosocialSexual.class, antPsicosocialSexual.getHicId());
            HistoriaClinica historiaClinicaOld = persistentAntPsicosocialSexual.getHistoriaClinica();
            HistoriaClinica historiaClinicaNew = antPsicosocialSexual.getHistoriaClinica();
            List<String> illegalOrphanMessages = null;
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                AntPsicosocialSexual oldAntPsicosocialSexualOfHistoriaClinica = historiaClinicaNew.getAntPsicosocialSexual();
                if (oldAntPsicosocialSexualOfHistoriaClinica != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HistoriaClinica " + historiaClinicaNew + " already has an item of type AntPsicosocialSexual whose historiaClinica column cannot be null. Please make another selection for the historiaClinica field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (historiaClinicaNew != null) {
                historiaClinicaNew = em.getReference(historiaClinicaNew.getClass(), historiaClinicaNew.getHicId());
                antPsicosocialSexual.setHistoriaClinica(historiaClinicaNew);
            }
            antPsicosocialSexual = em.merge(antPsicosocialSexual);
            if (historiaClinicaOld != null && !historiaClinicaOld.equals(historiaClinicaNew)) {
                historiaClinicaOld.setAntPsicosocialSexual(null);
                historiaClinicaOld = em.merge(historiaClinicaOld);
            }
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                historiaClinicaNew.setAntPsicosocialSexual(antPsicosocialSexual);
                historiaClinicaNew = em.merge(historiaClinicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = antPsicosocialSexual.getHicId();
                if (findAntPsicosocialSexual(id) == null) {
                    throw new NonexistentEntityException("The antPsicosocialSexual with id " + id + " no longer exists.");
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
            AntPsicosocialSexual antPsicosocialSexual;
            try {
                antPsicosocialSexual = em.getReference(AntPsicosocialSexual.class, id);
                antPsicosocialSexual.getHicId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The antPsicosocialSexual with id " + id + " no longer exists.", enfe);
            }
            HistoriaClinica historiaClinica = antPsicosocialSexual.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica.setAntPsicosocialSexual(null);
                historiaClinica = em.merge(historiaClinica);
            }
            em.remove(antPsicosocialSexual);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AntPsicosocialSexual> findAntPsicosocialSexualEntities() {
        return findAntPsicosocialSexualEntities(true, -1, -1);
    }

    public List<AntPsicosocialSexual> findAntPsicosocialSexualEntities(int maxResults, int firstResult) {
        return findAntPsicosocialSexualEntities(false, maxResults, firstResult);
    }

    private List<AntPsicosocialSexual> findAntPsicosocialSexualEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AntPsicosocialSexual.class));
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

    public AntPsicosocialSexual findAntPsicosocialSexual(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AntPsicosocialSexual.class, id);
        } finally {
            em.close();
        }
    }

    public int getAntPsicosocialSexualCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AntPsicosocialSexual> rt = cq.from(AntPsicosocialSexual.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
