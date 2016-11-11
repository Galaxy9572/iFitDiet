package com.orange.ifitdiet.domain;

import com.orange.ifitdiet.common.Bean;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/10/12.
 */
public class WeatherBean extends Bean implements Serializable {
    private String weather;
    private String temperature;
    private String province;
    private String city;

    public WeatherBean(String temperature, String weather) {
        this.temperature = temperature;
        this.weather = weather;
    }

    public WeatherBean(String temperature,String weather, String province,String city) {
        this.temperature = temperature;
        this.weather = weather;
        this.province = province;
        this.city=city;
    }
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
