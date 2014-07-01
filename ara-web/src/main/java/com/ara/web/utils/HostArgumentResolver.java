/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.utils;

import com.ara.web.constant.ArgumentNameDef;
import com.ara.web.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author <a href="mailto:iiiandrea57@gmail.com">i云涯</a>
 * @createTime 14-3-28 下午5:48
 */
public class HostArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean isSupport = methodParameter.getParameterType().equals(User.class);
        return isSupport;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {
        User host = (User) nativeWebRequest.getAttribute(
                ArgumentNameDef.HOST, RequestAttributes.SCOPE_REQUEST);
        if (host == null) {
            host = new User();
            return host;
        } else {
            return host;
        }
    }
}
