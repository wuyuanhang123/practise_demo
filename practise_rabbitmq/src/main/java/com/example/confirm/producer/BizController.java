package com.example.confirm.producer;

import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class BizController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        /**
        生产者将信
        道设置成confirm(确认)模式，一旦信道进入confirm 模式，所有在该信道上⾯面发布的消息都会被指派
        一个唯一的ID(从1 开始)，一旦消息被投递到所有匹配的队列之后（如果消息和队列是持久化的，那么
        确认消息会在消息持久化后发出），RabbitMQ 就会发送一个确认(Basic.Ack)给生产者(包含消息的唯一ID)，这样生产者就知道消息已经正确送达了
         */
        this.rabbitTemplate.setConfirmCallback((correlationData, flag, cause) -> {
            if(flag){
                try{
                    System.out.println("消息确认:"+correlationData.getId()+" "+"消息确认成功");
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else{
                //打印失败原因
                System.out.println(cause);
            }
        });

    }

    @RequestMapping("/biz")
    public String doBiz() throws UnsupportedEncodingException {
        org.springframework.amqp.core.MessageProperties properties = new org.springframework.amqp.core.MessageProperties();
        properties.setCorrelationId("1234");
        properties.setConsumerTag("msg1");

        properties.setContentType(org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        properties.setContentEncoding("utf-8");

        CorrelationData cd = new CorrelationData();
        cd.setId("msg1");

        Message message = new Message("这是等待确认的消息".getBytes("utf-8"), properties);
                rabbitTemplate.convertAndSend("ex.biz", "biz", message,
                        cd);
                return "ok";
    }

    @RequestMapping("/bizfalse")
    public String doBizFalse() throws UnsupportedEncodingException {
        org.springframework.amqp.core.MessageProperties properties = new org.springframework.amqp.core.MessageProperties();
        properties.setCorrelationId("1234");
        properties.setConsumerTag("msg1");
        properties.setContentType(org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        properties.setContentEncoding("utf-8");

        Message message = new Message("这是等待确认的消息".getBytes("utf-8"),properties);
        rabbitTemplate.convertAndSend("ex.bizFalse", "biz", message);
        return "ok";
    }
}
