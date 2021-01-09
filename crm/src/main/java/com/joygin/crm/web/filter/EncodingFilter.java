package com.joygin.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 字符编码过滤器，设置编码格式
 */
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置请求体编码
        servletRequest.setCharacterEncoding("UTF-8");
        //设置响应编码
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
