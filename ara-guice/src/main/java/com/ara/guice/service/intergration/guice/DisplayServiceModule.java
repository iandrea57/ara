/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.service.intergration.guice;

import com.ara.guice.intergration.ServiceModule;
import com.ara.guice.service.DisplayService;
import com.ara.guice.service.internal.DisplayServiceImpl;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 下午12:04
 */
public class DisplayServiceModule extends ServiceModule {

    @Override
    protected void configure() {
        bindService(DisplayService.class, DisplayServiceImpl.class);
    }
}
