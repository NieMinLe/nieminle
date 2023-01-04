package com.swaggertest.demo.aop;

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodAuth {

    boolean value() default true;

    int level() default 0;
}
