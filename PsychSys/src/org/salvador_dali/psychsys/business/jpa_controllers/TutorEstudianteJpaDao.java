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
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.TutorEstudianteDao;
import org.salvador_dali.psychsys.model.entities.Estudiante;
import org.salvador_dali.psychsys.model.entities.Tutor;
import org.salvador_dali.psychsys.model.entities.TutorEstudiante;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class TutorEstudianteJpaDao extends JpaDao implements TutorEstudianteDao {

    public TutorEstudianteJpaDao() {
        super(TutorEstudiante.class);
    }

    public TutorEstudianteJpaDao(Map properties) {
        super(TutorEstudiante.class, properties);
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
    public List<TutorEstudiante> getTutorEstudianteByTutId(int tutId) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("TutorEstudiante.findByTutId");
            q.setParameter("tutId", tutId);
            return (List<TutorEstudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<TutorEstudiante> getTutorEstudianteByEstId(int estId) {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createNamedQuery("TutorEstudiante.findByEstId");
            q.setParameter("estId", estId);
            return (List<TutorEstudiante>) q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public TutorEstudiante getTutorEstudiante(Tutor tutor, Estudiante estudiante) {
        EntityManager entityManager = getEntityManager();
        try {
            String strQuery = "SELECT te FROM TutorEstudiante te WHERE te.tutor := tesTutor";
            strQuery += " AND te.estudiante := tesEstudiante";
            Query q = entityManager.createQuery(strQuery);
            q.setParameter("primerNombre", tutor);
            q.setParameter("primerApellido", estudiante);
            return (TutorEstudiante) q.getSingleResult();
        } finally {
            entityManager.close();
        }
    }
}
