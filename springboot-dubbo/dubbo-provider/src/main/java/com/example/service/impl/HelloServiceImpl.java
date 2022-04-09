package com.example.service.impl;

import com.example.api.service.HelloService;
import com.alibaba.dubbo.config.annotation.Service;


/**
 * description
 * history:
 */
@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name + ",This is springboot-dubbo test";
    }
}
