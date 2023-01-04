package com.swaggertest.demo.servlet;

import com.swaggertest.demo.interceptor.OpenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public OpenInterceptor tokenInterceptor() {
//        return new OpenInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截的请求，并排除几个不拦截的请求
//        registry.addInterceptor(new OpenInterceptor()).addPathPatterns("/**")
//            .excludePathPatterns("/login","/submit","/demo");
        registry.addInterceptor(new OpenInterceptor()).excludePathPatterns("/interceptor/demo*","/swagger**/**").addPathPatterns("/**");
    }

}