package com.ara.web.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午6:21
 */
public interface ICommon<T> {

    public void add(T entity) throws CommonException;

    public void update(T entity) throws CommonException;

    public void delete(Class theClass, long id) throws CommonException;

    public T get(Class theClass, long id) throws CommonException;

    public T getOneByProperty(Class theClass, final String property,
            final String value) throws CommonException;

    public List query(final String hql) throws CommonException;

    public List query(Class theClazz) throws CommonException;

    public Session getHibernateSession();
}
