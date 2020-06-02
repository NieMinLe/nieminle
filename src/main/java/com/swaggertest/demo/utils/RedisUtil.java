package com.swaggertest.demo.utils;

import com.alibaba.fastjson.JSON;
import com.swaggertest.demo.domain.dto.RedisDTO;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {
  @Autowired
  private RedisTemplate redisTemplate;



  public void setCache(String key,Object data) {
    redisTemplate.opsForValue().set(key, JSON.toJSONString(data));
  }


  public void setCacheExpireTime(String key, Object data, Long expire, TimeUnit unit) {
    redisTemplate.opsForValue().set(key,JSON.toJSONString(data),expire,unit);
  }

  public Boolean del(Object key) {
    return redisTemplate.delete(key);
  }
  public void delBatchCache(List<String> list) {
    redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      list.forEach(obj->{
        connection.del(obj.getBytes());
      });
      return null;
    });
  }

  public void setCacheByObject(List<RedisDTO> list) {
    redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      list.forEach(obj->{
        connection.set(obj.getKey().toString().getBytes(), JSON.toJSONString(obj.getData()).getBytes());
        if(null != obj.getExpire()){
          connection.expire(obj.getKey().toString().getBytes(), obj.getExpire());
        }
      });
      return null;
    });
  }


  public Object getCacheObject(String key){
    return redisTemplate.opsForValue().get(key);
  }

}
