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
import java.util.Map;
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.CasoDao;
import org.salvador_dali.psychsys.model.JpaDao;
import org.salvador_dali.psychsys.model.entities.Caso;
import org.salvador_dali.psychsys.model.entities.Referimiento;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class JpaCasoDao extends JpaDao implements CasoDao {

    public JpaCasoDao() {
        super(Caso.class);
    }

    public JpaCasoDao(Map props) {
        super(Caso.class, props);
    }

    @Override
    public List getCasosByFecha(Date fecha) {
        Query q = entityManager.createNamedQuery("Estudiante.findByEstPrimerApellido");
        q.setParameter("estPrimerApellido", fecha);
        return q.getResultList();
    }

    @Override
    public List getCasosByAnioEscolar(String anioEscolar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Caso getCasoByReferimiento(Referimiento referimiento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getCasosByEstado(char estadoCaso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
