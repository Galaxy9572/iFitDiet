package com.orange.ifitdiet.domain;

import android.media.Image;

import com.orange.ifitdiet.common.Bean;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 * 注册使用的JavaBean
 */
public class UserBean extends Bean implements Serializable {
    private String id;//id，服务器产生
    private String name;//用户名
    private int sex;//性别
    private String password;//密码
    private Image portrait;//头像
    private String loginName;//(登录名)电子邮件
    private String birthday;//生日
    private String hometown;//籍贯
    private String taste;//口味
    private int height;//身高
    private int weight;//体重

    public UserBean() {
    }

    public UserBean(String id, String name, int sex, String password,String loginName, String birthday, String hometown, String taste, int height, int weight) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.loginName = loginName;
        this.birthday = birthday;
        this.hometown = hometown;
        this.taste = taste;
        this.height = height;
        this.weight = weight;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}