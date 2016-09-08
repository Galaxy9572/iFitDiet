package com.orange.ifitdiet.domain;

/**
 * Created by 廖俊瑶 on 2016/9/8.
 */
public class StatesBean {

    private String province;//省
    private String city;//市
    private String district;//区
    private String street;//街道
    private boolean isLogin;
    private boolean isRegister;

    public StatesBean() {
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }
}
