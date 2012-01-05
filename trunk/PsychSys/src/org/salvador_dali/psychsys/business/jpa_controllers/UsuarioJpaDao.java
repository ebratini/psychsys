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
import org.salvador_dali.psychsys.business.exceptions.IllegalOrphanException;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.UsuarioDao;
import org.salvador_dali.psychsys.model.entities.Bitacora;
import org.salvador_dali.psychsys.model.entities.Referimiento;
import org.salvador_dali.psychsys.model.entities.Rol;
import org.salvador_dali.psychsys.model.entities.Usuario;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class UsuarioJpaDao extends JpaDao implements UsuarioDao {

    public UsuarioJpaDao() {
        super(Usuario.class);
    }

    public UsuarioJpaDao(Map properties) {
        super(Usuario.class, properties);
    }
    
    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Usuario) {
            Usuario usuario = (Usuario) entity;
            if (usuario.getReferimientoCollection() == null) {
                usuario.setReferimientoCollection(new ArrayList<Referimiento>());
            }
            if (usuario.getBitacoraCollection() == null) {
                usuario.setBitacoraCollection(new ArrayList<Bitacora>());
            }
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Rol rol = usuario.getRol();
                if (rol != null) {
                    rol = em.getReference(rol.getClass(), rol.getRolId());
                    usuario.setRol(rol);
                }
                Collection<Referimiento> attachedReferimientoCollection = new ArrayList<Referimiento>();
                for (Referimiento referimientoCollectionReferimientoToAttach : usuario.getReferimientoCollection()) {
                    referimientoCollectionReferimientoToAttach = em.getReference(referimientoCollectionReferimientoToAttach.getClass(), referimientoCollectionReferimientoToAttach.getRefId());
                    attachedReferimientoCollection.add(referimientoCollectionReferimientoToAttach);
                }
                usuario.setReferimientoCollection(attachedReferimientoCollection);
                Collection<Bitacora> attachedBitacoraCollection = new ArrayList<Bitacora>();
                for (Bitacora bitacoraCollectionBitacoraToAttach : usuario.getBitacoraCollection()) {
                    bitacoraCollectionBitacoraToAttach = em.getReference(bitacoraCollectionBitacoraToAttach.getClass(), bitacoraCollectionBitacoraToAttach.getBitId());
                    attachedBitacoraCollection.add(bitacoraCollectionBitacoraToAttach);
                }
                usuario.setBitacoraCollection(attachedBitacoraCollection);
                em.persist(usuario);
                if (rol != null) {
                    rol.getUsuarioCollection().add(usuario);
                    rol = em.merge(rol);
                }
                for (Referimiento referimientoCollectionReferimiento : usuario.getReferimientoCollection()) {
                    Usuario oldUsuarioOfReferimientoCollectionReferimiento = referimientoCollectionReferimiento.getUsuario();
                    referimientoCollectionReferimiento.setUsuario(usuario);
                    referimientoCollectionReferimiento = em.merge(referimientoCollectionReferimiento);
                    if (oldUsuarioOfReferimientoCollectionReferimiento != null) {
                        oldUsuarioOfReferimientoCollectionReferimiento.getReferimientoCollection().remove(referimientoCollectionReferimiento);
                        oldUsuarioOfReferimientoCollectionReferimiento = em.merge(oldUsuarioOfReferimientoCollectionReferimiento);
                    }
                }
                for (Bitacora bitacoraCollectionBitacora : usuario.getBitacoraCollection()) {
                    Usuario oldUsuarioOfBitacoraCollectionBitacora = bitacoraCollectionBitacora.getUsuario();
                    bitacoraCollectionBitacora.setUsuario(usuario);
                    bitacoraCollectionBitacora = em.merge(bitacoraCollectionBitacora);
                    if (oldUsuarioOfBitacoraCollectionBitacora != null) {
                        oldUsuarioOfBitacoraCollectionBitacora.getBitacoraCollection().remove(bitacoraCollectionBitacora);
                        oldUsuarioOfBitacoraCollectionBitacora = em.merge(oldUsuarioOfBitacoraCollectionBitacora);
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
    
    public void update(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsrId());
            Rol rolOld = persistentUsuario.getRol();
            Rol rolNew = usuario.getRol();
            Collection<Referimiento> referimientoCollectionOld = persistentUsuario.getReferimientoCollection();
            Collection<Referimiento> referimientoCollectionNew = usuario.getReferimientoCollection();
            Collection<Bitacora> bitacoraCollectionOld = persistentUsuario.getBitacoraCollection();
            Collection<Bitacora> bitacoraCollectionNew = usuario.getBitacoraCollection();
            List<String> illegalOrphanMessages = null;
            for (Referimiento referimientoCollectionOldReferimiento : referimientoCollectionOld) {
                if (!referimientoCollectionNew.contains(referimientoCollectionOldReferimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Referimiento " + referimientoCollectionOldReferimiento + " since its usuario field is not nullable.");
                }
            }
            for (Bitacora bitacoraCollectionOldBitacora : bitacoraCollectionOld) {
                if (!bitacoraCollectionNew.contains(bitacoraCollectionOldBitacora)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bitacora " + bitacoraCollectionOldBitacora + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getRolId());
                usuario.setRol(rolNew);
            }
            Collection<Referimiento> attachedReferimientoCollectionNew = new ArrayList<Referimiento>();
            for (Referimiento referimientoCollectionNewReferimientoToAttach : referimientoCollectionNew) {
                referimientoCollectionNewReferimientoToAttach = em.getReference(referimientoCollectionNewReferimientoToAttach.getClass(), referimientoCollectionNewReferimientoToAttach.getRefId());
                attachedReferimientoCollectionNew.add(referimientoCollectionNewReferimientoToAttach);
            }
            referimientoCollectionNew = attachedReferimientoCollectionNew;
            usuario.setReferimientoCollection(referimientoCollectionNew);
            Collection<Bitacora> attachedBitacoraCollectionNew = new ArrayList<Bitacora>();
            for (Bitacora bitacoraCollectionNewBitacoraToAttach : bitacoraCollectionNew) {
                bitacoraCollectionNewBitacoraToAttach = em.getReference(bitacoraCollectionNewBitacoraToAttach.getClass(), bitacoraCollectionNewBitacoraToAttach.getBitId());
                attachedBitacoraCollectionNew.add(bitacoraCollectionNewBitacoraToAttach);
            }
            bitacoraCollectionNew = attachedBitacoraCollectionNew;
            usuario.setBitacoraCollection(bitacoraCollectionNew);
            usuario = em.merge(usuario);
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getUsuarioCollection().remove(usuario);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getUsuarioCollection().add(usuario);
                rolNew = em.merge(rolNew);
            }
            for (Referimiento referimientoCollectionNewReferimiento : referimientoCollectionNew) {
                if (!referimientoCollectionOld.contains(referimientoCollectionNewReferimiento)) {
                    Usuario oldUsuarioOfReferimientoCollectionNewReferimiento = referimientoCollectionNewReferimiento.getUsuario();
                    referimientoCollectionNewReferimiento.setUsuario(usuario);
                    referimientoCollectionNewReferimiento = em.merge(referimientoCollectionNewReferimiento);
                    if (oldUsuarioOfReferimientoCollectionNewReferimiento != null && !oldUsuarioOfReferimientoCollectionNewReferimiento.equals(usuario)) {
                        oldUsuarioOfReferimientoCollectionNewReferimiento.getReferimientoCollection().remove(referimientoCollectionNewReferimiento);
                        oldUsuarioOfReferimientoCollectionNewReferimiento = em.merge(oldUsuarioOfReferimientoCollectionNewReferimiento);
                    }
                }
            }
            for (Bitacora bitacoraCollectionNewBitacora : bitacoraCollectionNew) {
                if (!bitacoraCollectionOld.contains(bitacoraCollectionNewBitacora)) {
                    Usuario oldUsuarioOfBitacoraCollectionNewBitacora = bitacoraCollectionNewBitacora.getUsuario();
                    bitacoraCollectionNewBitacora.setUsuario(usuario);
                    bitacoraCollectionNewBitacora = em.merge(bitacoraCollectionNewBitacora);
                    if (oldUsuarioOfBitacoraCollectionNewBitacora != null && !oldUsuarioOfBitacoraCollectionNewBitacora.equals(usuario)) {
                        oldUsuarioOfBitacoraCollectionNewBitacora.getBitacoraCollection().remove(bitacoraCollectionNewBitacora);
                        oldUsuarioOfBitacoraCollectionNewBitacora = em.merge(oldUsuarioOfBitacoraCollectionNewBitacora);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getUsrId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
        if (entity instanceof Usuario) {
            Usuario usuario = (Usuario) entity;
            try {
                update(usuario);
                return (E) usuario;
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(UsuarioJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UsuarioJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(UsuarioJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsrId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Referimiento> referimientoCollectionOrphanCheck = usuario.getReferimientoCollection();
            for (Referimiento referimientoCollectionOrphanCheckReferimiento : referimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Referimiento " + referimientoCollectionOrphanCheckReferimiento + " in its referimientoCollection field has a non-nullable usuario field.");
            }
            Collection<Bitacora> bitacoraCollectionOrphanCheck = usuario.getBitacoraCollection();
            for (Bitacora bitacoraCollectionOrphanCheckBitacora : bitacoraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Bitacora " + bitacoraCollectionOrphanCheckBitacora + " in its bitacoraCollection field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol rol = usuario.getRol();
            if (rol != null) {
                rol.getUsuarioCollection().remove(usuario);
                rol = em.merge(rol);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Usuario) {
            try {
                remove(((Usuario) entity).getUsrId());
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(UsuarioJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UsuarioJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<Usuario> getUsuariosByRol(Rol rol) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT usr FROM Usuario usr WHERE usr.rol = :usrRol");
            q.setParameter("usrRol", rol);
            return (List<Usuario>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Usuario getUsuarioByLogin(String login) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Usuario.findByUsrLogin");
            q.setParameter("usrLogin", login);
            return (Usuario) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Usuario> getUsuariosByFechaCreacion(Date fechaCreacion) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Usuario.findByUsrFechaCreacion");
            q.setParameter("usrFechaCreacion", fechaCreacion);
            return (List<Usuario>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Usuario> getUsuariosByVerificado(char verificado) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Usuario.findByUsrVerificado");
            q.setParameter("usrVerificado", verificado);
            return (List<Usuario>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Usuario> getUsuarioByStatus(char status) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Usuario.findByUsrStatus");
            q.setParameter("usrStatus", status);
            return (List<Usuario>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
