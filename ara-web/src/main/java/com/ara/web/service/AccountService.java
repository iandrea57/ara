/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.service;

import com.ara.web.constant.ErrorEnum;
import com.ara.web.dao.AccountDAO;
import com.ara.web.entity.AccountEntity;
import com.ara.web.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午5:33
 */
@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public Account get(long userId) {
        AccountEntity entity = accountDAO.get(userId);
        return accountFromEntity(entity);
    }

    public Account get(String account) {
        AccountEntity entity = accountDAO.getOneByProperty("account", account);
        return accountFromEntity(entity);
    }

    public int auth(String account, String password) {
        int result;
        AccountEntity entity = accountDAO.getOneByProperty("account", account);
        if (entity != null) {
            if (entity.getPassword().endsWith(password)) {
                result = ErrorEnum.NO_ERROR.getIndex();
            } else {
                result = ErrorEnum.AUTH_PASSWORD_WRONG.getIndex();
            }
        } else {
            result = ErrorEnum.AUTH_ACCOUNT_NOT_EXIST.getIndex();
        }
        return result;
    }

    private Account accountFromEntity(AccountEntity entity) {
        Account account = null;
        if (entity != null) {
            account = new Account();
            account.setId(entity.getId());
            account.setAccount(entity.getAccount());
            account.setPassword(entity.getPassword());
        }
        return account;
    }

}
