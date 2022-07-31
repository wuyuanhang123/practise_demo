package com.example.ttl.producer;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class PayContorller {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping("/pay/queuettl")
    public String sendMessage(){
        rabbitTemplate.convertAndSend("ex.pay.ttl-waiting", "pay.ttl-waiting", "发送TTL-WAITING-MESSAGE");
        return "ok";
    }

    @RequestMapping("/pay/msgttl")
    public String sendTTLMessage() throws UnsupportedEncodingException {
        MessageProperties properties = new MessageProperties();
        properties.setExpiration("5000");
        Message message = new Message("发送了WAITING-MESSAGE".getBytes("utf-8"), properties);
        rabbitTemplate.convertAndSend("ex.pay.waiting", "pay.waiting",message);
        return "msg-ttl-ok";
    }
}
