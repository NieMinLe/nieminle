package com.swaggertest.demo.entity.dto;

import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //生成getter,setter等函数
@Builder
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor //生成无参构造函数
public class RedisDTO {
  private Object key;
  private Object data;
  private Long expire;
  private TimeUnit unit = TimeUnit.SECONDS;

}
