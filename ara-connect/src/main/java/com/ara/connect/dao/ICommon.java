package com.ara.connect.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午6:21
 */
public interface ICommon<T> {

    public void insertObject(T entity) throws CommonException;

    public void updateObject(T entity) throws CommonException;

    public void deleteObject(Class theClass, long id) throws CommonException;

    public T loadObject(Class theClass, long id) throws CommonException;

    public List queryObjects(final String hql) throws CommonException;

    public List queryObjects(Class theClazz) throws CommonException;

    public Session getHibernateSession();
}
