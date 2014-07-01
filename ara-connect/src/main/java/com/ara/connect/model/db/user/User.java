/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.model.db.user;

import javax.persistence.Entity;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 上午11:47
 */
@Entity
public class User {
    private long id;
    private String userName;
    private String password;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true,
            updatable = true, length = 19, precision = 0)
    @javax.persistence.Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "user_name", nullable = false,
            insertable = true, updatable = true, length = 128, precision = 0)
    @javax.persistence.Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @javax.persistence.Column(name = "password", nullable = false,
            insertable = true, updatable = true, length = 128, precision = 0)
    @javax.persistence.Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (password != null ? !password.equals(
                user.password) : user.password != null) return false;
        if (userName != null ? !userName.equals(
                user.userName) : user.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
