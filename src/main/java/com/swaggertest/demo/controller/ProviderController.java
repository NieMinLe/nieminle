package com.swaggertest.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "testController", tags = "activeMq提供者")
public class ProviderController {

    /**
     * 获取配置文件中的队列名称
     */
//    @Value("${myqueue}")
//    private String myqueue;

    /**
     * 注入springboot封装的工具类
     */
//    @Resource
//    private JmsMessagingTemplate jmsTemplate;

//    @PostMapping("send")
//    @ApiOperation("提供者给出的名字")
//    public void send(String name) {
//        jmsTemplate.convertAndSend(myqueue, name);
//    }

}