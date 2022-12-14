package com.example.confirm.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BizACKController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Random random = new Random();

    @RequestMapping("/ack/biz")
    public String getBizMessage(){
        String message = rabbitTemplate.execute(new ChannelCallback<String>() {
            @Override
            public String doInRabbit(Channel channel) throws Exception {
                final GetResponse getResponse = channel.basicGet("q.biz", false);
                if(getResponse == null) return "您已消费完所有消息";
                String message = new String(getResponse.getBody(),"utf-8");

                if(random.nextInt(10) % 3 == 0){
                    channel.basicAck(getResponse.getEnvelope().getDeliveryTag(), false);
                    return "已确认的消息"+message;
                } else{
                    channel.basicReject(getResponse.getEnvelope().getDeliveryTag(), true);
                    return "拒绝的消息:"+message;
                }
            }
        });
        return message;
    }
}
