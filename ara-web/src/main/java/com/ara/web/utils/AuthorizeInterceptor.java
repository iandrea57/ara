/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.utils;

import com.ara.web.constant.ArgumentNameDef;
import com.ara.web.constant.TokenConstants;
import com.ara.web.dao.AccountDAO;
import com.ara.web.entity.AccountEntity;
import com.ara.web.model.Account;
import com.ara.web.model.AuthToken;
import com.ara.web.model.Principal;
import com.ara.web.model.User;
import com.ara.web.service.AccountService;
import com.ara.web.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:iiiandrea57@gmail.com">i云涯</a>
 * @createTime 14-3-28 下午6:00
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String cookie = CookieUtils.getCookie(request, TokenConstants.TICKET);
        Principal principal = authService.authenticate(new AuthToken(cookie));
        if (principal != null) {
            Account account = accountService.get(principal.getUserId());
            if (account != null) {
                User host = new User();
                host.setId(account.getId());
                host.setAccount(account.getAccount());
                host.setName(account.getAccount());
                host.setPassword(account.getPassword());
                request.setAttribute(ArgumentNameDef.HOST, host);
            }
            return true;
        }

        // 验证未通过， 跳登录页
        String srcUrl = request.getRequestURL().toString();
        if (srcUrl.contains("/login")) {
            return true;
        }
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            srcUrl = srcUrl + "?" + queryString;
            //如果是退出就不添加
            if(srcUrl.contains("/logout")) {
                srcUrl = "";
            }
        }
        response.sendRedirect("/login?fromUrl=" + UrlEncodeUtils.encodeUTF8(srcUrl));
        return false;
    }
}
