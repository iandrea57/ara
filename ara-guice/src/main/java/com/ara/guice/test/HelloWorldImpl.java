/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.test;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 下午2:48
 */
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHello() {
        return "Hello, world!";
    }
}
