package com.example.consumerpublisher.service;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MQService {

    @RabbitListener(queues = "fanout.queue")
    public void receive(Message message) {
        System.out.println("收到消息 : " + new String(message.getBody()));

    }

    @RabbitListener(queues = "direct.queue")
    public void receive2(Message message) {
        System.out.println("direct.queue收到消息 : " + new String(message.getBody()));

    }
}
