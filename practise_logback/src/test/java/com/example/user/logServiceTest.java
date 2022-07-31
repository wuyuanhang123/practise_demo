package com.example.user;

import com.example.practise_basic.PractiseBasicApplication;
import com.example.user.service.logService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PractiseBasicApplication.class)
public class logServiceTest {
    @Autowired
    private logService log;

    @Test
    public void log(){
        log.logInfo("测试");
    }
}
