package com.swaggertest.demo.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
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

  private static final String activeName = "activeUser:";

  public void setRedisBitMap() {
    del(activeName);
    List<Long> te = Lists.newArrayList(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L);
    te.forEach(v ->{
      redisTemplate.opsForValue().setBit(activeName,v,true);
    });
  }

  public Boolean getRedisBitMap(Long id) {
    return redisTemplate.opsForValue().getBit(activeName,id);
  }

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
