package com.ws.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 目标方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            System.out.println("登录的用户：" + user);
            request.setAttribute("msg", "没有权限，请先登录~");
            // 未登录，返回登录页面
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        // 已登录，放行请求
        return true;
    }
}
