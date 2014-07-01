/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.service.impl;

import com.ara.connect.dao.UserDAO;
import com.ara.connect.dao.UserFriendDAO;
import com.ara.connect.dao.UserStatusDAO;
import com.ara.connect.model.db.user.User;
import com.ara.connect.model.db.user.UserStatus;
import com.ara.connect.model.user.UserInfo;
import com.ara.connect.util.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午4:02
 */
@Service
public class UserService {

    private final Log logger = LogFactory.getLog(UserService.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserStatusDAO userStatusDAO;

    @Autowired
    private UserFriendDAO userFriendDAO;

    public UserInfo login(String userName, String password) {
        User user = null;
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            user = userDAO.getByUserName(StringUtils.trimToEmpty(userName));
            if (user != null) {
                if (!password.equals(user.getPassword())) {
                    user = null;
                }
            }
        }
        if (user != null) {
            userStatusDAO.login(user.getId());
        }
        return ConvertUtils.infoFromUser(user);
    }

    public void logout(long userId) {
        UserStatus userStatus = userStatusDAO.logout(userId);
        if (userStatus != null) {
            logger.error(" user logout !!  -->  " + userStatus.getUserId());
        }
    }

    public List<UserInfo> getOnlineFriends(long userId) {
        List<Long> friendIdList = userFriendDAO.getFriendIds(userId);
        List<Long> idList = userStatusDAO.getOnlineIds(friendIdList);
        return getUsers(idList);
    }

    public List<UserInfo> getFriends(long userId) {
        List<Long> friendIdList = userFriendDAO.getFriendIds(userId);
        return getUsers(friendIdList);
    }

    public Map<Long, UserInfo> getUserMap(List<Long> userIdList) {
        Map<Long, UserInfo> userMap = new HashMap<Long, UserInfo>();
        List<UserInfo> userInfoList = getUsers(userIdList);
        for (UserInfo info : userInfoList) {
            userMap.put(info.getUserId(), info);
        }
        return userMap;
    }

    public List<UserInfo> getUsers(List<Long> userIdList) {
        List<User> userList = userDAO.getUsers(userIdList);
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        for (User user : userList) {
            UserInfo userInfo = ConvertUtils.infoFromUser(user);
            if (userInfo != null) {
                userInfoList.add(userInfo);
            }
        }
        return userInfoList;
    }


}
