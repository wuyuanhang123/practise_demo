package com.example.user.entity;

import java.io.Serializable;

/**
 * (UserWechat)实体类
 *
 * @author makejava
 * @since 2022-03-24 22:12:51
 */
public class UserWechat implements Serializable {
    private static final long serialVersionUID = -64154433298424655L;

    private String id;

    private String userId;

    private String openId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}

