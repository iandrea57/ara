/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.intergration;

import com.ara.guice.service.dal.DisplayDAO;
import com.google.inject.Provider;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:23
 */
public class DisplayDaoProvider implements Provider<DisplayDAO> {

    @Override
    public DisplayDAO get() {
        return new DisplayDAO();
    }
}
