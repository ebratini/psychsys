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
package org.salvador_dali.psychsys.model;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public abstract class JpaDao implements Dao {
    
    protected Class entityClass;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SyscafilPU");
    protected EntityManager entityManager = emf.createEntityManager();

    // TODO: resolver problemas con el ParameterizedType
    public JpaDao() {
        ParameterizedType genericSuperclass = ((ParameterizedType) getClass().getGenericSuperclass());
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
    }
    
    public JpaDao(Class entityClass, Map properties) {
        this(entityClass);
        emf = Persistence.createEntityManagerFactory("SyscafilPU", properties);
        entityManager = emf.createEntityManager(properties);
    }
    
    public JpaDao(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    @Override
    public <E> void persist(E entity) {
        entityManager.persist(entity);
    }
    
    @Override
    public List retrieve() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public <E> E update(E entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public <E> void remove(E entity) {
        entityManager.remove(entity);
    }
    
    @Override
    public <E, K> E findById(K id) {
        return (E) entityManager.find(entityClass, id);
    }
}
