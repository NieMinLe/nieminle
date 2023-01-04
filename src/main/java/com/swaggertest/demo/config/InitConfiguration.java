package com.swaggertest.demo.config;

import com.swaggertest.demo.controller.testa.UserController;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
