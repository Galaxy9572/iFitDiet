package com.orange.ifitdiet.domain;

import android.media.Image;

import com.orange.ifitdiet.common.Bean;

/**
 * Created by 廖俊瑶 on 2016/10/26.
 */

public class FoodBean extends Bean{
    private String name;
    private int type;
    private Image picture;
    private int calories;
    private String taste;
    private String nutrition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }
}
