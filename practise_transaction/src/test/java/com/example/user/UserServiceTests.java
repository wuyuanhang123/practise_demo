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
        userService.insertNew(user);
    }

    @Test
    public void testDefault(){
        User user = new User();
        user.setName("测试");
        user.setPhone("1223");
        userService.insertDefault(user);
    }

    @Test
    public void testRollbackOnly(){
        User user = new User();
        user.setName("测试");
        user.setPhone("1223");
        userService.rollbackOnlyTest(user);
    }

    @Test
    public void testInsert2(){
        User user = new User();
        user.setName("测试");
        user.setPhone("1223");
        userService.insertcycleDefault(user);
    }



    @Test
    public void testInsertNested() throws Exception {
        User user = new User();
        user.setName("测试");
        user.setPhone("1223");
        userService.insertNested(user);
    }

    @Test
    public void testInsertCycle() throws Exception {
        User user = new User();
        user.setName("测试");
        user.setPhone("1223");
        userService.insertcycle(user);
    }


}
