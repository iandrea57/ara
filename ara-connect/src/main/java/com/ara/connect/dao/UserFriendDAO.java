/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.dao;

import com.ara.connect.model.db.user.UserFriend;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:45
 */
@Repository
public class UserFriendDAO extends AbstractDAO<UserFriend> {

    private final Log logger = LogFactory.getLog(UserFriendDAO.class);

    public List<Long> getFriendIds(long userId) {
        try {
            String hql = "select friendId from UserFriend where userId = :userId";
            Query query = getSession().createQuery(hql);
            query.setParameter("userId", userId);
            return (List<Long>) query.list();
        } catch (Exception e) {
            logger.error("UserFriendDAO.getFriendIdList", e);
        }
        return null;
    }

    public void addFriend(UserFriend userFriend) {
        this.add(userFriend);
    }
}
