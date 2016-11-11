package com.orange.ifitdiet.domain;

import com.orange.ifitdiet.common.Bean;

/**
 * Created by 廖俊瑶 on 2016/11/5.
 */

public class HealthBean extends Bean {
    private String userId;
    private int heartrate;
    private int bloodpressure;
    private int steps;
    private int inCalories;
    private int outCalories;
    private String date;
    private int burn_calories;

    public HealthBean(String userId, int heartrate, int steps, String date) {
        this.userId = userId;
        this.heartrate = heartrate;
        this.steps = steps;
        this.date = date;
    }

    public HealthBean(int heartrate) {
        this.heartrate = heartrate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    public int getBloodpressure() {
        return bloodpressure;
    }

    public void setBloodpressure(int bloodpressure) {
        this.bloodpressure = bloodpressure;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getInCalories() {
        return inCalories;
    }

    public void setInCalories(int inCalories) {
        this.inCalories = inCalories;
    }

    public int getOutCalories() {
        return outCalories;
    }

    public void setOutCalories(int outCalories) {
        this.outCalories = outCalories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBurn_calories() {
        return burn_calories;
    }

    public void setBurn_calories(int burn_calories) {
        this.burn_calories = burn_calories;
    }
}
