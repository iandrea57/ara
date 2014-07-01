/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.controllers;

import com.ara.web.constant.ErrorEnum;
import com.ara.web.constant.TokenConstants;
import com.ara.web.model.Account;
import com.ara.web.model.AuthToken;
import com.ara.web.model.Principal;
import com.ara.web.model.User;
import com.ara.web.service.AccountService;
import com.ara.web.service.AuthService;
import com.ara.web.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-5 下午6:05
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, User host,
            @RequestParam(value = "fromUrl", required = false, defaultValue = "") String fromUrl)
            throws Exception {
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response, User host,
            @RequestParam(value = "account", required = true) String account,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "fromUrl", required = false, defaultValue = "") String fromUrl)
            throws Exception {
        String toUrl = fromUrl;
        if (StringUtils.isBlank(toUrl)) {
            toUrl = "/home";
        }
        int auth = accountService.auth(account, password);
        ErrorEnum error = ErrorEnum.indexOf(auth);
        String errorDesc = "未知错误";
        if (error != null) {
            if (error == ErrorEnum.NO_ERROR) { // 正常登录
                Account ac = accountService.get(account);
                Principal principal = new Principal(ac.getId(), ac.getAccount());
                AuthToken token = authService.getToken(principal);
                CookieUtils.setCookie(response, TokenConstants.TICKET, token.getCookie());
                return new ModelAndView("redirect:" + toUrl);
            } else {
                errorDesc = error.getDescription();
            }
        }
        ModelAndView mv = new ModelAndView("login/login");
        mv.addObject("account", account);
        mv.addObject("error", errorDesc);
        return mv;
    }
}
