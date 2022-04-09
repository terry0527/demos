package com.example.rabbitmq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class ConsumerAckPublisherConfirmsApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void contextLoads() {
    }

    @Test
    public void create() {
//		//创建Exchange
        amqpAdmin.declareExchange(new DirectExchange("customer.ack.exchange"));

        //创建Queue
        amqpAdmin.declareQueue(new Queue("customer.ack.queue", true));

        //绑定Queue
        amqpAdmin.declareBinding(new Binding("ccustomer.ack.exchange", Binding.DestinationType.QUEUE, "customer.ack.exchange", "customer.ack.key", null));
    }

    @Test
    public void send2Direct() {
        Map<String , Object> map = new HashMap<>();
        map.put( "msg" , "这是一条点对点消息" );
        map.put( "data" , Arrays.asList("helloworld" , 123 , true) );

        rabbitTemplate.convertAndSend( "customer.ack.exchange" , "customer.ack.queue" , map );

    }


}
