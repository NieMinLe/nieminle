package com.swaggertest.demo.servlet;

import com.swaggertest.demo.config.ParamValidInterceptor;
import com.swaggertest.demo.interceptor.OpenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public OpenInterceptor tokenInterceptor() {
//        return new OpenInterceptor();
//    }

    @Bean
    public ParameterNameDiscoverer parameterNameDiscoverer() {
        return new LocalVariableTableParameterNameDiscoverer();
    }

    @Resource
    private ParamValidInterceptor paramValidInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截的请求，并排除几个不拦截的请求
//        registry.addInterceptor(new OpenInterceptor()).addPathPatterns("/**")
//            .excludePathPatterns("/login","/submit","/demo");
        registry.addInterceptor(new OpenInterceptor()).excludePathPatterns("/interceptor/demo*","/swagger**/**").addPathPatterns("/**");

//        registry.addInterceptor(paramValidInterceptor);
    }

}