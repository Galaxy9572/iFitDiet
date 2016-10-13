package com.orange.ifitdiet.domain;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/10/12.
 */
public class WeatherBean implements Serializable {
    private String weather;
    private String temperature;

    public WeatherBean(String temperature, String weather) {
        this.temperature = temperature;
        this.weather = weather;
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
}
