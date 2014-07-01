/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.model.db.user;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:43
 */
@javax.persistence.Table(name = "user_friend", schema = "", catalog = "ara")
@Entity
public class UserFriend {
    private long id;
    private long userId;
    private long friendId;
    private Timestamp addTime;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true,
            updatable = true, length = 19, precision = 0)
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "user_id", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @javax.persistence.Column(name = "friend_id", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    @javax.persistence.Column(name = "add_time", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFriend that = (UserFriend) o;

        if (friendId != that.friendId) return false;
        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (addTime != null ? !addTime.equals(
                that.addTime) : that.addTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (friendId ^ (friendId >>> 32));
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        return result;
    }
}
