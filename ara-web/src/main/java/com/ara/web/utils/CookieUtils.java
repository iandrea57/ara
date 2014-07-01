/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.utils;

import com.ara.web.constant.TokenConstants;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午3:45
 */
public class CookieUtils {

    private static final Logger logger = Logger.getLogger(CookieUtils.class);

    private static final int EXPIRED_TIME_HOUR = 1 * 60 * 60;

    private static final int EXPIRED_TIME_DAY = 24 * EXPIRED_TIME_HOUR;


    /**
     * 获取cookie
     *
     * @param request    请求request
     * @param cookieName cookie名
     * @return
     */
    public static String getCookie(HttpServletRequest request,
            String cookieName) {
        Cookie[] cookies = request.getCookies();
        String result = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    result = cookie.getValue();
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 设置cookie
     *
     * @param response
     * @param cookieName
     * @param value
     * @param maxAge
     * @param domain
     */
    public static void setCookie(HttpServletResponse response,
            String cookieName, String value, int maxAge, String domain) {
        try {
            Cookie cookie = new Cookie(cookieName, value);
            cookie.setDomain(domain);
            cookie.setPath("/");
            cookie.setMaxAge(maxAge);
            response.addCookie(cookie);
        } catch (Exception e) {
            logger.error("set cookie exception", e);
        }
    }

    /**
     * 移除cookie
     *
     * @param response
     * @param cookieName
     * @param domain
     */
    public static void removeCookie(HttpServletResponse response,
            String cookieName, String domain) {
        try {
            Cookie cookie = new Cookie(cookieName, "");
            cookie.setDomain(domain);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            logger.error("remove cookie exception", e);
        }
    }

    public static void setCookie(HttpServletResponse response,
            String cookieName, String value) {
        setCookie(response, cookieName, value, EXPIRED_TIME_DAY, TokenConstants.ARA_DOMAIN);
    }

}
