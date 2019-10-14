package com.clouderwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author xuqiang
 * @Description:
 * @Date: 2017/7/11
 **/
@Service
public class RedisService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  public void setStr(String key, String value) {
    stringRedisTemplate.opsForValue().set(key, value);
  }

  public String getStr(String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }

  public void setObject(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public Object getObject(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public boolean expire(String key, long timeout) {
    return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
  }

  public boolean exists(String key) {
    return redisTemplate.hasKey(key);
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }

  public void setList(final String key, final List list) {
    ListOperations opsForList = stringRedisTemplate.opsForList();
    list.stream().forEach(i -> opsForList.rightPush(key, i.toString()));
  }

  public Object getIdFormList(final String key, final String uuid) {
    List listStr = stringRedisTemplate.opsForList().range(key, 0, -1);
    if (!CollectionUtils.isEmpty(listStr)) {
      List collect = (List) listStr.stream().filter(i -> {
//        System.out.println(i);
        return i.toString().contains(uuid);
      }).collect(Collectors.toList());
      return CollectionUtils.isEmpty(collect) == true ? null : collect.get(0);
    }
    return null;
  }

  public boolean setExpireObject(final String key, Object value, Long expireTime) {
    boolean result = false;
    try {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public void setExpireStr(final String key, String value, Long expireTime) {
      stringRedisTemplate.opsForValue().set(key, value);
      redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
  }

}
