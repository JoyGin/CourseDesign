package com.joygin.crm.settings.service;

import com.joygin.crm.exception.LoginException;
import com.joygin.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
