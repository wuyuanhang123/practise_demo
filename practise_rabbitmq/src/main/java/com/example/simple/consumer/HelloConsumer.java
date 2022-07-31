package com.example.simple.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq简单模式消费者
 */
@Component
public class HelloConsumer {
    @RabbitListener(queues = "myqueue")
    public void service(String message){
        System.out.println("接受到消息："+message);
    }
}
