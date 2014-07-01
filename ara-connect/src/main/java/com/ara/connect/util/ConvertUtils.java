/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.util;

import com.ara.connect.model.db.user.User;
import com.ara.connect.model.db.user.UserMessage;
import com.ara.connect.model.user.UserInfo;
import com.ara.connect.model.user.UserMsgInfo;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:14
 */
public class ConvertUtils {


    public static List<UserMsgInfo> infoFromMsg(List<UserMessage> messageList) {
        List<UserMsgInfo> infoList = new ArrayList<UserMsgInfo>();
        if (CollectionUtils.isNotEmpty(messageList)) {
            for (UserMessage msg : messageList) {
                UserMsgInfo info = new UserMsgInfo();
                info.setId(msg.getId());
                info.setFromId(msg.getFromId());
                info.setToId(msg.getToId());
                info.setMsg(msg.getMsg());
                info.setAddTime(msg.getAddTime());
                info.setStatus(msg.getStatus());
                infoList.add(info);
            }
        }
        return infoList;
    }

    public static UserMessage infoToMsg(UserMsgInfo info) {
        UserMessage msg = null;
        if (info != null) {
            msg = new UserMessage();
            msg.setId(info.getId());
            msg.setFromId(info.getFromId());
            msg.setToId(info.getToId());
            msg.setMsg(info.getMsg());
            if (info.getAddTime() != null) {
                msg.setAddTime(new Timestamp(info.getAddTime().getTime()));
            }
            msg.setStatus(info.getStatus());
        }
        return msg;
    }

    public static UserInfo infoFromUser(User user) {
        UserInfo info = null;
        if (user != null) {
            info = new UserInfo();
            info.setUserId(user.getId());
            info.setUserName(user.getUserName());
        }
        return info;
    }
}
