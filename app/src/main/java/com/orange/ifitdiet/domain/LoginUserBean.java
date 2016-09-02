package com.orange.ifitdiet.domain;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 */
public class LoginUserBean {
    private String userName;
    private String psw;

    public LoginUserBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
