package com.swaggertest.demo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 请求执行前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        // 如果获取的request的session中的loginUser参数为空（未登录），就返回登录页，否则放行访问
        // if (user == null) {
        //     // 未登录，给出错误信息，
        //     System.out.println("无权限");
        //     request.setAttribute("name","无权限请先登录");
        //     // 获取request返回页面到登录页
        //     request.getRequestDispatcher("/login").forward(request, response);
        //     return false;
        // } else {
        //     // 已登录，放行
        //     return true;
        // }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}