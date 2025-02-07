package com.example.demo.controller;

import com.example.demo.service.RedisFileService;
import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "success to set " + key + ":" + value;
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        return redisService.getValue(key);
    }

    @DeleteMapping("/del")
    public String deleteValue(@RequestParam String key) {
        String value = redisService.getValue(key);
        redisService.deleteValue(key);
        return "success to delete " + key + ":" + value;
    }

    @PostMapping("/gen")
    public String randomGen(@RequestParam Integer count) {
        redisService.randomGen(count);
        return "success to generate";
    }

    @Autowired
    private RedisFileService redisFileService;

    @GetMapping("/dumptxt")
    public String dumpAllKeyValueTXT(@RequestParam String filename) {
        redisFileService.dumpAllKeyValueTXT(filename);
        return "success to dump all key value to " + filename;
    }

    @GetMapping("/dumpjson")
    public String dumpAllKeyValueJSON(@RequestParam String filename) {
        redisFileService.dumpAllKeyValueJSON(filename);
        return "success to dump all key value to " + filename;
    }
}
