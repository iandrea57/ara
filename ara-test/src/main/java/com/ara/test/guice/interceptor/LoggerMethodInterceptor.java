/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.test.guice.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午3:59
 */
public class LoggerMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        long startTime = System.nanoTime();
        System.out.println(String.format("before method[%s] at %s", methodName,
                startTime));
        Object ret = null;
        try {
            ret = invocation.proceed();
        } finally {
            long endTime = System.nanoTime();
            System.out.println(
                    String.format(" after method[%s] at %s, cost(ns):%d",
                            methodName, endTime, (endTime - startTime)));
        }
        return ret;
    }
}
