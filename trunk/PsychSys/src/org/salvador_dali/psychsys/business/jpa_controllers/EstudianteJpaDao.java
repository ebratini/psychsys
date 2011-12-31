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

import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.EstudianteDao;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.Tutor;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class EstudianteJpaDao extends JpaDao implements EstudianteDao {

    public EstudianteJpaDao() {
        super(Estudiante.class);
    }

    public EstudianteJpaDao(Map props) {
        super(Estudiante.class, props);
    }

    @Override
    public void crearEstudiante(Estudiante estudiante, List<Tutor> tutores) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Estudiante getEstudianteByDNI(String dni) {
        try {
            Query q = entityManager.createNamedQuery("Estudiante.findByEstDni");
            q.setParameter("estDni", dni);
            return (Estudiante) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List getEstudiantesByPrimerApellido(String primerApellido) {
        Query q = entityManager.createNamedQuery("Estudiante.findByEstPrimerApellido");
        q.setParameter("estPrimerApellido", primerApellido.toLowerCase());
        return (List<Estudiante>) q.getResultList();
    }

    @Override
    public List getEstudiantesByPrimerNombre(String primerNombre) {
        Query q = entityManager.createNamedQuery("Estudiante.findByEstPrimerNombre");
        q.setParameter("estPrimerNombre", primerNombre.toLowerCase());
        return (List<Estudiante>) q.getResultList();
    }

    @Override
    public List getEstudiantesByStatus(char status) {
        Query q = entityManager.createNamedQuery("Estudiante.findByEstStatus");
        q.setParameter("estStatus", status);
        return (List<Estudiante>) q.getResultList();
    }

    @Override
    public List getEstudiantesByNombreCompleto(String primerNombre, String primerApellido) {
        String strQuery = "SELECT e FROM Estudiante e WHERE lower(e.estPrimerNombre) = lower(:primerNombre)";
        strQuery += " AND lower(e.estPrimerApellido) = lower(:primerApellido)";
        
        Query q = entityManager.createQuery(strQuery);
        q.setParameter("primerNombre", primerNombre.toLowerCase());
        q.setParameter("primerApellido", primerApellido.toLowerCase());
        return (List<Estudiante>) q.getResultList();
    }
}
