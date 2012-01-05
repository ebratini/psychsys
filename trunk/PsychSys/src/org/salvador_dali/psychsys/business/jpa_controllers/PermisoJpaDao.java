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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.PermisoDao;
import org.salvador_dali.psychsys.model.entities.Permiso;
import org.salvador_dali.psychsys.model.entities.Rol;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class PermisoJpaDao extends JpaDao implements PermisoDao {

    public PermisoJpaDao() {
        super(Permiso.class);
    }

    public PermisoJpaDao(Map properties) {
        super(Permiso.class, properties);
    }
    
    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Permiso) {
            Permiso permiso = (Permiso) entity;
            if (permiso.getRolCollection() == null) {
                permiso.setRolCollection(new ArrayList<Rol>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Collection<Rol> attachedRolCollection = new ArrayList<Rol>();
                for (Rol rolCollectionRolToAttach : permiso.getRolCollection()) {
                    rolCollectionRolToAttach = em.getReference(rolCollectionRolToAttach.getClass(), rolCollectionRolToAttach.getRolId());
                    attachedRolCollection.add(rolCollectionRolToAttach);
                }
                permiso.setRolCollection(attachedRolCollection);
                em.persist(permiso);
                for (Rol rolCollectionRol : permiso.getRolCollection()) {
                    rolCollectionRol.getPermisoCollection().add(permiso);
                    rolCollectionRol = em.merge(rolCollectionRol);
                }
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }

    public void update(Permiso permiso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permiso persistentPermiso = em.find(Permiso.class, permiso.getPerId());
            Collection<Rol> rolCollectionOld = persistentPermiso.getRolCollection();
            Collection<Rol> rolCollectionNew = permiso.getRolCollection();
            Collection<Rol> attachedRolCollectionNew = new ArrayList<Rol>();
            for (Rol rolCollectionNewRolToAttach : rolCollectionNew) {
                rolCollectionNewRolToAttach = em.getReference(rolCollectionNewRolToAttach.getClass(), rolCollectionNewRolToAttach.getRolId());
                attachedRolCollectionNew.add(rolCollectionNewRolToAttach);
            }
            rolCollectionNew = attachedRolCollectionNew;
            permiso.setRolCollection(rolCollectionNew);
            permiso = em.merge(permiso);
            for (Rol rolCollectionOldRol : rolCollectionOld) {
                if (!rolCollectionNew.contains(rolCollectionOldRol)) {
                    rolCollectionOldRol.getPermisoCollection().remove(permiso);
                    rolCollectionOldRol = em.merge(rolCollectionOldRol);
                }
            }
            for (Rol rolCollectionNewRol : rolCollectionNew) {
                if (!rolCollectionOld.contains(rolCollectionNewRol)) {
                    rolCollectionNewRol.getPermisoCollection().add(permiso);
                    rolCollectionNewRol = em.merge(rolCollectionNewRol);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permiso.getPerId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The permiso with id " + id + " no longer exists.");
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
        if (entity instanceof Permiso) {
            Permiso permiso = (Permiso) entity;
            try {
                update(permiso);
                return (E) permiso;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(PermisoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(PermisoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Permiso permiso;
            try {
                permiso = em.getReference(Permiso.class, id);
                permiso.getPerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permiso with id " + id + " no longer exists.", enfe);
            }
            Collection<Rol> rolCollection = permiso.getRolCollection();
            for (Rol rolCollectionRol : rolCollection) {
                rolCollectionRol.getPermisoCollection().remove(permiso);
                rolCollectionRol = em.merge(rolCollectionRol);
            }
            em.remove(permiso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Permiso) {
            try {
                remove(((Permiso) entity).getPerId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(PermisoJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Permiso getPermisoByNombre(String nombre) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Permiso.findByPerNombre");
            q.setParameter("perNombre", nombre);
            return (Permiso) q.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Permiso> getPermisosByStatus(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Permiso.findByPerStatus");
            q.setParameter("perStatus", status);
            return (List<Permiso>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
