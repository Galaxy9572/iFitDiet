package com.orange.ifitdiet.domain;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 * 注册使用的JavaBean
 */
public class UserBean implements Serializable {
    private int UUID;//UUID，服务器产生
    private String userName;//用户名
    private int sex;//性别
    private String psw;//密码
    private String phone;//手机号
    private Image avatar;//头像
    private String email;//电子邮件
    private String birthday;//生日
    private String hometown;//籍贯

    public UserBean() {
    }

    public int getUUID() {
        return UUID;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
