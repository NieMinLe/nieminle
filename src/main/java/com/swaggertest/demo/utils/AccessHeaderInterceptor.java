package com.swaggertest.demo.utils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * json跨域过滤
 * @author nie
 */
public class AccessHeaderInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		//josn跨域
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE");//,GET,PUT,DELETE
		response.setHeader("Access-Control-Allow-Headers","Access-Control,jwt,content-type");
		response.setHeader("Allow","POST");
		response.setCharacterEncoding("UTF-8");
		return true;
	}
	
	

}
