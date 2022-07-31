package com.example.practise_basic;

import org.apache.catalina.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example" })
@MapperScan(basePackages = { "com.example" })
public class PractiseBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(PractiseBasicApplication.class, args);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public ApplicationRunner runner(){
        return args -> {
            Thread.sleep(5000);
            for(int i =0;i<10;i++){
                MessageProperties properties = new MessageProperties();
                properties.setDeliveryTag(i);

                Message message = new Message(("消息"+i).getBytes("utf-8"), properties);
                this.rabbitTemplate.convertAndSend("ex.biz", "biz",message);
            }
        };
    }
}
