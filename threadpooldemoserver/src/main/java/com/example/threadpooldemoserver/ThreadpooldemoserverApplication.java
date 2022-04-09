package com.example.threadpooldemoserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan("com.example.threadpooldemoserver.task5")
public class ThreadpooldemoserverApplication {
/**
 * description
 * date: 
 */
    public static void main(String[] args) {

        SpringApplication.run(ThreadpooldemoserverApplication.class, args);

    }

}
