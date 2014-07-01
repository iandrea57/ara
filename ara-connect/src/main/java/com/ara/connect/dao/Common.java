/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午3:33
 */
public class Common<T> extends HibernateDaoSupport implements ICommon<T> {

    @Override
    public void insertObject(T entity) throws CommonException {
        try {
            this.getHibernateTemplate().save(entity);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CommonException(e);
        }
    }

    @Override
    public void updateObject(T entity) throws CommonException {
        try {
            this.getHibernateTemplate().update(entity);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CommonException(e);
        }
    }

    @Override
    public void deleteObject(Class theClass, long id) throws CommonException {
        try {
            Object obj = this.getHibernateTemplate().load(theClass, id);
            this.getHibernateTemplate().delete(obj);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CommonException(e);
        }
    }

    @Override
    public T loadObject(Class theClass, long id) throws CommonException {
        try {
            Object obj = this.getHibernateTemplate().load(theClass, id);
            return (T) obj;
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CommonException(e);
        }
    }

    @Override
    public List queryObjects(final String hql) throws CommonException {
        class Hc implements HibernateCallback {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                return session.createQuery(hql).list();
            }
        }

        try {
            return this.getHibernateTemplate().executeFind(new Hc());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CommonException(e);
        }
    }

    @Override
    public List queryObjects(Class theClazz) throws CommonException {
        return this.queryObjects("from " + theClazz.getSimpleName());
    }

    @Override
    public Session getHibernateSession() {
//        return getHibernateTemplate().getSessionFactory().getCurrentSession();
        return getHibernateTemplate().getSessionFactory().openSession();
    }
}
