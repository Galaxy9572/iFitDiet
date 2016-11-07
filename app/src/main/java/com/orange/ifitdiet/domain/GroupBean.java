package com.orange.ifitdiet.domain;

import android.media.Image;

import com.orange.ifitdiet.common.Bean;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/9.
 */
public class GroupBean extends Bean implements Serializable {
    private String id;//群组ID
    private String name;//群组名
    private String userID;//创建者的ID
    private String date;//创建日期
    private int number;//群组人数
    private Image qr;//二维码

    public GroupBean(String name,String userID,String date,int number) {
        this.name=name;
        this.userID=userID;
        this.date=date;
        this.number=number;
    }

    public GroupBean() {
    }

    public GroupBean(String id, String name, String userID, String date, int number, Image qr) {
        this.id = id;
        this.name = name;
        this.userID = userID;
        this.date = date;
        this.number = number;
        this.qr = qr;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Image getQr() {
        return qr;
    }

    public void setQr(Image qr) {
        this.qr = qr;
    }
}
