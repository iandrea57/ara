/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午7:23
 */
public enum ErrorEnum {

    NO_ERROR(0, "无错误"),

    AUTH_PASSWORD_WRONG(101, "密码错误"),

    AUTH_ACCOUNT_NOT_EXIST(102, "用户名不存在"),

    ;

    private final int index;

    private String description;

    private static final Map<Integer, ErrorEnum> INDEXES = toIndexes(ErrorEnum.values());

    ErrorEnum(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    private static Map<Integer, ErrorEnum> toIndexes(ErrorEnum[] enums) {
        Map<Integer, ErrorEnum> instances = new ConcurrentHashMap<Integer, ErrorEnum>();
        for (ErrorEnum enm : enums) {
            instances.put(enm.getIndex(), enm);
        }
        return instances;
    }

    public static ErrorEnum indexOf(final int index) {
        return INDEXES.get(index);
    }
}
