package com.orange.ifitdiet.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orange.ifitdiet.domain.LoginUserBean;
import com.orange.ifitdiet.domain.UserBean;

import org.apache.http.Header;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 廖俊瑶 on 2016/9/2.
 * 网络连接的工具类，使用开源项目AsyncHttpClient
 */
public class NetUtil {
    private boolean isRegistered;//是否注册成功
    private boolean isLogin;//是否登陆成功

    public boolean register(UserBean userBean) {//注册方法
        String reg_url = "http://172.17.215.1:8080/iFitDiet2/AccountServlet?cmd=register";//服务器地址，测试地址
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //将注册信息添加到参数中
        params.put("username", userBean.getUserName());
        params.put("sex", userBean.getSex());
        params.put("password", userBean.getPsw());
        params.put("phone", userBean.getPhone());
        params.put("portrait", userBean.getAvatar());
        params.put("email", userBean.getEmail());
        params.put("hometown", userBean.getHometown());
        params.put("birthday", userBean.getBirthday());
        client.post(reg_url, params, new AsyncHttpResponseHandler() {//用Http POST方式提交到服务器
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                isRegistered = true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                isRegistered = false;
            }
        });
        return isRegistered;
    }

    public boolean login(LoginUserBean userBean) {//登陆方法
        String reg_url = "http://172.17.215.1:8080/iFitDiet2/AccountServlet?cmd=login";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", userBean.getUserName());
        params.put("password", userBean.getPsw());
        client.post(reg_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    isLogin = true;
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
        System.out.println("RETURN++++++++++++++++++++++++++++++++++++++++++" + isLogin);
        return isLogin;
    }

    public void getImages(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
// 指定文件类型
        String[] allowedContentTypes = new String[]{"image/png", "image/jpeg"};
// 获取二进制数据如图片和其他文件
        client.get(url, new BinaryHttpResponseHandler(allowedContentTypes) {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] binaryData) {
                String tempPath = Environment.getExternalStorageDirectory()
                        .getPath() + "/temp.jpg";
                // 下载成功后需要做的工作
                Bitmap bmp = BitmapFactory.decodeByteArray(binaryData, 0,
                        binaryData.length);

                File file = new File(tempPath);
                // 压缩格式
                Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
                // 压缩比例
                int quality = 100;
                try {
                    // 若存在则删除
                    if (file.exists())
                        file.delete();
                    // 创建文件
                    file.createNewFile();
                    //
                    OutputStream stream = new FileOutputStream(file);
                    // 压缩输出
                    bmp.compress(format, quality, stream);
                    // 关闭
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] binaryData, Throwable error) {
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);
                int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);

            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                // 返回重试次数
            }

        });
    }


    public void getUserInfo() {
        //TODO
    }

}
