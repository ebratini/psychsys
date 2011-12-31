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
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.ReferimientoDao;
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
    public void crearReferimiento(Referimiento referimiento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List getReferimientosByFecha(Date fecha) {
        Query q = entityManager.createNamedQuery("Referimiento.findByRefFecha");
        q.setParameter("refFecha", fecha);
        return (List<Referimiento>) q.getResultList();
    }

    @Override
    public List getReferimientosByAnioEscolar(String anioEscolar) {
        Query q = entityManager.createNamedQuery("Referimiento.findByRefAnioEscolar");
        q.setParameter("refAnioEscolar", anioEscolar);
        return (List<Referimiento>) q.getResultList();
    }

    @Override
    public List getReferimientosByEstudiante(Estudiante estudiante) {
        Query q = entityManager.createQuery("SELECT r FROM Referimiento r WHERE r.estudiante = :refEstudiante");
        q.setParameter("refEstudiante", estudiante);
        return (List<Referimiento>) q.getResultList();
    }

    @Override
    public List getReferimientosByReferidor(String referidor) {
        Query q = entityManager.createNamedQuery("Referimiento.findByRefNombreReferidor");
        q.setParameter("refNombreReferidor", referidor);
        return (List<Referimiento>) q.getResultList();
    }
    
    @Override
    public List getReferimientosByUsuario(Usuario usuario) {
        Query q = entityManager.createQuery("SELECT r FROM Referimiento r WHERE r.usuario = :refUsuario");
        q.setParameter("refUsuario", usuario);
        return (List<Referimiento>) q.getResultList();
    }

    @Override
    public List getReferimientosByEstadoReferimiento(char status) {
        Query q = entityManager.createNamedQuery("Referimiento.findByRefEstadoReferimiento");
        q.setParameter("refEstadoReferimiento", status);
        return (List<Referimiento>) q.getResultList();
    }
}
