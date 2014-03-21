/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.test.guice.service.impl;

import com.ara.test.guice.service.IHelloWorldService;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午1:19
 */
public class HelloWorldServiceImpl implements IHelloWorldService {

    @Override
    public String sayHello() {
        return "Hello World!";
    }
}
