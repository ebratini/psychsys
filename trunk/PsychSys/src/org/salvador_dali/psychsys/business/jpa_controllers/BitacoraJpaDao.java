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
import javax.persistence.Query;
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
