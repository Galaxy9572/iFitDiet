package com.orange.ifitdiet.domain;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/11.
 */
public class SocialMsgBean implements Serializable {
    private String msg;
    private String time;
    private Bitmap image;

    public SocialMsgBean(String msg, String time, Bitmap image) {
        this.msg = msg;
        this.time = time;
        this.image = image;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
