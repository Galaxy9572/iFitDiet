package com.orange.ifitdiet.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orange.ifitdiet.domain.LoginUserBean;
import com.orange.ifitdiet.domain.UserBean;

import org.apache.http.Header;
import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 网络连接的工具类，使用开源项目AsyncHttpClient
 * Created by 廖俊瑶 on 2016/9/2.
 */
public class NetUtil {
    private boolean isLogin;//是否登陆成功
    private String serverUrl = "http://172.21.127.1:8080/iFitDiet/";
    private Context context;

    public NetUtil(Context context) {
        this.context = context;
    }

    /**
     * 注册一个新的用户
     *
     * @param userBean
     * @return 陈功返回true，否则返回false
     */
    public void register(UserBean userBean) {//注册方法
        String reg_url = serverUrl + "UserServlet?type=reg";//服务器地址，测试地址
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //将注册信息添加到参数中
        params.put("name", userBean.getName());
        params.put("sex", userBean.getSex());
        params.put("password", userBean.getPassword());
        params.put("loginName", userBean.getLoginName());
        params.put("portrait", userBean.getPortrait());
        params.put("hometown", userBean.getHometown());
        params.put("birthday", userBean.getBirthday());
        params.put("taste", userBean.getTaste());
        client.post(reg_url, params, new AsyncHttpResponseHandler() {//用Http POST方式提交到服务器
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200 || statusCode == 304) {
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
//                Log.e("返回的数据",new String(headers));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
    private void getData(){
        String reg_url = serverUrl + "UserServlet?type=reg";//服务器地址，测试地址
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(serverUrl,new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200||statusCode==304) {
                    //TODO
                }
            }
        });
    }


    /**
     * 用户登录方法
     *
     * @param userBean
     * @return 陈功返回true，否则返回false
     */
    public boolean login(LoginUserBean userBean) {
        String reg_url = serverUrl + "UserServlet?type=login";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", userBean.getUserName());
        params.put("password", userBean.getPsw());
        client.post(reg_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200 || statusCode == 304) {
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
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
