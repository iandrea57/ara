/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.test.guice;

import com.ara.test.guice.service.IHelloWorldService;
import com.ara.test.guice.service.impl.HelloWorldServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Provider;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午1:27
 */
public class HelloWorldTest {

    private static final String HELLO = "Hello World!";

    @Inject
    private IHelloWorldService injectService;

    public IHelloWorldService getInjectService() {
        return injectService;
    }

    private static Injector injector;

    static {
         injector = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(IHelloWorldService.class).to(HelloWorldServiceImpl.class);
            }
        });
    }

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
        Assert.assertEquals(service.sayHello(), HELLO);
    }

    @Test
    public void testHelloProvider() {
        Injector injector = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(IHelloWorldService.class).toProvider(
                        new Provider<IHelloWorldService>() {

                            @Override
                            public IHelloWorldService get() {
                                return new HelloWorldServiceImpl();
                            }
                        });
            }
        });
        IHelloWorldService service = injector.getInstance(
                IHelloWorldService.class);
        Assert.assertEquals(service.sayHello(), HELLO);
    }

    @Test
    public void testHelloJnject() {
        HelloWorldTest test = injector.getInstance(HelloWorldTest.class);
        Assert.assertEquals(test.getInjectService().sayHello(), HELLO);
    }

    @Test
    public void testHelloSington() {
        Injector injector = Guice.createInjector(new Module() {

            @Override
            public void configure(Binder binder) {
                binder.bind(IHelloWorldService.class).to(
                        HelloWorldServiceImpl.class).asEagerSingleton();
            }
        });
        IHelloWorldService service1 = injector.getInstance(
                IHelloWorldService.class);
        IHelloWorldService service2 = injector.getInstance(
                IHelloWorldService.class);
        Assert.assertEquals(service1.hashCode(), service2.hashCode());
    }

}
