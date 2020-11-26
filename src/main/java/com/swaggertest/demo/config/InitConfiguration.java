package com.swaggertest.demo.config;

import com.swaggertest.demo.controller.UserController;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Data
public class InitConfiguration {

  private UserController userController;

  @PostConstruct
  public void init(){
    initQpsLimit();
  }

  public void initQpsLimit(){
      System.out.println("启动时运行");
    }




}
