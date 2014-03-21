/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.test.guice.service.impl;

import com.ara.test.guice.service.IService;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午3:55
 */
@Singleton
public class ServiceImpl implements IService {

    @Override
    @Named("log")
    public String sayHello() {
        System.out.println(String.format("[%s#%d] execute %s at %d",
                this.getClass().getSimpleName(), hashCode(), "sayHello",
                System.nanoTime()));
        return "Hello World!";
    }
}
