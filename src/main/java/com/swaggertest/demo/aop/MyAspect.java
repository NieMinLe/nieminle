package com.swaggertest.demo.aop;

import com.swaggertest.demo.dao.TestMapper;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)// Order值越小优先被加载
public class MyAspect {

    private Boolean recordOptLog = true;

    @Resource
    private TestMapper testMapper;

    @Pointcut("@annotation(com.swaggertest.demo.aop.MyAuth)")
    public void myAuthPoint() {
    }

    @Pointcut("execution(public * com.swaggertest.demo.controller.CateController.threeLevelPullDown())")
    public void testPoint(){

    }

    @Around("testPoint()")
    public Object httpLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] orgs = proceedingJoinPoint.getArgs();
        Signature signature = proceedingJoinPoint.getSignature();
        return proceedingJoinPoint.proceed();
    }


    @Before("myAuthPoint()")
    public Object before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("这个是args啊=-=-="+args);
        return joinPoint.getTarget();
    }

    // Object[] args = joinPoint.getArgs();
    // System.out.println("这里这里=-=-="+ args);
    // System.out.println("我是之前执行的方法!!!");
    // ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    // RequestContextHolder.setRequestAttributes(attributes, true);

    @Around("myAuthPoint() && @annotation(myAuth)")
    public Object doBefore(ProceedingJoinPoint joinPoint, MyAuth myAuth) throws Throwable {
        // TestDto t = new TestDto();
        // t.setSname(myAuth.name());
        // testMapper.insertSelective(t);
        return joinPoint.proceed();
    }

}
