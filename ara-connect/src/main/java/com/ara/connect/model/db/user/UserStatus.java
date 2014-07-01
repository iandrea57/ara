/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.model.db.user;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午12:37
 */
@javax.persistence.Table(name = "user_status", schema = "", catalog = "ara")
@Entity
public class UserStatus {
    private long userId;
    private byte status;

    @javax.persistence.Column(name = "user_id", nullable = false,
            insertable = true, updatable = true, length = 19, precision = 0)
    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @javax.persistence.Column(name = "status", nullable = false,
            insertable = true, updatable = true, length = 3, precision = 0)
    @Basic
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatus that = (UserStatus) o;

        if (status != that.status) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) status;
        return result;
    }
}
