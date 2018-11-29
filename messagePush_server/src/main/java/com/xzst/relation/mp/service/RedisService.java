package com.xzst.relation.mp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public  void setString(String key, String value){
        logger.info("--------------------->[Redis set start]");
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String getString(String key){
        logger.info("--------------------->[Redis get start]");
        return  stringRedisTemplate.opsForValue().get(key);
    }


    public void set() {
        this.setString("name","张三");
    }

    public void get(){
        logger.info(this.getString("name"));
    }

}
