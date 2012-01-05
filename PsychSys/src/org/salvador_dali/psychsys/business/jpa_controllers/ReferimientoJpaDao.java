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
import org.salvador_dali.psychsys.model.ReferimientoDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.Usuario;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class ReferimientoJpaDao extends JpaDao implements ReferimientoDao {

    public ReferimientoJpaDao() {
        super(Referimiento.class);
    }

    public ReferimientoJpaDao(Map props) {
        super(Referimiento.class, props);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Referimiento) {
            Referimiento referimiento = (Referimiento) entity;
            if (referimiento.getCasoCollection() == null) {
                referimiento.setCasoCollection(new ArrayList<Caso>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Usuario usuario = referimiento.getUsuario();
                if (usuario != null) {
                    usuario = em.getReference(usuario.getClass(), usuario.getUsrId());
                    referimiento.setUsuario(usuario);
                }
                Estudiante estudiante = referimiento.getEstudiante();
                if (estudiante != null) {
                    estudiante = em.getReference(estudiante.getClass(), estudiante.getEstId());
                    referimiento.setEstudiante(estudiante);
                }
                Collection<Caso> attachedCasoCollection = new ArrayList<Caso>();
                for (Caso casoCollectionCasoToAttach : referimiento.getCasoCollection()) {
                    casoCollectionCasoToAttach = em.getReference(casoCollectionCasoToAttach.getClass(), casoCollectionCasoToAttach.getCsoId());
                    attachedCasoCollection.add(casoCollectionCasoToAttach);
                }
                referimiento.setCasoCollection(attachedCasoCollection);
                em.persist(referimiento);
                if (usuario != null) {
                    usuario.getReferimientoCollection().add(referimiento);
                    usuario = em.merge(usuario);
                }
                if (estudiante != null) {
                    estudiante.getReferimientoCollection().add(referimiento);
                    estudiante = em.merge(estudiante);
                }
                for (Caso casoCollectionCaso : referimiento.getCasoCollection()) {
                    Referimiento oldReferimientoOfCasoCollectionCaso = casoCollectionCaso.getReferimiento();
                    casoCollectionCaso.setReferimiento(referimiento);
                    casoCollectionCaso = em.merge(casoCollectionCaso);
                    if (oldReferimientoOfCasoCollectionCaso != null) {
                        oldReferimientoOfCasoCollectionCaso.getCasoCollection().remove(casoCollectionCaso);
                        oldReferimientoOfCasoCollectionCaso = em.merge(oldReferimientoOfCasoCollectionCaso);
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
    
    public void update(Referimiento referimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Referimiento persistentReferimiento = em.find(Referimiento.class, referimiento.getRefId());
            Usuario usuarioOld = persistentReferimiento.getUsuario();
            Usuario usuarioNew = referimiento.getUsuario();
            Estudiante estudianteOld = persistentReferimiento.getEstudiante();
            Estudiante estudianteNew = referimiento.getEstudiante();
            Collection<Caso> casoCollectionOld = persistentReferimiento.getCasoCollection();
            Collection<Caso> casoCollectionNew = referimiento.getCasoCollection();
            List<String> illegalOrphanMessages = null;
            for (Caso casoCollectionOldCaso : casoCollectionOld) {
                if (!casoCollectionNew.contains(casoCollectionOldCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Caso " + casoCollectionOldCaso + " since its referimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsrId());
                referimiento.setUsuario(usuarioNew);
            }
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getEstId());
                referimiento.setEstudiante(estudianteNew);
            }
            Collection<Caso> attachedCasoCollectionNew = new ArrayList<Caso>();
            for (Caso casoCollectionNewCasoToAttach : casoCollectionNew) {
                casoCollectionNewCasoToAttach = em.getReference(casoCollectionNewCasoToAttach.getClass(), casoCollectionNewCasoToAttach.getCsoId());
                attachedCasoCollectionNew.add(casoCollectionNewCasoToAttach);
            }
            casoCollectionNew = attachedCasoCollectionNew;
            referimiento.setCasoCollection(casoCollectionNew);
            referimiento = em.merge(referimiento);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getReferimientoCollection().remove(referimiento);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getReferimientoCollection().add(referimiento);
                usuarioNew = em.merge(usuarioNew);
            }
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                estudianteOld.getReferimientoCollection().remove(referimiento);
                estudianteOld = em.merge(estudianteOld);
            }
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                estudianteNew.getReferimientoCollection().add(referimiento);
                estudianteNew = em.merge(estudianteNew);
            }
            for (Caso casoCollectionNewCaso : casoCollectionNew) {
                if (!casoCollectionOld.contains(casoCollectionNewCaso)) {
                    Referimiento oldReferimientoOfCasoCollectionNewCaso = casoCollectionNewCaso.getReferimiento();
                    casoCollectionNewCaso.setReferimiento(referimiento);
                    casoCollectionNewCaso = em.merge(casoCollectionNewCaso);
                    if (oldReferimientoOfCasoCollectionNewCaso != null && !oldReferimientoOfCasoCollectionNewCaso.equals(referimiento)) {
                        oldReferimientoOfCasoCollectionNewCaso.getCasoCollection().remove(casoCollectionNewCaso);
                        oldReferimientoOfCasoCollectionNewCaso = em.merge(oldReferimientoOfCasoCollectionNewCaso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = referimiento.getRefId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The referimiento with id " + id + " no longer exists.");
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
        if (entity instanceof Referimiento) {
            Referimiento referimiento = (Referimiento) entity;
            try {
                update(referimiento);
                return (E) referimiento;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ReferimientoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ReferimientoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(ReferimientoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Referimiento referimiento;
            try {
                referimiento = em.getReference(Referimiento.class, id);
                referimiento.getRefId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The referimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Caso> casoCollectionOrphanCheck = referimiento.getCasoCollection();
            for (Caso casoCollectionOrphanCheckCaso : casoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Referimiento (" + referimiento + ") cannot be destroyed since the Caso " + casoCollectionOrphanCheckCaso + " in its casoCollection field has a non-nullable referimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuario = referimiento.getUsuario();
            if (usuario != null) {
                usuario.getReferimientoCollection().remove(referimiento);
                usuario = em.merge(usuario);
            }
            Estudiante estudiante = referimiento.getEstudiante();
            if (estudiante != null) {
                estudiante.getReferimientoCollection().remove(referimiento);
                estudiante = em.merge(estudiante);
            }
            em.remove(referimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Referimiento) {
            try {
                remove(((Referimiento) entity).getRefId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ReferimientoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ReferimientoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<Referimiento> getReferimientosByFecha(Date fecha) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Referimiento.findByRefFecha");
            q.setParameter("refFecha", fecha);
            return (List<Referimiento>) q.getResultList();
        } finally {
            entityManager.close();
        }
        
    }

    @Override
    public List<Referimiento> getReferimientosByAnioEscolar(String anioEscolar) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Referimiento.findByRefAnioEscolar");
            q.setParameter("refAnioEscolar", anioEscolar);
            return (List<Referimiento>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Referimiento> getReferimientosByEstudiante(Estudiante estudiante) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT r FROM Referimiento r WHERE r.estudiante = :refEstudiante");
            q.setParameter("refEstudiante", estudiante);
            return (List<Referimiento>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Referimiento> getReferimientosByReferidor(String referidor) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Referimiento.findByRefNombreReferidor");
            q.setParameter("refNombreReferidor", referidor);
            return (List<Referimiento>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public List<Referimiento> getReferimientosByUsuario(Usuario usuario) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT r FROM Referimiento r WHERE r.usuario = :refUsuario");
            q.setParameter("refUsuario", usuario);
            return (List<Referimiento>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Referimiento> getReferimientosByEstadoReferimiento(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Referimiento.findByRefEstadoReferimiento");
            q.setParameter("refEstadoReferimiento", status);
            return (List<Referimiento>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
