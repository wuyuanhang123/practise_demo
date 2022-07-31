package com.example.topic.consumer;

import com.example.message.MqMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * rabbitmq 订阅主题模式消费者
 */
@Component
public class TopicHelloConsumer {
    @RabbitListener(queues = "topicQueueFirst")
    public void service(@Payload MqMessage mqMessage){
        System.out.println("消费者1号接受到消息："+mqMessage);
    }

    @RabbitListener(queues = "topicQueueSecond")
    public void service2(@Payload MqMessage mqMessage){
        System.out.println("消费者2号接受到消息："+mqMessage);
    }

    @RabbitListener(queues = "topicQueueThird")
    public void service3(@Payload MqMessage mqMessage){
        System.out.println("消费者3号接受到消息："+mqMessage);
    }

}
