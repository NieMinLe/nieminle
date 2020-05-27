package com.swaggertest.demo.config;

import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author LIZEJIN
 * @Description TODO
 * @Date 2020/5/9
 * @Time 14:05
 * @Version V1.0.0
 */
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
