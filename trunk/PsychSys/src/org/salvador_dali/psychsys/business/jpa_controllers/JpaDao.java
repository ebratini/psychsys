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
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.salvador_dali.psychsys.model.Dao;

/**
 *
 * @author Edwin Bratini <edwin.bratini@gmail.com>
 */
public abstract class JpaDao implements Dao {
    public static final String PERSISTENCE_UNIT_NAME = "PsychSysPU";
    protected Class entityClass;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    // TODO: resolver problemas con el ParameterizedType
    public JpaDao() {
        //ParameterizedType genericSuperclass = (ParameterizedType) (getClass().getGenericSuperclass());
        //this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }
    
    public JpaDao(Class entityClass, Map properties) {
        this(entityClass);
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
    }
    
    public JpaDao(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public <E extends Serializable> void persist(E entity) {
        EntityManager entityManager = getEntityManager();
        try {
            EntityTransaction et = entityManager.getTransaction();
            et.begin();
            entityManager.persist(entity);
            et.commit();
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public List retrieve() {
        EntityManager entityManager = getEntityManager();
        try {
            Query q = entityManager.createQuery(String.format("SELECT e FROM %s e", entityClass.getSimpleName()));
            return q.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public <E extends Serializable> E update(E entity) {
        EntityManager entityManager = getEntityManager();
        try {
            E updated = null;
            EntityTransaction et = entityManager.getTransaction();
            et.begin();
            updated = entityManager.merge(entity);
            et.commit();
            return updated;
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public <E extends Serializable> void remove(E entity) {
        EntityManager entityManager = getEntityManager();
        try {
            EntityTransaction et = entityManager.getTransaction();
            et.begin();
            entityManager.remove(entity);
            et.commit();
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public <E extends Serializable, K> E findById(K id) {
        EntityManager entityManager = getEntityManager();
        try {
            return (E) entityManager.find(entityClass, id);
        } finally {
            entityManager.close();
        }
    }
    
    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
    
    public List findEntities() {
        return findEntities(true, -1, -1);
    }

    public List findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    private List findEntities(boolean all, int maxResults, int firstResult) {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = entityManager.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    public int getEntityCount() {
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            Root rt = cq.from(entityClass);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            entityManager.close();
        }
    }
}
