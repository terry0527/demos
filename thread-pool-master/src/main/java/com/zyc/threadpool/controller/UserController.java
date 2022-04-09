package com.zyc.threadpool.controller;

import com.zyc.threadpool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/send1")
    public void send1(){
        userService.massTextingByParallelStream();
    }

    @RequestMapping("/user/sendMessage")
    public void send(){
        userService.massTextingByThreadPoolExecutorAndLatch();
    }

    @RequestMapping("/user/send3")
    public void send3(){
        userService.massTextingByThreadPoolExecutorAndLatchC();
    }
}
