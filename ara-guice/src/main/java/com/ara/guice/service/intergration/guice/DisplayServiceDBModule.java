/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.service.intergration.guice;

import com.ara.guice.intergration.DisplayDaoProvider;
import com.ara.guice.model.DisplayInfo;
import com.ara.guice.service.dal.DisplayDAO;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 上午11:41
 */
public class DisplayServiceDBModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Class> entityShardMultibinder = Multibinder.newSetBinder(this.binder(), Class.class,
                Names.named("DAL.ENTITY.CLASS.SET"));
        entityShardMultibinder.addBinding().toInstance(DisplayInfo.class);
        this.binder().bind(DisplayDAO.class).toProvider(DisplayDaoProvider.class).asEagerSingleton();
    }
}
