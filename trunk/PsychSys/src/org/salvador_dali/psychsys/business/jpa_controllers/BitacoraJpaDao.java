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
import javax.persistence.Query;
import org.salvador_dali.psychsys.model.BitacoraDao;
import org.salvador_dali.psychsys.model.entities.Usuario;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class BitacoraJpaDao extends JpaDao implements BitacoraDao {

    @Override
    public List getBitacorasByUsuario(Usuario usuario) {
        Query q = entityManager.createNamedQuery("Bitacora.findByUsuario");
        q.setParameter("bitUsuario", usuario);
        return q.getResultList();
    }

    @Override
    public List getBitacorasByFecha(Date fecha) {
        Query q = entityManager.createNamedQuery("Bitacora.findByBitFecha");
        q.setParameter("bitFecha", fecha);
        return q.getResultList();
    }

    @Override
    public List getBitacorasByFuente(String fuente) {
        Query q = entityManager.createNamedQuery("Bitacora.findByBitFuente");
        q.setParameter("bitFuente", fuente);
        return q.getResultList();
    }

    @Override
    public List getBitacorasByCategoria(String categoria) {
        Query q = entityManager.createNamedQuery("Bitacora.findByBitCategoria");
        q.setParameter("bitCategoria", categoria);
        return q.getResultList();
    }
}
