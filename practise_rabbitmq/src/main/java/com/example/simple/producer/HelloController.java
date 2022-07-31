package com.example.simple.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbitmq简单模式生产者
 */
@RestController
public class HelloController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/simple/{message}")
    public String sendMessage(@PathVariable(value = "message") String message){
        amqpTemplate.convertAndSend("myqueue",message);
        return "ok";
    }
}
