package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void insert(User user){
        userDao.insert(user);
    }
}
