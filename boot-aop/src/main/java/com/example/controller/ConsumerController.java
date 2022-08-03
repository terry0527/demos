package com.example.controller;

import com.example.annotation.AutoIdempotent;
import com.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {

    @Autowired
    RedisService redisService;

    @GetMapping("/getToken")
    public String getToken(){
        boolean falg = redisService.setExp("mideng","asdfg",2L);
        return "falg";
    }

    @AutoIdempotent(expireTime = 5000)
    @GetMapping("/start/{index}")
    public String setValue( @PathVariable("index")  String index){
        return index + "1";
    }

    @GetMapping("/start2/{index}")
    public String setValue2( @PathVariable("index")  String index) {
        return index + "2";
    }
}
