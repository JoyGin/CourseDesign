package com.joygin.crm.settings.web.controller;

import com.joygin.crm.settings.domain.User;
import com.joygin.crm.settings.service.UserService;
import com.joygin.crm.settings.service.impl.UserServiceImpl;
import com.joygin.crm.utils.MD5Util;
import com.joygin.crm.utils.PrintJson;
import com.joygin.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入用户servlet");

        String path = request.getServletPath();
        System.out.println(path);

        if("/settings/user/login.do".equals(path)){
            login(request, response);
        }else if("/settings/user/xxx.do".equals(path)){
            //xxx(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println("登录方法");
        //获取账号
        String loginAct = request.getParameter("loginAct");
        //获取密码
        String loginPwd = request.getParameter("loginPwd");
        loginPwd = MD5Util.getMD5(loginPwd);
        //获取ip地址
        String ip = request.getRemoteAddr();
        //获取业务层对象
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{
            User user = us.login(loginAct, loginPwd, ip);
            request.getSession().setAttribute("user", user);
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            System.out.println("账号或密码错误");
            e.printStackTrace();
            String msg  = e.getMessage();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success",false);//false是布尔类型，不要弄字符串类型
            map.put("msg",msg);
//            try {
//                response.getWriter().print("{\"success\":false,\"msg\":\"账号或密码错误\"}");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
            PrintJson.printJsonObj(response, map);
        }

    }

}
