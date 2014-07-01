/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.model.db.user;

import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 上午11:48
 */
@javax.persistence.Table(name = "user_message", schema = "", catalog = "ara")
@Entity
public class UserMessage {
    private long id;
    private long fromId;
    private long toId;
    private String msg;
    private Timestamp addTime;
    private int status;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true,
            updatable = true, length = 19, precision = 0)
    @javax.persistence.Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "from_id", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @javax.persistence.Basic
    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    @javax.persistence.Column(name = "to_id", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @javax.persistence.Basic
    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    @javax.persistence.Column(name = "msg", nullable = false, insertable = true,
            updatable = true, length = 65535, precision = 0)
    @javax.persistence.Basic
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @javax.persistence.Column(name = "add_time", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @javax.persistence.Basic
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @javax.persistence.Column(name = "status", nullable = false,
            insertable = true, updatable = true, length = 10, precision = 0)
    @javax.persistence.Basic
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMessage message = (UserMessage) o;

        if (fromId != message.fromId) return false;
        if (id != message.id) return false;
        if (toId != message.toId) return false;
        if (status != message.status) return false;
        if (addTime != null ? !addTime.equals(
                message.addTime) : message.addTime != null) return false;
        if (msg != null ? !msg.equals(message.msg) : message.msg != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (fromId ^ (fromId >>> 32));
        result = 31 * result + (int) (toId ^ (toId >>> 32));
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        return result;
    }
}
