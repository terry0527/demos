package com.example.rabbitmq.producer;

import cn.hutool.core.lang.Console;
import com.example.rabbitmq.RabbitMQConfig;
import com.example.rabbitmq.RedisConfig;
import com.example.rabbitmq.entity.RabbitMetaMessage;
import com.example.rabbitmq.util.DefaultKeyGenerator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultKeyGenerator keyGenerator;

    @GetMapping("/sendMessage")
    public Object sendMessage() {
        new Thread(() -> {
            HashOperations hashOperations = redisTemplate.opsForHash();
            for (int i = 0; i < 1; i++) {
                String id = keyGenerator.generateKey() + "";
                String value = "message " + i;
                RabbitMetaMessage rabbitMetaMessage = new RabbitMetaMessage(value);

                // 先把消息存储到 redis
                hashOperations.put(RedisConfig.RETRY_KEY, id, rabbitMetaMessage);

                Console.log("send message = {}", value);

                // 再发送到 rabbitmq
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value, (message) -> {
                    message.getMessageProperties().setMessageId(id);
                    return message;
                }, new CorrelationData(id));
            }
        }).start();
        return "ok";
    }

}
