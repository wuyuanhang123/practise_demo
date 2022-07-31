package com.example.work.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbitmq work模式生产者
 */
@RestController
public class WorkHelloController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/work/{message}")
    public String sendMessage(@PathVariable(value = "message") String message){
        for(int i =0;i<10;i++){
            amqpTemplate.convertAndSend("workqueue",message+i);
        }
        return "ok";
    }
}
