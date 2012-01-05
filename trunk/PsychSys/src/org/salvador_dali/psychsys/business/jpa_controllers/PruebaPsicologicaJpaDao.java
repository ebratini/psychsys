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
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.IllegalOrphanException;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.PruebaPsicologicaDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.PruebaPsicologica;
import org.salvador_dali.psychsys.model.entities.UbicacionPrueba;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class PruebaPsicologicaJpaDao extends JpaDao implements PruebaPsicologicaDao {

    public PruebaPsicologicaJpaDao() {
        super(PruebaPsicologica.class);
    }

    public PruebaPsicologicaJpaDao(Map properties) {
        super(PruebaPsicologica.class, properties);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof PruebaPsicologica) {
            PruebaPsicologica pruebaPsicologica = (PruebaPsicologica) entity;
            if (pruebaPsicologica.getUbicacionPruebaCollection() == null) {
                pruebaPsicologica.setUbicacionPruebaCollection(new ArrayList<UbicacionPrueba>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Estudiante estudiante = pruebaPsicologica.getEstudiante();
                if (estudiante != null) {
                    estudiante = em.getReference(estudiante.getClass(), estudiante.getEstId());
                    pruebaPsicologica.setEstudiante(estudiante);
                }
                Caso caso = pruebaPsicologica.getCaso();
                if (caso != null) {
                    caso = em.getReference(caso.getClass(), caso.getCsoId());
                    pruebaPsicologica.setCaso(caso);
                }
                Collection<UbicacionPrueba> attachedUbicacionPruebaCollection = new ArrayList<UbicacionPrueba>();
                for (UbicacionPrueba ubicacionPruebaCollectionUbicacionPruebaToAttach : pruebaPsicologica.getUbicacionPruebaCollection()) {
                    ubicacionPruebaCollectionUbicacionPruebaToAttach = em.getReference(ubicacionPruebaCollectionUbicacionPruebaToAttach.getClass(), ubicacionPruebaCollectionUbicacionPruebaToAttach.getUbpId());
                    attachedUbicacionPruebaCollection.add(ubicacionPruebaCollectionUbicacionPruebaToAttach);
                }
                pruebaPsicologica.setUbicacionPruebaCollection(attachedUbicacionPruebaCollection);
                em.persist(pruebaPsicologica);
                if (estudiante != null) {
                    estudiante.getPruebaPsicologicaCollection().add(pruebaPsicologica);
                    estudiante = em.merge(estudiante);
                }
                if (caso != null) {
                    caso.getPruebaPsicologicaCollection().add(pruebaPsicologica);
                    caso = em.merge(caso);
                }
                for (UbicacionPrueba ubicacionPruebaCollectionUbicacionPrueba : pruebaPsicologica.getUbicacionPruebaCollection()) {
                    PruebaPsicologica oldPruebaPsicologicaOfUbicacionPruebaCollectionUbicacionPrueba = ubicacionPruebaCollectionUbicacionPrueba.getPruebaPsicologica();
                    ubicacionPruebaCollectionUbicacionPrueba.setPruebaPsicologica(pruebaPsicologica);
                    ubicacionPruebaCollectionUbicacionPrueba = em.merge(ubicacionPruebaCollectionUbicacionPrueba);
                    if (oldPruebaPsicologicaOfUbicacionPruebaCollectionUbicacionPrueba != null) {
                        oldPruebaPsicologicaOfUbicacionPruebaCollectionUbicacionPrueba.getUbicacionPruebaCollection().remove(ubicacionPruebaCollectionUbicacionPrueba);
                        oldPruebaPsicologicaOfUbicacionPruebaCollectionUbicacionPrueba = em.merge(oldPruebaPsicologicaOfUbicacionPruebaCollectionUbicacionPrueba);
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

    public void update(PruebaPsicologica pruebaPsicologica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PruebaPsicologica persistentPruebaPsicologica = em.find(PruebaPsicologica.class, pruebaPsicologica.getPpsId());
            Estudiante estudianteOld = persistentPruebaPsicologica.getEstudiante();
            Estudiante estudianteNew = pruebaPsicologica.getEstudiante();
            Caso casoOld = persistentPruebaPsicologica.getCaso();
            Caso casoNew = pruebaPsicologica.getCaso();
            Collection<UbicacionPrueba> ubicacionPruebaCollectionOld = persistentPruebaPsicologica.getUbicacionPruebaCollection();
            Collection<UbicacionPrueba> ubicacionPruebaCollectionNew = pruebaPsicologica.getUbicacionPruebaCollection();
            List<String> illegalOrphanMessages = null;
            for (UbicacionPrueba ubicacionPruebaCollectionOldUbicacionPrueba : ubicacionPruebaCollectionOld) {
                if (!ubicacionPruebaCollectionNew.contains(ubicacionPruebaCollectionOldUbicacionPrueba)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UbicacionPrueba " + ubicacionPruebaCollectionOldUbicacionPrueba + " since its pruebaPsicologica field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getEstId());
                pruebaPsicologica.setEstudiante(estudianteNew);
            }
            if (casoNew != null) {
                casoNew = em.getReference(casoNew.getClass(), casoNew.getCsoId());
                pruebaPsicologica.setCaso(casoNew);
            }
            Collection<UbicacionPrueba> attachedUbicacionPruebaCollectionNew = new ArrayList<UbicacionPrueba>();
            for (UbicacionPrueba ubicacionPruebaCollectionNewUbicacionPruebaToAttach : ubicacionPruebaCollectionNew) {
                ubicacionPruebaCollectionNewUbicacionPruebaToAttach = em.getReference(ubicacionPruebaCollectionNewUbicacionPruebaToAttach.getClass(), ubicacionPruebaCollectionNewUbicacionPruebaToAttach.getUbpId());
                attachedUbicacionPruebaCollectionNew.add(ubicacionPruebaCollectionNewUbicacionPruebaToAttach);
            }
            ubicacionPruebaCollectionNew = attachedUbicacionPruebaCollectionNew;
            pruebaPsicologica.setUbicacionPruebaCollection(ubicacionPruebaCollectionNew);
            pruebaPsicologica = em.merge(pruebaPsicologica);
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                estudianteOld.getPruebaPsicologicaCollection().remove(pruebaPsicologica);
                estudianteOld = em.merge(estudianteOld);
            }
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                estudianteNew.getPruebaPsicologicaCollection().add(pruebaPsicologica);
                estudianteNew = em.merge(estudianteNew);
            }
            if (casoOld != null && !casoOld.equals(casoNew)) {
                casoOld.getPruebaPsicologicaCollection().remove(pruebaPsicologica);
                casoOld = em.merge(casoOld);
            }
            if (casoNew != null && !casoNew.equals(casoOld)) {
                casoNew.getPruebaPsicologicaCollection().add(pruebaPsicologica);
                casoNew = em.merge(casoNew);
            }
            for (UbicacionPrueba ubicacionPruebaCollectionNewUbicacionPrueba : ubicacionPruebaCollectionNew) {
                if (!ubicacionPruebaCollectionOld.contains(ubicacionPruebaCollectionNewUbicacionPrueba)) {
                    PruebaPsicologica oldPruebaPsicologicaOfUbicacionPruebaCollectionNewUbicacionPrueba = ubicacionPruebaCollectionNewUbicacionPrueba.getPruebaPsicologica();
                    ubicacionPruebaCollectionNewUbicacionPrueba.setPruebaPsicologica(pruebaPsicologica);
                    ubicacionPruebaCollectionNewUbicacionPrueba = em.merge(ubicacionPruebaCollectionNewUbicacionPrueba);
                    if (oldPruebaPsicologicaOfUbicacionPruebaCollectionNewUbicacionPrueba != null && !oldPruebaPsicologicaOfUbicacionPruebaCollectionNewUbicacionPrueba.equals(pruebaPsicologica)) {
                        oldPruebaPsicologicaOfUbicacionPruebaCollectionNewUbicacionPrueba.getUbicacionPruebaCollection().remove(ubicacionPruebaCollectionNewUbicacionPrueba);
                        oldPruebaPsicologicaOfUbicacionPruebaCollectionNewUbicacionPrueba = em.merge(oldPruebaPsicologicaOfUbicacionPruebaCollectionNewUbicacionPrueba);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pruebaPsicologica.getPpsId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The pruebaPsicologica with id " + id + " no longer exists.");
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
        if (entity instanceof PruebaPsicologica) {
            PruebaPsicologica pruebaPsicologica = (PruebaPsicologica) entity;
            try {
                update(pruebaPsicologica);
                return (E) pruebaPsicologica;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(PruebaPsicologicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(PruebaPsicologicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(PruebaPsicologicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            PruebaPsicologica pruebaPsicologica;
            try {
                pruebaPsicologica = em.getReference(PruebaPsicologica.class, id);
                pruebaPsicologica.getPpsId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pruebaPsicologica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UbicacionPrueba> ubicacionPruebaCollectionOrphanCheck = pruebaPsicologica.getUbicacionPruebaCollection();
            for (UbicacionPrueba ubicacionPruebaCollectionOrphanCheckUbicacionPrueba : ubicacionPruebaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PruebaPsicologica (" + pruebaPsicologica + ") cannot be destroyed since the UbicacionPrueba " + ubicacionPruebaCollectionOrphanCheckUbicacionPrueba + " in its ubicacionPruebaCollection field has a non-nullable pruebaPsicologica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estudiante estudiante = pruebaPsicologica.getEstudiante();
            if (estudiante != null) {
                estudiante.getPruebaPsicologicaCollection().remove(pruebaPsicologica);
                estudiante = em.merge(estudiante);
            }
            Caso caso = pruebaPsicologica.getCaso();
            if (caso != null) {
                caso.getPruebaPsicologicaCollection().remove(pruebaPsicologica);
                caso = em.merge(caso);
            }
            em.remove(pruebaPsicologica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof PruebaPsicologica) {
            try {
                remove(((PruebaPsicologica) entity).getPpsId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(PruebaPsicologicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(PruebaPsicologicaJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<PruebaPsicologica> getPruebasPsicologicasByEstudiante(Estudiante estudiante) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT pps FROM PruebaPsicologica pps WHERE pps.estudiante = :ppsEstudiante");
            q.setParameter("ppsEstudiante", estudiante);
            return (List<PruebaPsicologica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PruebaPsicologica> getPruebasPsicologicasByCaso(Caso caso) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT pps FROM PruebaPsicologica pps WHERE pps.caso = :ppsCaso");
            q.setParameter("ppsCaso", caso);
            return (List<PruebaPsicologica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PruebaPsicologica> getPruebasPsicologicasByFechaAplicacion(Date fechaAplicacion) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("PruebaPsicologica.findByPpsFechaAplicacion");
            q.setParameter("ppsFechaAplicacion", fechaAplicacion);
            return (List<PruebaPsicologica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PruebaPsicologica> getPruebasPsicologicasByNombrePrueba(String nombrePrueba) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("PruebaPsicologica.findByPpsNombrePrueba");
            q.setParameter("ppsNombrePrueba", nombrePrueba);
            return (List<PruebaPsicologica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PruebaPsicologica> getPruebasPsicologicasByCorreccionAutomatica(char correcionAutomatica) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("PruebaPsicologica.findByPpsCorrecionAutomatica");
            q.setParameter("ppsCorrecionAutomatica", correcionAutomatica);
            return (List<PruebaPsicologica>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
