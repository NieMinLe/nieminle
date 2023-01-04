package com.swaggertest.demo.controller.testa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("send")
    @ApiOperation("提供者给出的名字")
    public void send(String name) {
//        System.out.println(myqueue);
    }

}