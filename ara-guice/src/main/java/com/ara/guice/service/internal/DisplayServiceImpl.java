/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.service.internal;

import com.ara.guice.model.DisplayInfo;
import com.ara.guice.service.DisplayService;
import com.ara.guice.service.dal.DisplayDAO;
import com.google.inject.Inject;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 上午11:50
 */
public class DisplayServiceImpl implements DisplayService {

    @Inject
    private DisplayDAO displayDAO;

    @Override
    public DisplayInfo getInfo() {
        return displayDAO.getInfo();
    }
}
