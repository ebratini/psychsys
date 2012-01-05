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
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class CasoJpaController implements Serializable {

    public CasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caso caso) {
        if (caso.getPruebaPsicologicaCollection() == null) {
            caso.setPruebaPsicologicaCollection(new ArrayList<PruebaPsicologica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Referimiento referimiento = caso.getReferimiento();
            if (referimiento != null) {
                referimiento = em.getReference(referimiento.getClass(), referimiento.getRefId());
                caso.setReferimiento(referimiento);
            }
            Collection<PruebaPsicologica> attachedPruebaPsicologicaCollection = new ArrayList<PruebaPsicologica>();
            for (PruebaPsicologica pruebaPsicologicaCollectionPruebaPsicologicaToAttach : caso.getPruebaPsicologicaCollection()) {
                pruebaPsicologicaCollectionPruebaPsicologicaToAttach = em.getReference(pruebaPsicologicaCollectionPruebaPsicologicaToAttach.getClass(), pruebaPsicologicaCollectionPruebaPsicologicaToAttach.getPpsId());
                attachedPruebaPsicologicaCollection.add(pruebaPsicologicaCollectionPruebaPsicologicaToAttach);
            }
            caso.setPruebaPsicologicaCollection(attachedPruebaPsicologicaCollection);
            em.persist(caso);
            if (referimiento != null) {
                referimiento.getCasoCollection().add(caso);
                referimiento = em.merge(referimiento);
            }
            for (PruebaPsicologica pruebaPsicologicaCollectionPruebaPsicologica : caso.getPruebaPsicologicaCollection()) {
                Caso oldCasoOfPruebaPsicologicaCollectionPruebaPsicologica = pruebaPsicologicaCollectionPruebaPsicologica.getCaso();
                pruebaPsicologicaCollectionPruebaPsicologica.setCaso(caso);
                pruebaPsicologicaCollectionPruebaPsicologica = em.merge(pruebaPsicologicaCollectionPruebaPsicologica);
                if (oldCasoOfPruebaPsicologicaCollectionPruebaPsicologica != null) {
                    oldCasoOfPruebaPsicologicaCollectionPruebaPsicologica.getPruebaPsicologicaCollection().remove(pruebaPsicologicaCollectionPruebaPsicologica);
                    oldCasoOfPruebaPsicologicaCollectionPruebaPsicologica = em.merge(oldCasoOfPruebaPsicologicaCollectionPruebaPsicologica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caso caso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caso persistentCaso = em.find(Caso.class, caso.getCsoId());
            Referimiento referimientoOld = persistentCaso.getReferimiento();
            Referimiento referimientoNew = caso.getReferimiento();
            Collection<PruebaPsicologica> pruebaPsicologicaCollectionOld = persistentCaso.getPruebaPsicologicaCollection();
            Collection<PruebaPsicologica> pruebaPsicologicaCollectionNew = caso.getPruebaPsicologicaCollection();
            if (referimientoNew != null) {
                referimientoNew = em.getReference(referimientoNew.getClass(), referimientoNew.getRefId());
                caso.setReferimiento(referimientoNew);
            }
            Collection<PruebaPsicologica> attachedPruebaPsicologicaCollectionNew = new ArrayList<PruebaPsicologica>();
            for (PruebaPsicologica pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach : pruebaPsicologicaCollectionNew) {
                pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach = em.getReference(pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach.getClass(), pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach.getPpsId());
                attachedPruebaPsicologicaCollectionNew.add(pruebaPsicologicaCollectionNewPruebaPsicologicaToAttach);
            }
            pruebaPsicologicaCollectionNew = attachedPruebaPsicologicaCollectionNew;
            caso.setPruebaPsicologicaCollection(pruebaPsicologicaCollectionNew);
            caso = em.merge(caso);
            if (referimientoOld != null && !referimientoOld.equals(referimientoNew)) {
                referimientoOld.getCasoCollection().remove(caso);
                referimientoOld = em.merge(referimientoOld);
            }
            if (referimientoNew != null && !referimientoNew.equals(referimientoOld)) {
                referimientoNew.getCasoCollection().add(caso);
                referimientoNew = em.merge(referimientoNew);
            }
            for (PruebaPsicologica pruebaPsicologicaCollectionOldPruebaPsicologica : pruebaPsicologicaCollectionOld) {
                if (!pruebaPsicologicaCollectionNew.contains(pruebaPsicologicaCollectionOldPruebaPsicologica)) {
                    pruebaPsicologicaCollectionOldPruebaPsicologica.setCaso(null);
                    pruebaPsicologicaCollectionOldPruebaPsicologica = em.merge(pruebaPsicologicaCollectionOldPruebaPsicologica);
                }
            }
            for (PruebaPsicologica pruebaPsicologicaCollectionNewPruebaPsicologica : pruebaPsicologicaCollectionNew) {
                if (!pruebaPsicologicaCollectionOld.contains(pruebaPsicologicaCollectionNewPruebaPsicologica)) {
                    Caso oldCasoOfPruebaPsicologicaCollectionNewPruebaPsicologica = pruebaPsicologicaCollectionNewPruebaPsicologica.getCaso();
                    pruebaPsicologicaCollectionNewPruebaPsicologica.setCaso(caso);
                    pruebaPsicologicaCollectionNewPruebaPsicologica = em.merge(pruebaPsicologicaCollectionNewPruebaPsicologica);
                    if (oldCasoOfPruebaPsicologicaCollectionNewPruebaPsicologica != null && !oldCasoOfPruebaPsicologicaCollectionNewPruebaPsicologica.equals(caso)) {
                        oldCasoOfPruebaPsicologicaCollectionNewPruebaPsicologica.getPruebaPsicologicaCollection().remove(pruebaPsicologicaCollectionNewPruebaPsicologica);
                        oldCasoOfPruebaPsicologicaCollectionNewPruebaPsicologica = em.merge(oldCasoOfPruebaPsicologicaCollectionNewPruebaPsicologica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caso.getCsoId();
                if (findCaso(id) == null) {
                    throw new NonexistentEntityException("The caso with id " + id + " no longer exists.");
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
            Caso caso;
            try {
                caso = em.getReference(Caso.class, id);
                caso.getCsoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caso with id " + id + " no longer exists.", enfe);
            }
            Referimiento referimiento = caso.getReferimiento();
            if (referimiento != null) {
                referimiento.getCasoCollection().remove(caso);
                referimiento = em.merge(referimiento);
            }
            Collection<PruebaPsicologica> pruebaPsicologicaCollection = caso.getPruebaPsicologicaCollection();
            for (PruebaPsicologica pruebaPsicologicaCollectionPruebaPsicologica : pruebaPsicologicaCollection) {
                pruebaPsicologicaCollectionPruebaPsicologica.setCaso(null);
                pruebaPsicologicaCollectionPruebaPsicologica = em.merge(pruebaPsicologicaCollectionPruebaPsicologica);
            }
            em.remove(caso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caso> findCasoEntities() {
        return findCasoEntities(true, -1, -1);
    }

    public List<Caso> findCasoEntities(int maxResults, int firstResult) {
        return findCasoEntities(false, maxResults, firstResult);
    }

    private List<Caso> findCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caso.class));
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

    public Caso findCaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caso> rt = cq.from(Caso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
