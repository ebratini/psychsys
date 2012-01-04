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

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.CasoDao;
import org.salvador_dali.psychsys.model.entities.Caso;
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
    public void crearCaso(Caso caso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getCasosByFecha(Date fecha) {
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
    public List getCasosByAnioEscolar(String anioEscolar) {
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
    public List getCasosByEstado(char estadoCaso) {
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
