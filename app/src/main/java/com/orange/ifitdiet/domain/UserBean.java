package com.orange.ifitdiet.domain;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 * 注册使用的JavaBean
 */
public class UserBean implements Serializable {
    private String id;//id，服务器产生
    private String name;//用户名
    private int sex;//性别
    private String password;//密码
    private Image portrait;//头像
    private String loginName;//(登录名)电子邮件
    private String birthday;//生日
    private String hometown;//籍贯
    private String taste;//口味

    public UserBean() {
    }

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getPortrait() {
        return portrait;
    }

    public void setPortrait(Image portrait) {
        this.portrait = portrait;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
}