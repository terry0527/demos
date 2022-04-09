package com.example.consumerpublisher.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * description
 * history:
 */
@Component
public class UserTopicRecive {

    @RabbitListener(queues="spring.demo")
    public void process(String user) throws InterruptedException {
        System.out.println("TopicRecive1接受的消息： "+user);
    }
}
