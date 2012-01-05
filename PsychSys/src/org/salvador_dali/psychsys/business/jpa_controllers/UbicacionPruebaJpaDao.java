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
import org.salvador_dali.psychsys.model.UbicacionPruebaDao;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.UbicacionPrueba;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class UbicacionPruebaJpaDao extends JpaDao implements UbicacionPruebaDao {

    public UbicacionPruebaJpaDao() {
        super(UbicacionPrueba.class);
    }

    public UbicacionPruebaJpaDao(Map properties) {
        super(UbicacionPrueba.class, properties);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof UbicacionPrueba) {
            UbicacionPrueba ubicacionPrueba = (UbicacionPrueba) entity;
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
    }
    
    public void update(UbicacionPrueba ubicacionPrueba) throws NonexistentEntityException, Exception {
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
                if (findById(id) == null) {
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

    @Override
    public <E extends Serializable> E update(E entity) {
        if (entity instanceof UbicacionPrueba) {
            UbicacionPrueba ubicacionPrueba = (UbicacionPrueba) entity;
            try {
                update(ubicacionPrueba);
                return (E) ubicacionPrueba;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UbicacionPruebaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(UbicacionPruebaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return null;
        }
    }
    
    public void remove(Integer id) throws NonexistentEntityException {
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

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof UbicacionPrueba) {
            try {
                remove(((UbicacionPrueba) entity).getUbpId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UbicacionPruebaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<UbicacionPrueba> getUbicacionesPruebasByPruebaPsicologica(PruebaPsicologica pruebaPsicologica) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT ubp FROM UbicacionPrueba ubp WHERE ubp.pruebaPsicologica = :ubpPruebaPsicologica");
            q.setParameter("ubpPruebaPsicologica", pruebaPsicologica);
            return (List<UbicacionPrueba>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
