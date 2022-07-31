package com.example.user.service;

import com.example.user.dao.UserDao;
import com.example.user.entity.User;
import com.example.user.entity.UserWechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserWechatService userWechatService;


    /**
     * 外层REQUIRED内层REQUIRED，内层抛异常
     * 没有catch:user表没有数据，user_wechat表没有数据
     * 有catch: user表有数据，user_wechat表有数据
     * 有catch：user表有数据，user_wechat表有数据,把int i = 1/0调到sql前面，则user表有数据，user_wechat表没有数据
     * 总结：与new最大的不同是，外层抛异常会导致内层回滚
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertDefault(User user){
        userDao.insert(user);

        UserWechat userWechat = new UserWechat();
        userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
        userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());

        userWechatService.insertDefault(userWechat);
        int i = 1/0;
    }

    /**
     * 外层REQUIRED内层REQUIRED，内层抛出异常，而外层catch情况下
     * 会报出Transaction rolled back because it has been marked as rollback-only
     * @param user
     */
    public void rollbackOnlyTest(User user){
        userDao.insert(user);

        UserWechat userWechat = new UserWechat();
        userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
        userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());
        try {
            userWechatService.insertDefault(userWechat);
        } catch (Exception e){

        }
    }

    /**
     * 外层REQUIRED内层NEW,内层抛异常，
     * 没有catch: user表没有数据，user_wechat表没有数据
     * 有catch: user表有数据，user_wechat表有数据,把int i = 1/0调到sql前面，则user表有数据，user_wechat表没有数据
     * 外层抛异常，内层正常
     * 则user表没有数据，user_wechat表有数据
     * 总结：内层抛异常，会造成外层回滚，如果使用catch，则执行过的方法不会回滚，外层抛异常，内层已执行的方法不会回滚
     * @param user
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertNew(User user){
        userDao.insert(user);

        UserWechat userWechat = new UserWechat();
        userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
        userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());
        userWechatService.insertNew(userWechat);

    }

    /**
     * 外层REQUIRED内层NESTED,内层抛异常，
     * 没有catch，都没有数据
     * 外层catch,user有数据，user_wechat没有数据
     * 总结：外层抛异常内层必定回滚，内层抛异常，外层catch则外层不会回滚，与new的不同的点在于外层抛异常，内层必回滚
     * @param user
     */
    public void insertNested(User user){
        userDao.insert(user);

        UserWechat userWechat = new UserWechat();
        userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
        userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());
        try {
            userWechatService.insertNested(userWechat);
        }catch (Exception e){

        }
//        int i =1/0;
    }




    /**
     * 循环例子
     * @param user
     */
    @Transactional
    public void insertcycleDefault(User user){
        for(int i =0;i<5;i++){
            user.setName(String.valueOf(i));
            userDao.insert(user);

            UserWechat userWechat = new UserWechat();
            userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
            userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());

            userWechatService.insertcycleDefault(userWechat);
        }
    }

    /**
     * 循环例子
     * @param user
     */
    @Transactional
    public void insertcycle(User user) throws Exception {
        for(int i =0;i<7;i++){

            try {
                user.setName(String.valueOf(i));
                userDao.insert(user);

                UserWechat userWechat = new UserWechat();
                userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
                userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());
                userWechatService.insertcycleNested(userWechat,i);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void addUserAndSalary(User user) {
//
//        //往员工表里插入该用户的用户信息
//        userDao.insert(user);
//
//        //往人资表里插入该用户的工资信息
//        try {
//            UserWechat userWechat = new UserWechat();
//            userWechat.setOpenId(new Integer(new Random(6L).nextInt()).toString());
//            userWechat.setUserId(new Integer(new Random(6L).nextInt()).toString());
//            userWechatService.insertDefault(userWechat);
//            System.err.println(111);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
