/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.test.guice.demo;

import com.ara.test.guice.interceptor.LoggerMethodInterceptor;
import com.ara.test.guice.service.IService;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午4:04
 */
public class AopDemo {

    @Inject
    private IService service;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(
                        Names.named("log")), new LoggerMethodInterceptor());
            }
        });
        injector.getInstance(AopDemo.class).service.sayHello();
        injector.getInstance(AopDemo.class).service.sayHello();
        injector.getInstance(AopDemo.class).service.sayHello();
    }
}
