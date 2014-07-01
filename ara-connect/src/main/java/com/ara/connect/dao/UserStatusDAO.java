/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.dao;

import com.ara.connect.model.db.user.UserStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:38
 */
@Repository
public class UserStatusDAO extends AbstractDAO<UserStatus> {

    private final Log logger = LogFactory.getLog(UserStatusDAO.class);

    public void login(long userId) {
        UserStatus status = new UserStatus();
        status.setUserId(userId);
        status.setStatus((byte) 1);
        try {
            Session session = getSession();
            Transaction transaction= session.beginTransaction();
            session.saveOrUpdate(status);
            transaction.commit();
//            add(status);
        } catch (Exception e) {
            logger.error("UserStatusDAO.login", e);
        }
    }

    public UserStatus logout(long userId) {
        UserStatus status = new UserStatus();
        status.setUserId(userId);
        status.setStatus((byte) 0);
        try {
            Session session = getSession();
            Transaction transaction= session.beginTransaction();
            session.saveOrUpdate(status);
            transaction.commit();
//            add(status);
        } catch (Exception e) {
            logger.error("UserStatusDAO.logout", e);
        }
        return status;
    }

    public List<Long> getOnlineIds(List<Long> userIdList) {
        try {
            String hql = "select userId from UserStatus where userId in (:userIdList) and status = 1";
            Query query = getSession().createQuery(hql);
            query.setParameterList("userIdList", userIdList);
            return (List<Long>) query.list();
        } catch (Exception e) {
            logger.error("UserStatusDAO.getOnlineIdList", e);
        }
        return new ArrayList<Long>();
    }
}
