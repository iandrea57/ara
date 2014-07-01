/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.intergration;

import com.google.inject.Key;
import com.google.inject.util.Types;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:51
 */
public final class GuiceKeys {

    public static <T> Key<T> getKey(Type rawType, Annotation annotation, Type... typeArguments) {
        ParameterizedType parameterizedType = Types.newParameterizedType(rawType, typeArguments);
        return (Key<T>) Key.get((Type) parameterizedType, annotation);
    }
}
