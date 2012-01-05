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
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.UbicacionPrueba;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class UbicacionPruebaJpaController implements Serializable {

    public UbicacionPruebaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UbicacionPrueba ubicacionPrueba) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PruebaPsicologica pruebaPsicologica = ubicacionPrueba.getPruebaPsicologica();
            if (pruebaPsicologica != null) {
                pruebaPsicologica = em.getReference(pruebaPsicologica.getClass(), pruebaPsicologica.getPpsId());
                ubicacionPrueba.setPruebaPsicologica(pruebaPsicologica);
            }
            em.persist(ubicacionPrueba);
            if (pruebaPsicologica != null) {
                pruebaPsicologica.getUbicacionPruebaCollection().add(ubicacionPrueba);
                pruebaPsicologica = em.merge(pruebaPsicologica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UbicacionPrueba ubicacionPrueba) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UbicacionPrueba persistentUbicacionPrueba = em.find(UbicacionPrueba.class, ubicacionPrueba.getUbpId());
            PruebaPsicologica pruebaPsicologicaOld = persistentUbicacionPrueba.getPruebaPsicologica();
            PruebaPsicologica pruebaPsicologicaNew = ubicacionPrueba.getPruebaPsicologica();
            if (pruebaPsicologicaNew != null) {
                pruebaPsicologicaNew = em.getReference(pruebaPsicologicaNew.getClass(), pruebaPsicologicaNew.getPpsId());
                ubicacionPrueba.setPruebaPsicologica(pruebaPsicologicaNew);
            }
            ubicacionPrueba = em.merge(ubicacionPrueba);
            if (pruebaPsicologicaOld != null && !pruebaPsicologicaOld.equals(pruebaPsicologicaNew)) {
                pruebaPsicologicaOld.getUbicacionPruebaCollection().remove(ubicacionPrueba);
                pruebaPsicologicaOld = em.merge(pruebaPsicologicaOld);
            }
            if (pruebaPsicologicaNew != null && !pruebaPsicologicaNew.equals(pruebaPsicologicaOld)) {
                pruebaPsicologicaNew.getUbicacionPruebaCollection().add(ubicacionPrueba);
                pruebaPsicologicaNew = em.merge(pruebaPsicologicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ubicacionPrueba.getUbpId();
                if (findUbicacionPrueba(id) == null) {
                    throw new NonexistentEntityException("The ubicacionPrueba with id " + id + " no longer exists.");
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
            UbicacionPrueba ubicacionPrueba;
            try {
                ubicacionPrueba = em.getReference(UbicacionPrueba.class, id);
                ubicacionPrueba.getUbpId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ubicacionPrueba with id " + id + " no longer exists.", enfe);
            }
            PruebaPsicologica pruebaPsicologica = ubicacionPrueba.getPruebaPsicologica();
            if (pruebaPsicologica != null) {
                pruebaPsicologica.getUbicacionPruebaCollection().remove(ubicacionPrueba);
                pruebaPsicologica = em.merge(pruebaPsicologica);
            }
            em.remove(ubicacionPrueba);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UbicacionPrueba> findUbicacionPruebaEntities() {
        return findUbicacionPruebaEntities(true, -1, -1);
    }

    public List<UbicacionPrueba> findUbicacionPruebaEntities(int maxResults, int firstResult) {
        return findUbicacionPruebaEntities(false, maxResults, firstResult);
    }

    private List<UbicacionPrueba> findUbicacionPruebaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UbicacionPrueba.class));
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

    public UbicacionPrueba findUbicacionPrueba(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UbicacionPrueba.class, id);
        } finally {
            em.close();
        }
    }

    public int getUbicacionPruebaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UbicacionPrueba> rt = cq.from(UbicacionPrueba.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
