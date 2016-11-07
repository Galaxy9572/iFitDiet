package com.orange.ifitdiet.domain;

import android.media.Image;

import com.orange.ifitdiet.common.Bean;

/**
 * Created by 廖俊瑶 on 2016/11/5.
 */
public class BusinessBean extends Bean{
    private String id;
    private String name;
    private String location;//商家位置
    private int type;//类型：中餐、西餐···
    private String phone;
    private Image portrait;
    private int average;//均价
    private double pointX;//经度
    private double pointY;//纬度
    private int star;//评分

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Image getPortrait() {
        return portrait;
    }

    public void setPortrait(Image portrait) {
        this.portrait = portrait;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
