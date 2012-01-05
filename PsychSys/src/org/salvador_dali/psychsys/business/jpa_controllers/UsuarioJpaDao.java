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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.UsuarioDao;
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
        super.persist(entity);
    }

    @Override
    public <E extends Serializable> E update(E entity) {
        return super.update(entity);
    }

    @Override
    public <E extends Serializable> void remove(E entity) {
        super.remove(entity);
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
