package com.joygin.crm.settings.service;

import com.joygin.crm.exception.LoginException;
import com.joygin.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
