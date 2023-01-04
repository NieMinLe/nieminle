package com.swaggertest.demo.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAuth {
    boolean value() default true;
    String name() default "";//备注信息
}
