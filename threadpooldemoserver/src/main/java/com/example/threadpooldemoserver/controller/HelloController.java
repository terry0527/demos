package com.example.threadpooldemoserver.controller;

import com.example.threadpooldemoserver.service.AsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 * history:
 */
@RestController
@Log4j2
public class HelloController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/")
    public String submit(){
        log.info("start submit");

        //启动一个异步任务
        asyncService.executeAsync();

        log.info("end submit");

        return "success";
    }
}
