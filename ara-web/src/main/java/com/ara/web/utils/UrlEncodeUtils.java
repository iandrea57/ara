/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.utils;

import org.apache.log4j.Logger;

import java.net.URLEncoder;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午5:56
 */
public class UrlEncodeUtils {

    private static final Logger logger = Logger.getLogger(UrlEncodeUtils.class);

    public static String encodeUTF8(String url) {
        String result = "";
        try {
            result = URLEncoder.encode(url, "utf-8");
        } catch (Exception e) {
            logger.error("UrlEncodeUTF8 exception", e);
        }
        return result;
    }
}
