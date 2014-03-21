/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.test.guice;

import com.ara.test.guice.service.IHelloWorldService;
import com.ara.test.guice.service.impl.HelloWorldServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午1:27
 */
public class HelloWorldTest {

    @Test
    public void testHello() {
        Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(IHelloWorldService.class).to(
                        HelloWorldServiceImpl.class);
            }
        });
        IHelloWorldService service = injector.getInstance(
                IHelloWorldService.class);
        Assert.assertEquals(service.sayHello(), "Hello World!");
    }

}
