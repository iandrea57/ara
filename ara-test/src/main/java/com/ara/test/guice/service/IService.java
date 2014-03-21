package com.ara.test.guice.service;

import com.ara.test.guice.service.impl.ServiceImpl;
import com.google.inject.ImplementedBy;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-21 下午3:55
 */
@ImplementedBy(ServiceImpl.class)
public interface IService {

    public String sayHello();
}
