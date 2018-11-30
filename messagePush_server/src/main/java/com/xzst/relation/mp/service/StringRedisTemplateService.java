package com.xzst.relation.mp.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StringRedisTemplateService {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private StringRedisTemplate stringRedisTemplate;





    public void setString(String key,String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }
    public String getString(String key){
        logger.info(stringRedisTemplate.opsForValue().get(key));
        return stringRedisTemplate.opsForValue().get(key);
    }


}
