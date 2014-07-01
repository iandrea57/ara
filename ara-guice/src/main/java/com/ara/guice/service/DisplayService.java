/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.service;

import com.ara.guice.model.DisplayInfo;

/**
 * 展示信息服务
 *
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:17
 */
public interface DisplayService {

    /**
     * 获取展示信息
     *
     * @return 展示信息
     */
    public DisplayInfo getInfo();

}
