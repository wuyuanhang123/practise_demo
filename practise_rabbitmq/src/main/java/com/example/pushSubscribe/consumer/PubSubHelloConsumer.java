package com.example.pushSubscribe.consumer;

import com.example.message.MqMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * rabbitmq 订阅推送模式消费者
 */
@Component
public class PubSubHelloConsumer {
    @RabbitListener(queues = "pubsubQueueFirst")
    public void service(@Payload MqMessage mqMessage){
        System.out.println("消费者1号接受到消息："+mqMessage);
    }

    @RabbitListener(queues = "pubsubQueueSecond")
    public void service2(@Payload MqMessage mqMessage){
        System.out.println("消费者2号接受到消息："+mqMessage);
    }

}
