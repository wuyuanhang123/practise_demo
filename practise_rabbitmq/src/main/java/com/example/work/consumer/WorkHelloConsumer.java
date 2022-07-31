package com.example.work.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq work模式消费者
 */
@Component
public class WorkHelloConsumer {
    @RabbitListener(queues = "workqueue")
    public void service(String message){
        System.out.println("消费者1号接受到消息："+message);
    }

    @RabbitListener(queues = "workqueue")
    public void service2(String message){
        System.out.println("消费者2号接受到消息："+message);
    }

    @RabbitListener(queues = "workqueue")
    public void service3(String message){
        System.out.println("消费者3号接受到消息："+message);
    }
}
