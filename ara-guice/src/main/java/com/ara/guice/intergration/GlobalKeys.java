/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.intergration;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Key;
import com.google.inject.name.Names;

/**
 * 定义全局Guice 绑定名称
 *
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午3:49
 */
public final class GlobalKeys {

    public static final Key<ImmutableMap<String, String>> GLOBAL_CONF_KEY = GuiceKeys.getKey(
            ImmutableMap.class, Names.named("GLOBAL.CONF"), String.class,
            String.class);

    private GlobalKeys() {}
}
