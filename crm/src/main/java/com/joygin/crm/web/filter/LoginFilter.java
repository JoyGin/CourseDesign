package com.joygin.crm.web.filter;

import com.joygin.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证是否登录过滤器
 */
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //判断是否为登录操作，是则直接放行,否则验证
        String path = request.getServletPath();
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){

            filterChain.doFilter(servletRequest,servletResponse);

        }else{

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            //验证是否登录
            if(user!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}
