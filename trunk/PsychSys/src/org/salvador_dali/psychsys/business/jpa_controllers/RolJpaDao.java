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
import org.salvador_dali.psychsys.business.exceptions.IllegalOrphanException;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.RolDao;
import org.salvador_dali.psychsys.model.entities.Permiso;
import org.salvador_dali.psychsys.model.entities.Rol;
import org.salvador_dali.psychsys.model.entities.Usuario;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class RolJpaDao extends JpaDao implements RolDao {

    public RolJpaDao() {
        super(Rol.class);
    }

    public RolJpaDao(Map properties) {
        super(Rol.class, properties);
    }
    
    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Rol) {
            Rol rol = (Rol) entity;
            if (rol.getPermisoCollection() == null) {
                rol.setPermisoCollection(new ArrayList<Permiso>());
            }
            if (rol.getUsuarioCollection() == null) {
                rol.setUsuarioCollection(new ArrayList<Usuario>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Collection<Permiso> attachedPermisoCollection = new ArrayList<Permiso>();
                for (Permiso permisoCollectionPermisoToAttach : rol.getPermisoCollection()) {
                    permisoCollectionPermisoToAttach = em.getReference(permisoCollectionPermisoToAttach.getClass(), permisoCollectionPermisoToAttach.getPerId());
                    attachedPermisoCollection.add(permisoCollectionPermisoToAttach);
                }
                rol.setPermisoCollection(attachedPermisoCollection);
                Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
                for (Usuario usuarioCollectionUsuarioToAttach : rol.getUsuarioCollection()) {
                    usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getUsrId());
                    attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
                }
                rol.setUsuarioCollection(attachedUsuarioCollection);
                em.persist(rol);
                for (Permiso permisoCollectionPermiso : rol.getPermisoCollection()) {
                    permisoCollectionPermiso.getRolCollection().add(rol);
                    permisoCollectionPermiso = em.merge(permisoCollectionPermiso);
                }
                for (Usuario usuarioCollectionUsuario : rol.getUsuarioCollection()) {
                    Rol oldRolOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getRol();
                    usuarioCollectionUsuario.setRol(rol);
                    usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                    if (oldRolOfUsuarioCollectionUsuario != null) {
                        oldRolOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                        oldRolOfUsuarioCollectionUsuario = em.merge(oldRolOfUsuarioCollectionUsuario);
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
    
    public void update(Rol rol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getRolId());
            Collection<Permiso> permisoCollectionOld = persistentRol.getPermisoCollection();
            Collection<Permiso> permisoCollectionNew = rol.getPermisoCollection();
            Collection<Usuario> usuarioCollectionOld = persistentRol.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = rol.getUsuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioCollectionOldUsuario + " since its rol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Permiso> attachedPermisoCollectionNew = new ArrayList<Permiso>();
            for (Permiso permisoCollectionNewPermisoToAttach : permisoCollectionNew) {
                permisoCollectionNewPermisoToAttach = em.getReference(permisoCollectionNewPermisoToAttach.getClass(), permisoCollectionNewPermisoToAttach.getPerId());
                attachedPermisoCollectionNew.add(permisoCollectionNewPermisoToAttach);
            }
            permisoCollectionNew = attachedPermisoCollectionNew;
            rol.setPermisoCollection(permisoCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getUsrId());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            rol.setUsuarioCollection(usuarioCollectionNew);
            rol = em.merge(rol);
            for (Permiso permisoCollectionOldPermiso : permisoCollectionOld) {
                if (!permisoCollectionNew.contains(permisoCollectionOldPermiso)) {
                    permisoCollectionOldPermiso.getRolCollection().remove(rol);
                    permisoCollectionOldPermiso = em.merge(permisoCollectionOldPermiso);
                }
            }
            for (Permiso permisoCollectionNewPermiso : permisoCollectionNew) {
                if (!permisoCollectionOld.contains(permisoCollectionNewPermiso)) {
                    permisoCollectionNewPermiso.getRolCollection().add(rol);
                    permisoCollectionNewPermiso = em.merge(permisoCollectionNewPermiso);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Rol oldRolOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getRol();
                    usuarioCollectionNewUsuario.setRol(rol);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldRolOfUsuarioCollectionNewUsuario != null && !oldRolOfUsuarioCollectionNewUsuario.equals(rol)) {
                        oldRolOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldRolOfUsuarioCollectionNewUsuario = em.merge(oldRolOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rol.getRolId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
        if (entity instanceof Rol) {
            Rol rol = (Rol) entity;
            try {
                update(rol);
                return (E) rol;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(RolJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RolJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(RolJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getRolId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Usuario> usuarioCollectionOrphanCheck = rol.getUsuarioCollection();
            for (Usuario usuarioCollectionOrphanCheckUsuario : usuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rol (" + rol + ") cannot be destroyed since the Usuario " + usuarioCollectionOrphanCheckUsuario + " in its usuarioCollection field has a non-nullable rol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Permiso> permisoCollection = rol.getPermisoCollection();
            for (Permiso permisoCollectionPermiso : permisoCollection) {
                permisoCollectionPermiso.getRolCollection().remove(rol);
                permisoCollectionPermiso = em.merge(permisoCollectionPermiso);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Rol) {
            try {
                remove(((Rol) entity).getRolId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(RolJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RolJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Rol getRolByNombre(String nombre) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Rol.findByRolNombre");
            q.setParameter("rolNombre", nombre);
            return (Rol) q.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Rol> getRolesByStatus(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Rol.findByRolStatus");
            q.setParameter("rolStatus", status);
            return (List<Rol>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
