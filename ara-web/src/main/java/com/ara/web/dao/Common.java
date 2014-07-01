/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * @author <a href="mailto:iiiandrea57@gmail.com">i云涯</a>
 * @createTime 14-3-28 下午7:21
 */
public class Common<T> extends HibernateDaoSupport implements ICommon<T> {
    
    private final Log logger = LogFactory.getLog(Common.class);

    @Override
    public void add(T entity) throws CommonException {
        try {
            this.getHibernateTemplate().save(entity);
        } catch (DataAccessException e) {
            logger.error("add operation error", e);
            throw new CommonException(e);
        }
    }

    @Override
    public void update(T entity) throws CommonException {
        try {
            this.getHibernateTemplate().update(entity);
        } catch (DataAccessException e) {
            logger.error("update operation error", e);
            throw new CommonException(e);
        }
    }

    @Override
    public void delete(Class theClass, long id) throws CommonException {
        try {
            Object obj = this.getHibernateTemplate().load(theClass, id);
            this.getHibernateTemplate().delete(obj);
        } catch (DataAccessException e) {
            logger.error("delete operation error", e);
            throw new CommonException(e);
        }
    }

    @Override
    public T get(Class theClass, long id) throws CommonException {
        try {
            Object obj = this.getHibernateTemplate().get(theClass, id);
            return (T) obj;
        } catch (DataAccessException e) {
            logger.error("get operation error", e);
            throw new CommonException(e);
        }
    }

    @Override
    public T getOneByProperty(Class theClass, final String property,
            final String value) throws CommonException {
        final String table = theClass.getSimpleName();
        class Hc implements HibernateCallback {
            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                String hql = "from " + table + " where " + property + " = :property";
                Query query = session.createQuery(hql);
                query.setParameter("property", value);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        }
        try {
            return (T) this.getHibernateTemplate().execute(new Hc());
        } catch (DataAccessException e) {
            logger.error("getOneByProperty operation error", e);
            throw new CommonException(e);
        }
    }

    @Override
    public List query(final String hql) throws CommonException {
        class Hc implements HibernateCallback {
            @Override
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                return session.createQuery(hql).list();
            }
        }
        try {
            return this.getHibernateTemplate().executeFind(new Hc());
        } catch (DataAccessException e) {
            logger.error("query operation error", e);
            throw new CommonException(e);
        }
    }

    @Override
    public List query(Class theClazz) throws CommonException {
        return this.query("from " + theClazz.getSimpleName());
    }

    @Override
    public Session getHibernateSession() {
//        return getHibernateTemplate().getSessionFactory().openSession();
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
}
