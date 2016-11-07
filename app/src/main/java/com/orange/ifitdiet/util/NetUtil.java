package com.orange.ifitdiet.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orange.ifitdiet.activity.MainActivity;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.domain.GroupBean;
import com.orange.ifitdiet.domain.UserBean;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static java.lang.Integer.valueOf;

/**
 * 网络连接的工具类，使用开源项目AsyncHttpClient
 * Created by 廖俊瑶 on 2016/9/2.
 */
public class NetUtil {
    private String serverUrl = "http://172.21.127.1:8080/iFitDiet/";
    private Context context;
    private BeanPool beanPool = MainActivity.getBeanPool();
    private static int group_count = 0;

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
        params.put("iv_groupImg", userBean.getPortrait());
        params.put("hometown", userBean.getHometown());
        params.put("birthday", userBean.getBirthday());
        params.put("taste", userBean.getTaste());
        params.put("weight", userBean.getWeight());
        params.put("height", userBean.getHeight());
        client.post(reg_url, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject obj) {
                if (statusCode == 200 || statusCode == 304) {
                    try {
                        // 获取具体的一个JSONObject对象
                        Log.e("name", obj.getString("name"));
                        Log.e("password", obj.getString("password"));
                        Log.e("loginName", obj.getString("loginName"));
                        String id = obj.getString("id");
                        String name = obj.getString("name");
                        String sex = obj.getString("sex");
                        String password = obj.getString("password");
                        String loginName = obj.getString("loginName");
                        String birthday = obj.getString("birthday");
                        String hometown = obj.getString("hometown");
                        String taste = obj.getString("taste");
                        int height = Integer.valueOf(obj.getString("height"));
                        int weight = Integer.valueOf(obj.getString("weight"));
                        beanPool.getBeanMap().put("user", new UserBean(id, name, valueOf(sex), password, loginName, birthday, hometown, taste, height, weight));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
    public void login(final UserBean userBean) {
        String reg_url = serverUrl + "UserServlet?type=login";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginName", userBean.getLoginName());
        params.put("password", userBean.getPassword());

        client.post(reg_url, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject obj) {
                if (statusCode == 200 || statusCode == 304) {
                    try {
                        // 获取具体的一个JSONObject对象
                        Log.e("id", obj.getString("id"));
                        Log.e("name", obj.getString("name"));
                        Log.e("sex", obj.getString("sex"));
                        Log.e("password", obj.getString("password"));
                        Log.e("loginName", obj.getString("loginName"));
                        userBean.setId(obj.getString("id"));
                        userBean.setName(obj.getString("name"));
                        String sex = obj.getString("sex");
                        int sex2 = valueOf(sex);
                        userBean.setSex(sex2);
                        userBean.setPassword(obj.getString("password"));
                        userBean.setLoginName(obj.getString("loginName"));
                        userBean.setBirthday(obj.getString("birthday"));
                        userBean.setHometown(obj.getString("hometown"));
                        userBean.setTaste(obj.getString("taste"));
                        userBean.setHeight(Integer.valueOf(obj.getString("height")));
                        userBean.setWeight(Integer.valueOf(obj.getString("weight")));
                        beanPool.getBeanMap().put("user", userBean);
                        getGroup(userBean);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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

    /**
     * 创建新群组
     *
     * @param groupBean
     */
    public void createGroup(GroupBean groupBean) {
        String createUrl = serverUrl + "GroupServlet?type=add";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("name", groupBean.getName());
        params.put("userid", groupBean.getUserID());
        client.post(createUrl, params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject obj) {
                group_count++;
                if (statusCode == 200 || statusCode == 304) {
                    try {
                        String id = obj.getString("id");
                        String userid = obj.getString("userid");
                        String name = obj.getString("name");
                        String date = obj.getString("date");
                        String number = obj.getString("number");
                        QRCodeUtil.createQRImage(id, 300, 300, null, Environment.getExternalStorageDirectory().getPath());
//                        String qr = obj.getString("qr");
                        beanPool.getBeanMap().put(name, new GroupBean(name, userid, date, valueOf(number)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public List<GroupBean> getGroup(UserBean userBean) {
        String getUrl = serverUrl + "GroupServlet?type=query";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("userid", userBean.getId());
        client.post(getUrl, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONArray array) {
                Log.e("JSON长度：：：：",array.length()+"");
                for (int i = 0; i < array.length(); i++) {
                    try {
                        // 获取具体的一个JSONObject对象
                        JSONObject obj = array.getJSONObject(i);
                        //JSONObject对象get(“属性名”)，getString(“属性名”),getInt(“属性名”)等方法来获取指定属性名的值
                        Log.e("群组：：：：","id" + obj.getString("id") + "__name：" + obj.getString("name")
                                + "__userid：" + obj.getString("userid")
                                + "__date：" + obj.getString("date") + "__number：" + obj.getString("number")
                                + "__qRCode：" + obj.getString("qRCode"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        return null;
    }


    public List getBusiness() {

        return null;
    }

    // 检测网络
    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
