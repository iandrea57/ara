/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午3:45
 */
@Repository
public abstract class AbstractDAO<T> {

    private final Log logger = LogFactory.getLog(AbstractDAO.class);

    @Autowired
    private ICommon<T> common;

    public ICommon<T> getCommon() {
        return common;
    }

    public void setCommon(ICommon<T> common) {
        this.common = common;
    }

    public Session getSession() {
        return common.getHibernateSession();
    }

    public void add(T entity) {
        try {
            common.insertObject(entity);
        } catch (Exception e) {
            logger.error("AbstractDAO.add", e);
        }
    }

    public void update(T entity) {
        try {
            common.updateObject(entity);
        } catch (Exception e) {
            logger.error("AbstractDAO.update", e);
        }
    }

    public void delete(T entity, long id) {
        try {
            common.deleteObject(entity.getClass(), id);
        } catch (Exception e) {
            logger.error("AbstractDAO.delete", e);
        }
    }

    public List<T> getListByHQL(String hql) {
        try {
            return common.queryObjects(hql);
        } catch (Exception e) {
            logger.error("AbstractDAO.getListByHQL", e);
        }
        return new ArrayList<T>();
    }

    public T getByHQL(String hql) {
        List<T> list = getListByHQL(hql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
