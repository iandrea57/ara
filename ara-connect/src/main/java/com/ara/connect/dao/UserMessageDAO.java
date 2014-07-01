/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.dao;

import com.ara.connect.model.db.user.UserMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午7:26
 */
@Repository
public class UserMessageDAO extends AbstractDAO<UserMessage> {

    private final Log logger = LogFactory.getLog(UserMessageDAO.class);

    public List<UserMessage> aquireMsg(long userId) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserMessage.class);
            criteria.add(Restrictions.eq("toId", userId));
            criteria.add(Restrictions.eq("status", 0));
            criteria.addOrder(Order.asc("addTime"));
            List<UserMessage> result = criteria.list();
            List<Long> idList = new ArrayList<Long> ();
            for (UserMessage msg : result) {
                idList.add(msg.getId());
            }
            if (CollectionUtils.isNotEmpty(idList)) {
                String hql = "update UserMessage set status = 1 where id in (:idList)";
                Query query = session.createQuery(hql);
                query.setParameterList("idList", idList);
                query.executeUpdate();
            }
            transaction.commit();
            return result;
        } catch (Exception e) {
            logger.error("UserMessageDAO.aquireMsg", e);
        }
        return new ArrayList<UserMessage>();
    }
    public List<UserMessage> aquireMsg(long userId, Date since) {
        try {
            Criteria criteria = getSession().createCriteria(UserMessage.class);
            criteria.add(Restrictions.ge("addTime", since));
            criteria.add(Restrictions.eq("toId", userId));
            criteria.addOrder(Order.desc("addTime"));
            return (List<UserMessage>) criteria.list();
        } catch (Exception e) {
            logger.error("UserMessageDAO.aquireMsg", e);
        }
        return new ArrayList<UserMessage>();
    }

    public void saveMsg(UserMessage msg) {
        try {
//            add(msg);
            Session session = getSession();
            Transaction transaction= session.beginTransaction();
            session.save(msg);
            transaction.commit();
        } catch (Exception e) {
            logger.error("UserMessageDAO.saveMsg", e);
        }
    }
}
