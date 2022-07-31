package com.example.user;

import com.example.practise_basic.PractiseBasicApplication;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PractiseBasicApplication.class)
class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("测试");
        user.setPhone("1223");
        userService.insert(user);
    }

}
