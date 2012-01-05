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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.CasoDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.Referimiento;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class CasoJpaDao extends JpaDao implements CasoDao {

    public CasoJpaDao() {
        super(Caso.class);
    }

    public CasoJpaDao(Map props) {
        super(Caso.class, props);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Caso) {
            Caso caso = (Caso) entity;
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
    }
    
    public void update(Caso caso) throws NonexistentEntityException, Exception {
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
                if (findById(id) == null) {
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

    @Override
    public <E extends Serializable> E update(E entity) {
        if (entity instanceof Caso) {
            Caso caso = (Caso) entity;
            try {
                update(caso);
                return (E) caso;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(CasoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(CasoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Caso) {
            try {
                remove(((Caso) entity).getCsoId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(CasoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<Caso> getCasosByFecha(Date fecha) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Caso.findByCsoFecha");
            q.setParameter("csoFecha", fecha);
            return (List<Caso>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Caso> getCasosByAnioEscolar(String anioEscolar) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Caso.findByCsoAnioEscolar");
            q.setParameter("csoAnioEscolar", anioEscolar);
            return (List<Caso>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Caso getCasoByReferimiento(Referimiento referimiento) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT c FROM Caso c WHERE c.referimiento = :csoReferimiento");
            q.setParameter("csoReferimiento", referimiento);
            return (Caso) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Caso> getCasosByEstado(char estadoCaso) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Caso.findByCsoEstadoCaso");
            q.setParameter("csoEstadoCaso", estadoCaso);
            return (List<Caso>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
