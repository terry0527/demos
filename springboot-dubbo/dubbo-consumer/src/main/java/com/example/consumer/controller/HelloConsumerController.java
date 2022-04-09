package com.example.consumer.controller;

import com.example.api.service.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description

 * history:
 */
@RestController
public class HelloConsumerController {

    //用下面这样指定服务提供者url的方式可以做到服务直连，不经过zookeeper
    //@Reference(interfaceName = "com.example.api.service.HelloService",url = "dubbo://127.0.0.1:20880",application = "dubbo-provider",timeout = 3000,version = "${demo.service.version}")
    @Reference(version = "${demo.service.version}")
    private HelloService helloService;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("参数="+name);
        return helloService.sayHello(name);
    }
}
