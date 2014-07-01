/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.intergration;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.multibindings.Multibinder;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 下午12:08
 */
public abstract class ServiceModule extends AbstractModule {

    protected <T> void bindService(Class<T> serviceInterface, Class<? extends T> serviceImpl) {
        bind(serviceInterface).to(serviceImpl).asEagerSingleton();
        Key<T> key = Key.get(serviceInterface);

        Multibinder<ServiceEntry> multibinder = Multibinder.newSetBinder(binder(), ServiceEntry.class);
        multibinder.addBinding().toInstance(new ServiceEntry(serviceInterface.getSimpleName(), serviceInterface.getName(), key, null));
    }

}
