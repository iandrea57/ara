/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.model.user;

import java.util.Date;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:08
 */
public class UserMsgInfo {

    /** 消息id */
    private long id;

    /** 消息发送方id */
    private long fromId;

    /** 消息发送方姓名 */
    private String fromName;

    /** 消息接收方id */
    private long toId;

    /** 消息内容 */
    private String msg;

    /** 消息添加时间 */
    private Date addTime;

    /** 消息状态标志 **/
    private int status;

    public UserMsgInfo() { }

    public UserMsgInfo(long fromId, long toId, String msg, Date addTime) {
        this.fromId = fromId;
        this.toId = toId;
        this.msg = msg;
        this.addTime = addTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserMsgInfo{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", toId=" + toId +
                ", msg='" + msg + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
