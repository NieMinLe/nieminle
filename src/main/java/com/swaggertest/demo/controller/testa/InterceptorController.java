package com.swaggertest.demo.controller.testa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @GetMapping("/demo")
    public String demoAction(String param) {
        System.out.println("开始输入=-=-"+param);
        return "success";
    }

}
