package com.swaggertest.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(com.swaggertest.demo.aop.MyAuth)")
    public void myAuthPoint() {
    }


    @Before("myAuthPoint()")
    public void before() {
        // System.out.println("我是之前执行的方法!!!");
        // ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // RequestContextHolder.setRequestAttributes(attributes, true);
    }

    @Around("myAuthPoint() && @annotation(myAuth)")
    public Object authAspect(ProceedingJoinPoint joinPoint, MyAuth myAuth) throws Throwable {
        return joinPoint.proceed();
    }

}
