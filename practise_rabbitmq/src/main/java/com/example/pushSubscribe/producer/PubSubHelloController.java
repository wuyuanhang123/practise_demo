package com.example.pushSubscribe.producer;

import com.example.message.MqMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * rabbitmq 订阅推送模式生产者
 */
@RestController
public class PubSubHelloController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/pubSub/{message}")
    public String sendMessage(@PathVariable(value = "message") String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");

        for(int i =0;i<10;i++){
            MqMessage mqMessage = new MqMessage();
            mqMessage.setCreatedAt(LocalDateTime.now());
            mqMessage.setId(String.valueOf(i));
            mqMessage.setMessage(message+i);
            String jsonMessage = objectMapper.writeValueAsString(mqMessage);
            Message message_ = new Message(jsonMessage.getBytes(), messageProperties);
            amqpTemplate.convertAndSend("pubsubExchange",null,message_);
        }
        return "ok";
    }
}
