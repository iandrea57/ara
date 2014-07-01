/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.intergration;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-7-1 下午12:12
 */
public final class ServiceEntry {
    public static final TypeLiteral<Set<ServiceEntry>> SET_TYPE_LITERAL = (TypeLiteral<Set<ServiceEntry>>) TypeLiteral.get(
            Types.setOf(ServiceEntry.class));
    public static final Key<Set<ServiceEntry>> SET_KEY = Key.get(SET_TYPE_LITERAL);

    private final String name;

    private final String className;

    private final Key<?> guiceKey;

    private final String annotationName;

    public ServiceEntry(String name, String className,
            Key<?> guiceKey, String annotationName) {
        this.name = name;
        this.className = className;
        this.guiceKey = guiceKey;
        this.annotationName = annotationName;
    }

    public static Set<ServiceEntry> getAllService(Injector injector) {
        List<Binding<Set<ServiceEntry>>> bindingsByType = injector.findBindingsByType(SET_TYPE_LITERAL);
        if (bindingsByType == null || bindingsByType.isEmpty()) {
            throw new IllegalStateException("Can't find the defined ServiceEntry");
        }
        return injector.getInstance(SET_KEY);
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public Key<?> getGuiceKey() {
        return guiceKey;
    }

    public String getAnnotationName() {
        return annotationName;
    }
}
