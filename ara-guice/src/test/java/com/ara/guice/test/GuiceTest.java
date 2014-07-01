/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.test;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 下午2:46
 */
public class GuiceTest {

    @Test
    public void testSayHello() {
        Injector inj = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(HelloWorld.class).to(HelloWorldImpl.class);
            }
        });
        HelloWorld hw = inj.getInstance(HelloWorld.class);
        assertEquals(hw.sayHello(), "Hello, world!");
    }
}
