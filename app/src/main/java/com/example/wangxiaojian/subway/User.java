package com.example.wangxiaojian.subway;

/**
 * Created by Wangxiaojian on 2016/12/12.
 */

public class User {

    private String name;

    private String phoneNumber;
    private String password;

    public User(String name, String phoneNumber,String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password=password;
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }
}

