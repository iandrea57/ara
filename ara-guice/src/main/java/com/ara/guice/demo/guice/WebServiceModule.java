/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.demo.guice;

import com.ara.guice.intergration.GlobalKeys;
import com.ara.guice.service.intergration.guice.DisplayServiceDBModule;
import com.ara.guice.service.intergration.guice.DisplayServiceModule;
import com.ara.guice.util.MapConfig;
import com.google.inject.AbstractModule;

/**
 * web服务配置module
 *
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:45
 */
public class WebServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GlobalKeys.GLOBAL_CONF_KEY).toInstance(MapConfig.pasreConf("conf.properties"));
        install(new DisplayServiceDBModule());
        install(new DisplayServiceModule());

    }
}
