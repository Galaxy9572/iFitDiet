package com.orange.ifitdiet.domain;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/9.
 */
public class GroupBean implements Serializable {
    private int personNum;
    private int times;

    public GroupBean() {
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
