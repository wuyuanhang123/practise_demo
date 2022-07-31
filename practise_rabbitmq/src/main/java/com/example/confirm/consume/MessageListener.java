package com.example.confirm.consume;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class MessageListener {

    private Random random = new Random();

    @RabbitListener(queues = "q.biz", ackMode = "MANUAL")
    public void handleMessageTopic(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, @Payload String message){
        System.out.println("RabbitListener消费消息, 消息内容:"+message);

        try{
            if(random.nextInt(10)%3!=0){
                // 手动nack，告诉broker消费者处理失败，最后一个参数表示是否需要将消息重新入列
                // channel.basicNack(deliveryTag, false, true);
                // 手动拒绝消息。第二个参数表示是否重新入列
                channel.basicReject(deliveryTag, true);
            } else{
                // 手动ack，deliveryTag表示消息的唯一标志，multiple表示是否是批量确认
                channel.basicAck(deliveryTag,false);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
