/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.service.impl;

import com.ara.connect.dao.UserMessageDAO;
import com.ara.connect.model.db.user.UserMessage;
import com.ara.connect.model.user.UserInfo;
import com.ara.connect.model.user.UserMsgInfo;
import com.ara.connect.util.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午7:06
 */
@Service
public class MsgService {

    @Autowired
    private UserMessageDAO userMessageDAO;

    @Autowired
    private UserService userService;

    public void sendMsg(long fromId, long toId, String msg, Date addTime) {
        sendMsg(new UserMsgInfo(fromId, toId, msg, addTime));
    }

    public void sendMsg(UserMsgInfo userMsgInfo) {
        UserMessage msg = ConvertUtils.infoToMsg(userMsgInfo);
        if (msg != null) {
            userMessageDAO.saveMsg(msg);
        }
    }

    public List<UserMsgInfo> aquireMsg(long userId) {
        List<UserMessage> messageList = userMessageDAO.aquireMsg(userId);
        List<UserMsgInfo> infoList = ConvertUtils.infoFromMsg(messageList);
        addUserInfo(infoList);
        return infoList;
    }

    public List<UserMsgInfo> aquireMsg(long userId, Date since) {
        List<UserMessage> messageList = userMessageDAO.aquireMsg(userId, since);
        List<UserMsgInfo> infoList = ConvertUtils.infoFromMsg(messageList);
        addUserInfo(infoList);
        return infoList;
    }

    private void addUserInfo(List<UserMsgInfo> infoList) {
        if (CollectionUtils.isNotEmpty(infoList)) {
            List<Long> fromIdList = new ArrayList<Long> ();
            for (UserMsgInfo info : infoList) {
                fromIdList.add(info.getFromId());
            }
            Map<Long, UserInfo> userMap = userService.getUserMap(fromIdList);
            for (UserMsgInfo info : infoList) {
                UserInfo user = userMap.get(info.getFromId());
                if (user != null) {
                    info.setFromName(user.getUserName());
                }
            }
        }
    }

}
