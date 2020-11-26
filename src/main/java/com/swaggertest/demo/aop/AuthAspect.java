package com.swaggertest.demo.aop;

import com.alibaba.fastjson.JSONObject;
import com.swaggertest.demo.domain.dto.TestDto;
import com.swaggertest.demo.exception.MyException;
import com.swaggertest.demo.system.consts.RedisConst;
import com.swaggertest.demo.system.enums.EnumDataOpenCode;
import com.swaggertest.demo.utils.RedisUtil;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 权限校验切面.
 */
@Slf4j
@Aspect
@Component
public class AuthAspect {

    /**
     * request
     */
    private final HttpServletRequest request;

    /**
     * 认证中心用户dubbo服务.
     */
    @Autowired
    private RedisUtil redisUtil;

    /**
     * redis
     *
     * @param request 请求
     */
    @Autowired
    public AuthAspect(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 请求日志记录
     */
    @Pointcut("execution(public * com.swaggertest.demo.controller..*.*(..))")
    public void requestPoint() {
    }

    /**
     * 认证拦截器
     */
    @Pointcut("@annotation(com.swaggertest.demo.aop.MethodAuth)")
    public void authPoint() {
    }

    /**
     * 前置方法日志打印
     *
     * @param joinPoint 切点
     */
    @Before("authPoint()")
    public void doBefore(JoinPoint joinPoint) {
        // System.out.println("我是之前执行的方法!!!");
        // ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // RequestContextHolder.setRequestAttributes(attributes, true);

        log.info("token = {}  url = {} method = {} ip = {} class_method = {} class_method = {} args = {}",
                request.getHeader("token"), request.getRequestURI(), request.getMethod(), request.getRemoteAddr(),
                joinPoint.getSignature(), joinPoint.getArgs());
    }

    /**
     * AuthAspect
     *
     * @param joinPoint 切点
     * @param methodAuth 自定义注解
     * @return do
     * @throws Throwable 未登录时抛出异常
     */
    @Around("authPoint() && @annotation(methodAuth)")
    public Object authAspect(ProceedingJoinPoint joinPoint, MethodAuth methodAuth) throws Throwable {
        String token = request.getHeader("token");
        String sessionID = request.getHeader("user-session");

        Object jsonStr;
        if(StringUtils.isNotBlank(token)){
            jsonStr = redisUtil.getCacheObject(String.format(RedisConst.TOKEN_USER_INFO_CACHE_KEY,token));
        }else if(StringUtils.isNotBlank(sessionID)){
            jsonStr = redisUtil.getCacheObject(String.format(RedisConst.LOGIN_SESSION_USER_KEY,sessionID));
        }else{
            throw new MyException(EnumDataOpenCode.NO_PERMISSION.getCode(),"非法请求");
        }

        TestDto po = JSONObject.parseObject(jsonStr.toString(),TestDto.class);

        if (po.getSno() != methodAuth.level()) {
            throw new MyException(EnumDataOpenCode.NO_PERMISSION.getCode(),"权限不够");
        }
        return joinPoint.proceed();
    }

    /**
     * AfterReturning
     *
     * @param obj 返回值
     */
    @AfterReturning(returning = "obj", pointcut = "requestPoint()")
    public void doAfterReturning(Object obj) {
        log.info("response = {}", Optional.ofNullable(obj).orElse(""));
    }

}
