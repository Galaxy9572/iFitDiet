package com.orange.ifitdiet.domain;

import com.orange.ifitdiet.common.Bean;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/11/9.
 */

public class TipBean extends Bean implements Serializable{
    private String provinceInfo;
    private String temperatureInfo;

    public TipBean(String provinceInfo, String temperatureInfo) {
        this.provinceInfo = provinceInfo;
        this.temperatureInfo = temperatureInfo;
    }

    public String getProvinceInfo() {
        return provinceInfo;
    }

    public void setProvinceInfo(String provinceInfo) {
        this.provinceInfo = provinceInfo;
    }

    public String getTemperatureInfo() {
        return temperatureInfo;
    }

    public void setTemperatureInfo(String temperatureInfo) {
        this.temperatureInfo = temperatureInfo;
    }
}
