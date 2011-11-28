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

import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.TutorDao;
import org.salvador_dali.psychsys.model.entities.Tutor;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class JpaTutorDao extends JpaDao implements TutorDao {

    public JpaTutorDao() {
        super(Tutor.class);
    }

    public JpaTutorDao(Map props) {
        super(Tutor.class, props);
    }

    @Override
    public Tutor getTutorByDNI(String dni) {
        try {
            Query q = entityManager.createNamedQuery("Tutor.findByTutDni");
            q.setParameter("tutDni", dni);
            return (Tutor) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List getTutoresByPrimerApellido(String primerApellido) {
        Query q = entityManager.createNamedQuery("Tutor.findByTutPrimerApellido");
        q.setParameter("tutPrimerApellido", primerApellido.toLowerCase());
        return q.getResultList();
    }

    @Override
    public List getTutoresByPrimerNombre(String primerNombre) {
        Query q = entityManager.createNamedQuery("Tutor.findByTutPrimerNombre");
        q.setParameter("tutPrimerNombre", primerNombre.toLowerCase());
        return q.getResultList();
    }

    @Override
    public List getTutoresByStatus(char status) {
        Query q = entityManager.createNamedQuery("Tutor.findByTutStatus");
        q.setParameter("tutStatus", status);
        return q.getResultList();
    }
}
