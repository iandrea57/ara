package com.ara.connect.service;

import com.ara.connect.model.user.UserInfo;
import com.ara.connect.model.user.UserMsgInfo;

import java.util.List;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:25
 */
public interface IClient {

    public UserInfo login(String userName, String password);

    public void logout(long userId);

    public List<UserInfo> getFriends(long userId);

    public List<UserInfo> getOnlineFriends(long userId);

    public void sendMsg(long fromId, long toId, String msg);

    public List<UserMsgInfo> aquireMsg(long userId);

    public List<UserMsgInfo> aquireMsg(long userId, long since);

}
