/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
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
            common.add(entity);
        } catch (Exception e) {
            logger.error("add", e);
        }
    }

    public void update(T entity) {
        try {
            common.update(entity);
        } catch (Exception e) {
            logger.error("update", e);
        }
    }

    public void delete(T entity, long id) {
        try {
            common.delete(entity.getClass(), id);
        } catch (Exception e) {
            logger.error("delete", e);
        }
    }

    public List<T> getListByHQL(String hql) {
        try {
            return common.query(hql);
        } catch (Exception e) {
            logger.error("getListByHQL", e);
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

    public T get(long id) {
        try {
            return common.get(
                    (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0],
                    id);
        } catch (CommonException e) {
            logger.error("get", e);
        }
        return null;
    }

    public T getOneByProperty(String property, String value) {
        try {
            return common.getOneByProperty(
                    (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0],
                    property, value);
        } catch (Exception e) {
            logger.error("getOneByProperty", e);
        }
        return null;
    }
}
