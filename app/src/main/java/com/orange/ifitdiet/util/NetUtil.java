package com.orange.ifitdiet.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orange.ifitdiet.domain.LoginUserBean;
import com.orange.ifitdiet.domain.RegisterUserBean;

import org.apache.http.Header;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 * 网络连接的工具类，使用开源项目AsyncHttpClient
 */
public class NetUtil {
    private boolean isRegistered = false;
    private boolean isLogin=false;

    public boolean register(RegisterUserBean userBean){//注册方法
        String reg_url ="http://172.30.168.1:8080/iFitDiet2/AccountServlet?cmd=register";//服务器地址，测试地址
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params=new RequestParams();
        //将注册信息添加到参数中
        params.put("username",userBean.getUserName());
        params.put("sex",userBean.getSex());
        params.put("password",userBean.getPsw());
        params.put("phone",userBean.getPhone());
        params.put("portrait",userBean.getAvatar());
        params.put("email",userBean.getEmail());
        params.put("hometown",userBean.getHometown());
        params.put("birthday",userBean.getBirthday());
        client.post(reg_url, params, new AsyncHttpResponseHandler() {//用Http POST方式提交到服务器
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                isRegistered =true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                isRegistered=false;
            }
        });
        return isRegistered;
    }
    public boolean login(LoginUserBean userBean){//登陆方法
        String reg_url ="http://172.30.168.1:8080/iFitDiet2/AccountServlet?cmd=login";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",userBean.getUserName());
        params.put("password",userBean.getPsw());
        client.post(reg_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                isLogin =true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                isLogin=false;
            }
        });
        return isLogin;
    }

}
