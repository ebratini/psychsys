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
package org.salvador_dali.psychsys.business;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.PruebaPsicologicaDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Estudiante;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class JpaPruebaPsicologicaDao extends JpaDao implements PruebaPsicologicaDao {

    @Override
    public List getPruebasPsicologicasByEstudiante(Estudiante estudiante) {
        Query q = entityManager.createQuery("SELECT pps FROM PruebaPsicologica pps WHERE pps.estudiante = :ppsEstudiante");
        q.setParameter("ppsEstudiante", estudiante);
        return q.getResultList();
    }

    @Override
    public List getPruebasPsicologicasByCaso(Caso caso) {
        Query q = entityManager.createQuery("SELECT pps FROM PruebaPsicologica pps WHERE pps.caso = :ppsCaso");
        q.setParameter("ppsCaso", caso);
        return q.getResultList();
    }

    @Override
    public List getPruebasPsicologicasByFechaAplicacion(Date fechaAplicacion) {
        Query q = entityManager.createNamedQuery("PruebaPsicologica.findByPpsFechaAplicacion");
        q.setParameter("ppsFechaAplicacion", fechaAplicacion);
        return q.getResultList();
    }

    @Override
    public List getPruebasPsicologicasByNombrePrueba(String nombrePrueba) {
        Query q = entityManager.createNamedQuery("PruebaPsicologica.findByPpsNombrePrueba");
        q.setParameter("ppsNombrePrueba", nombrePrueba);
        return q.getResultList();
    }

    @Override
    public List getPruebasPsicologicasByCorreccionAutomatica(char correcionAutomatica) {
        Query q = entityManager.createNamedQuery("PruebaPsicologica.findByPpsCorrecionAutomatica");
        q.setParameter("ppsCorrecionAutomatica", correcionAutomatica);
        return q.getResultList();
    }
}
