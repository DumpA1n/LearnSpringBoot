package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        stringRedisTemplate.delete(key);
    }

    public void randomGen(Integer count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Integer randomVal = random.nextInt(9999999);
            stringRedisTemplate.opsForValue().set("key" + String.valueOf(randomVal), "value" + String.valueOf(randomVal));
        }
    }
}
