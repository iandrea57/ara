/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.dao;

import com.ara.connect.model.db.user.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午3:46
 */
@Repository
public class UserDAO extends AbstractDAO<User> {

    private final Log logger = LogFactory.getLog(UserDAO.class);

    public User getByUserName(String userName) {
        try {
            String hql = "from User where userName = :userName";
            Query query = getSession().createQuery(hql);
            query.setParameter("userName", userName);
            query.setMaxResults(1);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            logger.error("UserDAO.getByUserName", e);
        }
        return null;
    }

    public List<User> getUsers(List<Long> idList) {
        try {
            String hql = "from User where id in (:idList) ";
            Query query = getSession().createQuery(hql);
            query.setParameterList("idList", idList);
            return (List<User>) query.list();
        } catch (Exception e) {

        }
        return new ArrayList<User>();
    }
}
