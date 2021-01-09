package com.joygin.crm.settings.dao;

import com.joygin.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, String> map);
}
