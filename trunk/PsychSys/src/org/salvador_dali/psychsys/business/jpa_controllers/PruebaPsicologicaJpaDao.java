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
import javax.persistence.Query;
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

    public PruebaPsicologicaJpaDao(Class entityClass) {
        super(PruebaPsicologica.class);
    }

    public PruebaPsicologicaJpaDao(Class entityClass, Map properties) {
        super(PruebaPsicologica.class, properties);
    }
    
    @Override
    public void crearPruebaPsicologica(PruebaPsicologica pruebaPsicologica) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void crearPruebaPsicologica(PruebaPsicologica pruebaPsicologica, List<UbicacionPrueba> ubicacionesPrueba) {
        throw new UnsupportedOperationException("Not supported yet.");
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
