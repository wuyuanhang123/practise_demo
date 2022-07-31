package com.example.practise_basic;

import org.apache.catalina.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example" })
@MapperScan(basePackages = { "com.example" })
public class PractiseBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(PractiseBasicApplication.class, args);
    }
}
