/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.connect.model.api;

import java.util.Map;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-1 下午4:30
 */
public class AraResponse {

    private int type;

    private Map<String, String> result;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AraResponse{" +
                "type=" + type +
                ", result=" + result +
                '}';
    }
}
