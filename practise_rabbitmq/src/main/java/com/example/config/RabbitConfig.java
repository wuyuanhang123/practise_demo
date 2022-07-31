package com.example.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.ExhaustedRetryException;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitConfig {
    /**
     * 简单模式和队列模式
     * @return
     */
//    @Bean
//    public Queue myQueue(){
//        return new Queue("myqueue");
//    }
//    @Bean
//    public Queue workQueue(){
//        return new Queue("workqueue");
//    }
//    @Bean
//    public Exchange myExchange(){
//        return new DirectExchange("myex",false,false,null);
//    }
//
//    @Bean
//    public Binding myBinding(){
//        return new Binding("myqueue", Binding.DestinationType.QUEUE,"myex","direct.biz.ex",null);
//    }
//    /**
//     * 发布/订阅模式
//     */
//    @Bean
//    public Queue pubsubQueueFirst(){
//        return new Queue("pubsubQueueFirst");
//    }
//
//    @Bean
//    public Queue pubsubQueueSecond(){
//        return new Queue("pubsubQueueSecond");
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange("pubsubExchange");
//    }
//
//    @Bean
//    public Binding pubsubQueueFirstBind(){
//        return BindingBuilder.bind(pubsubQueueFirst()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding pubsubQueueSecondBind(){
//        return BindingBuilder.bind(pubsubQueueSecond()).to(fanoutExchange());
//    }

    /**
     * 主题模式
     */
    @Bean
    public Queue topicQueueFirst(){
        return new Queue("topicQueueFirst");
    }

    @Bean
    public Queue topicQueueSecond(){
        return new Queue("topicQueueSecond");
    }

    @Bean
    public Queue topicQueueThird(){
        return new Queue("topicQueueThird");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding topicFirstBinding(){
        return  BindingBuilder.bind(topicQueueFirst()).to(topicExchange()).with("*.first.*");
    }

    @Bean
    public Binding topicSecondBinding(){
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with("*.second.*");
    }

    @Bean
    public Binding topicThirdBinding(){
        return BindingBuilder.bind(topicQueueThird()).to(topicExchange()).with("*.third.*");
    }

    @Bean
    public Queue queue(){
        Queue queue = new Queue("q.biz", false,false,false,null);
        return queue;
    }

    @Bean
    public Exchange exchange(){
        Exchange exchange = new DirectExchange("ex.biz", false,false,null);
        return exchange;
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with("biz").noargs();
    }

    @Bean
    public Queue queueTTLWaiting(){
        Map<String, Object> props = new HashMap<>();
        props.put("x-message-ttl",10000);
        Queue queue = new Queue("q.pay.ttl-waiting",false,false,false, props);
        return queue;
    }

    @Bean
    public Queue queueWaiting() {
        Queue queue = new Queue("q.pay.waiting", false, false,
                false);
        return queue;
    }

    @Bean
    public Exchange exchangeTTLWaiting(){
        DirectExchange exchange = new DirectExchange("ex.pay.ttl-waiting", false, false);
        return exchange;
    }


    @Bean
    public Binding bindingTTLWaiting(){
        return BindingBuilder.bind(queueTTLWaiting()).to(exchangeTTLWaiting()).with("pay.ttl-waiting").noargs();
    }

    @Bean
    public Exchange exchangeWaiting() {
        DirectExchange exchange = new
                DirectExchange("ex.pay.waiting", false, false);
        return exchange;
    }

    @Bean
    public Binding bindingWaiting() {
        return
                BindingBuilder.bind(queueWaiting()).to(exchangeWaiting()).with("pay.waiting").noargs();
    }

//    /**
//     * 传输对象json解码/编码
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }

}
