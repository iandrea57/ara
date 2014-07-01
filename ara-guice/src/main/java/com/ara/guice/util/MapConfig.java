/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.guice.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-6-27 下午4:01
 */
public final class MapConfig {

    public static ImmutableMap<String, String> pasreConf(String confPath) {
        Map<String, String> all = Maps.newHashMap();
        InputStream in = null;
        try {
            in = MapConfig.class.getResourceAsStream(confPath);
            Properties properties = new Properties();
            properties.load(in);
            for (String key : properties.stringPropertyNames()) {
                all.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ImmutableMap.<String, String>builder().putAll(all).build();
    }



    private MapConfig() {}
}
