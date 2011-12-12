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

import java.io.Serializable;
import java.util.ArrayList;
import org.salvador_dali.psychsys.model.JpaDao;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public class EntityEditor {

    private JpaDao jpaDao;
    private ArrayList<Serializable> nuevos = new ArrayList<Serializable>();
    private ArrayList<Serializable> editados = new ArrayList<Serializable>();
    private ArrayList<Serializable> elimados = new ArrayList<Serializable>();

    public EntityEditor() {
    }

    public EntityEditor(JpaDao jpaDao) {
        this.jpaDao = jpaDao;
    }

    public ArrayList<Serializable> getEditados() {
        return editados;
    }

    public void setEditados(ArrayList<Serializable> editados) {
        this.editados = editados;
    }

    public ArrayList<Serializable> getElimados() {
        return elimados;
    }

    public void setElimados(ArrayList<Serializable> elimados) {
        this.elimados = elimados;
    }

    public ArrayList<Serializable> getNuevos() {
        return nuevos;
    }

    public void setNuevos(ArrayList<Serializable> nuevos) {
        this.nuevos = nuevos;
    }

    public JpaDao getJpaDao() {
        return jpaDao;
    }

    public void setJpaDao(JpaDao jpaDao) {
        this.jpaDao = jpaDao;
    }

    public void agregarNuevo(Serializable entity) {
        this.nuevos.add(entity);
    }

    public void agregarEditado(Serializable entity) {
        this.editados.add(entity);
    }

    public void agregarEliminado(Serializable entity) {
        this.elimados.add(entity);
    }

    public void doDMLAction() {
        for (Serializable entity : nuevos) {
            jpaDao.persist(entity);
        }
        for (Serializable entity : editados) {
            jpaDao.update(entity);
        }
        for (Serializable entity : elimados) {
            jpaDao.remove(entity);
        }
    }
}
