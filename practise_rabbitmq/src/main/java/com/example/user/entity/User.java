package com.example.user.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-03-20 18:37:44
 */
public class User implements Serializable {
    private static final long serialVersionUID = 569925204703972118L;

    private String id;

    private String name;

    private String phone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

