/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.dao;

import org.springframework.dao.DataAccessException;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-2-28 下午3:36
 */
public class CommonException extends Exception {

    public CommonException(DataAccessException e) {
        super(e);
    }
}
