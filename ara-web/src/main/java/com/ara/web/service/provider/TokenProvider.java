/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.service.provider;

import com.ara.web.constant.TokenConstants;
import com.ara.web.model.AuthToken;
import com.ara.web.model.Principal;
import com.ara.web.utils.DesEncrypterUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午4:23
 */
@Service
public class TokenProvider {

    private static final Random RANDOM = new Random();

    public AuthToken encodeToken(Principal principal) {
        AuthToken token = null;
        if (principal != null) {
            String cookie = DesEncrypterUtils.encrypt(RANDOM.nextInt()
                    + TokenConstants.SPLIT_CHAR + principal.getUserId()
                    + TokenConstants.SPLIT_CHAR + System.currentTimeMillis());
            token = new AuthToken(cookie);
        }
        return token;
    }

    public Principal decodeToken(AuthToken token) {
        if (token == null) {
            return null;
        }
        String cookie = token.getCookie();
        if (StringUtils.isBlank(cookie)) {
            return null;
        }
        String decodeToken = DesEncrypterUtils.decrypt(cookie);
        if (StringUtils.isBlank(decodeToken)) {
            return  null;
        }
        String[] values = decodeToken.split(TokenConstants.SPLIT_CHAR);
        if (values == null || values.length != 3) {
            return null;
        }
        Principal principal = new Principal();
        principal.setUserId(NumberUtils.toLong(values[1]));
        return principal;
    }

}
