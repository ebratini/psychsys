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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.business.exceptions.NonexistentEntityException;
import org.salvador_dali.psychsys.model.BitacoraDao;
import org.salvador_dali.psychsys.model.entities.Bitacora;
import org.salvador_dali.psychsys.model.entities.Usuario;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class BitacoraJpaDao extends JpaDao implements BitacoraDao {

    public BitacoraJpaDao() {
        super(Bitacora.class);
    }

    public BitacoraJpaDao(Map properties) {
        super(Bitacora.class, properties);
    }

    @Override
    public <E extends Serializable> void persist(E entity) {
        if (entity instanceof Bitacora) {
            Bitacora bitacora = (Bitacora) entity;
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                Usuario usuario = bitacora.getUsuario();
                if (usuario != null) {
                    usuario = em.getReference(usuario.getClass(), usuario.getUsrId());
                    bitacora.setUsuario(usuario);
                }
                em.persist(bitacora);
                if (usuario != null) {
                    usuario.getBitacoraCollection().add(bitacora);
                    usuario = em.merge(usuario);
                }
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }
    }
    
    public void update(Bitacora bitacora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bitacora persistentBitacora = em.find(Bitacora.class, bitacora.getBitId());
            Usuario usuarioOld = persistentBitacora.getUsuario();
            Usuario usuarioNew = bitacora.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsrId());
                bitacora.setUsuario(usuarioNew);
            }
            bitacora = em.merge(bitacora);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getBitacoraCollection().remove(bitacora);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getBitacoraCollection().add(bitacora);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bitacora.getBitId();
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The bitacora with id " + id + " no longer exists.");
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
        if (entity instanceof Bitacora) {
            Bitacora bitacora = (Bitacora) entity;
            try {
                update(bitacora);
                return (E) bitacora;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(BitacoraJpaDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (Exception ex) {
                Logger.getLogger(BitacoraJpaDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Bitacora bitacora;
            try {
                bitacora = em.getReference(Bitacora.class, id);
                bitacora.getBitId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bitacora with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = bitacora.getUsuario();
            if (usuario != null) {
                usuario.getBitacoraCollection().remove(bitacora);
                usuario = em.merge(usuario);
            }
            em.remove(bitacora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public <E extends Serializable> void remove(E entity) {
        if (entity instanceof Bitacora) {
            try {
                remove(((Bitacora) entity).getBitId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(BitacoraJpaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<Bitacora> getBitacorasByUsuario(Usuario usuario) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT bit FROM Bitacora bit WHERE bit.usuario = :bitUsuario");
            q.setParameter("bitUsuario", usuario);
            return (List<Bitacora>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Bitacora> getBitacorasByFecha(Date fecha) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Bitacora.findByBitFecha");
            q.setParameter("bitFecha", fecha);
            return (List<Bitacora>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Bitacora> getBitacorasByFuente(String fuente) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Bitacora.findByBitFuente");
            q.setParameter("bitFuente", fuente);
            return (List<Bitacora>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Bitacora> getBitacorasByCategoria(String categoria) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("Bitacora.findByBitCategoria");
            q.setParameter("bitCategoria", categoria);
            return (List<Bitacora>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
