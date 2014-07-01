/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.service;

import com.ara.web.model.Account;
import com.ara.web.model.AuthToken;
import com.ara.web.model.Principal;
import com.ara.web.service.provider.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午4:19
 */
@Service
public class AuthService {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AccountService accountService;

    public Principal authenticate(AuthToken authToken) {
        Principal principal = tokenProvider.decodeToken(authToken);
        if (principal != null) {
            long userId = principal.getUserId();
            Account account = accountService.get(userId);
            if (account == null) {
                return null;
            }
        }
        return principal;
    }

    public AuthToken getToken(Principal principal) {
        return tokenProvider.encodeToken(principal);
    }

}
