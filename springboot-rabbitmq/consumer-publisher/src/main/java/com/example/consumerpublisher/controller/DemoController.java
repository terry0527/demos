package com.example.consumerpublisher.controller;

import com.example.consumerpublisher.entity.User;
import com.example.consumerpublisher.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * description
 * history:
 */
@RequestMapping("demo")
@RestController
public class DemoController {

    @Resource
    UserService userService;

    @PostMapping("/find")
    public String find(@RequestBody User user) throws Exception{
        userService.addPerson(user);
        return "aaaa";
    }
}
