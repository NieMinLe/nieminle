package com.swaggertest.demo.controller.testa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class InterceptorController {


    @GetMapping("/demo123")
    public String demoAction() {
        System.out.println("开始输入");
        return "success";
    }

}
