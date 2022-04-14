package com.example.threadpooldemoserver.service.impl;

import com.example.threadpooldemoserver.service.AsyncService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * description  异步任务的实现
 * history:
 */
@Service
@Log4j2
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        try{
            Thread.sleep(1000);
            System.out.println("线程id"+Thread.currentThread().getId());
            System.out.println("线程名称"+Thread.currentThread().getName());
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }
}
