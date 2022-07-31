package com.example.topic.producer;

import com.example.message.MqMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * rabbitmq 订阅主题模式生产者
 */
@RestController
public class TopicHelloController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/topic/{message}")
    public String sendMessage(@PathVariable(value = "message") String message) throws JsonProcessingException {
        String[] number = new String[]{"1","2","3","4","5","6","7","8","9","10"};
        String[] ordered = new String[]{"first","second","third"};
        String[] lastName = new String[]{"WU","MING","KKK","eee","wqqq"};
        ObjectMapper objectMapper = new ObjectMapper();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");

        for(int i =0;i<10;i++){
            Integer numberIndex = new Random().nextInt(number.length);
            Integer orderedIndex = new Random().nextInt(ordered.length);
            Integer lastNameIndex = new Random().nextInt(lastName.length);

            String routingKey = number[numberIndex]+"."+ordered[orderedIndex]+"."+lastName[lastNameIndex];
            MqMessage mqMessage = new MqMessage();
            mqMessage.setCreatedAt(LocalDateTime.now());
            mqMessage.setId(String.valueOf(i));
            mqMessage.setMessage(routingKey);
            String jsonMessage = objectMapper.writeValueAsString(mqMessage);
            Message message_ = new Message(jsonMessage.getBytes(), messageProperties);
            amqpTemplate.convertAndSend("topicExchange",routingKey,message_);
        }
        return "ok";
    }
}
