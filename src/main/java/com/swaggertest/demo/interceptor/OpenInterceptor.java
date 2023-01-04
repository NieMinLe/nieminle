package com.swaggertest.demo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.domain.po.OpenUserPO;
import com.swaggertest.demo.exception.IsException;
import com.swaggertest.demo.system.consts.RedisConst;
import com.swaggertest.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class OpenInterceptor implements HandlerInterceptor {

  @Autowired
  private RedisUtil redisUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String path = request.getServletPath();

    String sessionID = request.getHeader("user-session");
    String token = request.getHeader("token");
    String signature = request.getHeader("signature");

    if(null != token){
      if(null == signature){
        log.info("非法请求：没有签名");
        throw new IsException("没有签名");
      }
      //使用token请求
      Object jsonStr = redisUtil.getCacheObject(String.format(RedisConst.TOKEN_USER_INFO_CACHE_KEY,token));
      if(null == jsonStr){
        log.info("非法请求：token失效");
        throw new IsException("token失效");
      }
    }else{
      //使用session 请求
      Object jsonStr = redisUtil.getCacheObject(String.format(RedisConst.LOGIN_SESSION_USER_KEY,sessionID));
      if(null == jsonStr){
        log.info("请登陆");
        throw new IsException("请登陆");
      }

      if(path.contains("loginOut")){
        redisUtil.del(String.format(RedisConst.LOGIN_SESSION_USER_KEY,sessionID));
      }else{
        redisUtil.setCacheExpireTime(String.format(RedisConst.LOGIN_SESSION_USER_KEY,sessionID), JSONObject
            .parseObject(jsonStr.toString(), OpenUserPO.class),24L, TimeUnit.HOURS);
      }
    }
    return true;
  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


  }


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


  }
}
