package com.orange.ifitdiet.domain;

import java.io.Serializable;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 * 登录时用的JavaBean
 */
public class LoginUserBean implements Serializable {
    private String userName;//用户名
    private String psw;//密码

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
