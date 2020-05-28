package com.swaggertest.demo.config;

import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InitConfiguration {

  @PostConstruct
  public void init(){
    initQpsLimit();
  }

  public void initQpsLimit(){
      System.out.println("启动时运行");
    }




}
