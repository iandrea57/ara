/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.model.mvc;

import java.util.Date;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-3 下午6:00
 */
public class UserComment {

    private long userId;

    private String userName;

    private int topicId;

    private String comment;

    private Date addTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", topicId=" + topicId +
                ", comment='" + comment + '\'' +
                ", addTime=" + addTime +
                "} " + super.toString();
    }
}
