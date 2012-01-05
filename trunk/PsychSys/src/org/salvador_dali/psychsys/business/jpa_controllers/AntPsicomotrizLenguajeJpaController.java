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
import org.salvador_dali.psychsys.model.entities.AntPsicomotrizLenguaje;
import org.salvador_dali.psychsys.model.entities.HistoriaClinica;
import java.util.ArrayList;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class AntPsicomotrizLenguajeJpaController implements Serializable {

    public AntPsicomotrizLenguajeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AntPsicomotrizLenguaje antPsicomotrizLenguaje) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        HistoriaClinica historiaClinicaOrphanCheck = antPsicomotrizLenguaje.getHistoriaClinica();
        if (historiaClinicaOrphanCheck != null) {
            AntPsicomotrizLenguaje oldAntPsicomotrizLenguajeOfHistoriaClinica = historiaClinicaOrphanCheck.getAntPsicomotrizLenguaje();
            if (oldAntPsicomotrizLenguajeOfHistoriaClinica != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The HistoriaClinica " + historiaClinicaOrphanCheck + " already has an item of type AntPsicomotrizLenguaje whose historiaClinica column cannot be null. Please make another selection for the historiaClinica field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoriaClinica historiaClinica = antPsicomotrizLenguaje.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica = em.getReference(historiaClinica.getClass(), historiaClinica.getHicId());
                antPsicomotrizLenguaje.setHistoriaClinica(historiaClinica);
            }
            em.persist(antPsicomotrizLenguaje);
            if (historiaClinica != null) {
                historiaClinica.setAntPsicomotrizLenguaje(antPsicomotrizLenguaje);
                historiaClinica = em.merge(historiaClinica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAntPsicomotrizLenguaje(antPsicomotrizLenguaje.getHicId()) != null) {
                throw new PreexistingEntityException("AntPsicomotrizLenguaje " + antPsicomotrizLenguaje + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AntPsicomotrizLenguaje antPsicomotrizLenguaje) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AntPsicomotrizLenguaje persistentAntPsicomotrizLenguaje = em.find(AntPsicomotrizLenguaje.class, antPsicomotrizLenguaje.getHicId());
            HistoriaClinica historiaClinicaOld = persistentAntPsicomotrizLenguaje.getHistoriaClinica();
            HistoriaClinica historiaClinicaNew = antPsicomotrizLenguaje.getHistoriaClinica();
            List<String> illegalOrphanMessages = null;
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                AntPsicomotrizLenguaje oldAntPsicomotrizLenguajeOfHistoriaClinica = historiaClinicaNew.getAntPsicomotrizLenguaje();
                if (oldAntPsicomotrizLenguajeOfHistoriaClinica != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The HistoriaClinica " + historiaClinicaNew + " already has an item of type AntPsicomotrizLenguaje whose historiaClinica column cannot be null. Please make another selection for the historiaClinica field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (historiaClinicaNew != null) {
                historiaClinicaNew = em.getReference(historiaClinicaNew.getClass(), historiaClinicaNew.getHicId());
                antPsicomotrizLenguaje.setHistoriaClinica(historiaClinicaNew);
            }
            antPsicomotrizLenguaje = em.merge(antPsicomotrizLenguaje);
            if (historiaClinicaOld != null && !historiaClinicaOld.equals(historiaClinicaNew)) {
                historiaClinicaOld.setAntPsicomotrizLenguaje(null);
                historiaClinicaOld = em.merge(historiaClinicaOld);
            }
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                historiaClinicaNew.setAntPsicomotrizLenguaje(antPsicomotrizLenguaje);
                historiaClinicaNew = em.merge(historiaClinicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = antPsicomotrizLenguaje.getHicId();
                if (findAntPsicomotrizLenguaje(id) == null) {
                    throw new NonexistentEntityException("The antPsicomotrizLenguaje with id " + id + " no longer exists.");
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
            AntPsicomotrizLenguaje antPsicomotrizLenguaje;
            try {
                antPsicomotrizLenguaje = em.getReference(AntPsicomotrizLenguaje.class, id);
                antPsicomotrizLenguaje.getHicId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The antPsicomotrizLenguaje with id " + id + " no longer exists.", enfe);
            }
            HistoriaClinica historiaClinica = antPsicomotrizLenguaje.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica.setAntPsicomotrizLenguaje(null);
                historiaClinica = em.merge(historiaClinica);
            }
            em.remove(antPsicomotrizLenguaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AntPsicomotrizLenguaje> findAntPsicomotrizLenguajeEntities() {
        return findAntPsicomotrizLenguajeEntities(true, -1, -1);
    }

    public List<AntPsicomotrizLenguaje> findAntPsicomotrizLenguajeEntities(int maxResults, int firstResult) {
        return findAntPsicomotrizLenguajeEntities(false, maxResults, firstResult);
    }

    private List<AntPsicomotrizLenguaje> findAntPsicomotrizLenguajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AntPsicomotrizLenguaje.class));
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

    public AntPsicomotrizLenguaje findAntPsicomotrizLenguaje(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AntPsicomotrizLenguaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getAntPsicomotrizLenguajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AntPsicomotrizLenguaje> rt = cq.from(AntPsicomotrizLenguaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
