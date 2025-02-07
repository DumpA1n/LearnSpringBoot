package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class RedisFileService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Map<String, String> dumpAllKeyValueTXT(String filename) {
        SortedMap<String, String> map = new TreeMap<>();
        List<String> keys = new ArrayList<>(stringRedisTemplate.keys("*"));
        Collections.sort(keys);
        for (String key : keys) {
            map.put(key, stringRedisTemplate.opsForValue().get(key));
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String key : keys) {
                bw.write(key + ":" + map.get(key));
                bw.newLine();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return map;
    }

    public void dumpAllKeyValueJSON(String filename) {
    }
}
