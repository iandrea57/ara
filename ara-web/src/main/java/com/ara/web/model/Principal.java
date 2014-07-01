/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.model;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午4:25
 */
public class Principal {

    private long userId;

    private String account;

    public Principal() {}

    public Principal(long userId, String account) {
        this.userId = userId;
        this.account = account;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                "} " + super.toString();
    }
}
