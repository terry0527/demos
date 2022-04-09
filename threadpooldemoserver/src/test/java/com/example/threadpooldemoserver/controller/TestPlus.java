package com.example.threadpooldemoserver.controller;

import com.example.threadpooldemoserver.service.AsyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description
 * history:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPlus {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void submit(){

        //启动一个异步任务
        asyncService.executeAsync();


    }
}
