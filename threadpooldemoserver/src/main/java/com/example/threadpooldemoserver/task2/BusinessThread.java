package com.example.threadpooldemoserver.task2;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;

/**
 * description 业务线程类
 * history:
 */
public class BusinessThread implements Callable<String> {

    private String name;

    BusinessThread(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public String call() {

        // 模拟处理业务逻辑
        System.out.println(name + "正在游戏中--" + Thread.currentThread().getName());

        Thread.sleep(1000);

        return name;
    }
}
