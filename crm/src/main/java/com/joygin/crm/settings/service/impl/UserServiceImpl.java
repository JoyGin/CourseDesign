package com.joygin.crm.settings.service.impl;

import com.joygin.crm.exception.LoginException;
import com.joygin.crm.settings.dao.UserDao;
import com.joygin.crm.settings.domain.User;
import com.joygin.crm.settings.service.UserService;
import com.joygin.crm.utils.DateTimeUtil;
import com.joygin.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<String, String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);

        //判断账号或密码是否正确
        if(user == null){
            throw new LoginException("账号或密码错误");
        }

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){
            throw new LoginException("账号已失效");
        }

        //判断锁定状态
        String lockState = user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("账号已锁定");
       }

        //判断ip地址
        String allowIps = user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw new LoginException("ip地址受限");
        }

        return user;
    }

    public List<User> getUserList() {

        List<User> userList = userDao.getUserList();

        return userList;
    }
}
