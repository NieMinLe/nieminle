package com.swaggertest.demo.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author 10166815
 */
@Component
public class ConsumerController {

    /**
     * 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
     * @param message
     * @return
     */
//    @JmsListener(destination = "${myqueue}")
//    public void handleMessage(String message) {
//        System.out.println("成功接受name: " + message);
//    }

}