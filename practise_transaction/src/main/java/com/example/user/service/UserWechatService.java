package com.example.user.service;

import com.example.user.dao.UserWechatDao;
import com.example.user.entity.UserWechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserWechatService {
    @Autowired
    private UserWechatDao userWechatDao;

    @Transactional(rollbackFor = Exception.class)
    public void insertDefault(UserWechat userWechat){
            int i = 1 / 0;
            userWechatDao.insert(userWechat);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertNew(UserWechat userWechat){
            int i = 1 / 0;
        userWechatDao.insert(userWechat);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertNested(UserWechat userWechat){
        int i = 1/0;
            userWechatDao.insert(userWechat);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertcycleNested(UserWechat userWechat, int i){
        if(i == 3 || i == 5){
            int j = 1/0;
        }
        userWechat.setUserId(String.valueOf(i));
        userWechatDao.insert(userWechat);
    }

    @Transactional
    public void insertcycleDefault(UserWechat userWechat){
        try {
            userWechatDao.insert(userWechat);
            int i = 1 / 0;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertcycleNew(UserWechat userWechat) throws Exception {
            userWechatDao.insert(userWechat);
            int i = 1 / 0;
    }
}
