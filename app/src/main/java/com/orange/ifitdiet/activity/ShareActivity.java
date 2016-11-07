package com.orange.ifitdiet.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.Constants;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

public class ShareActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Window window;

    private IWeiboShareAPI iWeiboShareAPI;
    private Button bt_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        bt_share = (Button) findViewById(R.id.bt_share);
        initView();
    }

    private void initView() {
        Context context = this;
        iWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context, Constants.Weibo_APP_ID);//这里输入App Key
        iWeiboShareAPI.registerApp();//将应用注册到微博客户端
        alertDialog = new AlertDialog.Builder(context).create();
        window = alertDialog.getWindow();
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareAlertDialog();
            }
        });
    }

    /**
     * 显示分享的AlertDialog
     */
    private void showShareAlertDialog() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        window.setLayout((int) (screenWidth * 0.95F), LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(R.layout.alert_dialog_share_custom);
        TextView tvMessage = (TextView) window.findViewById(R.id.textview_message_alert_dialog_share_custom);
        ImageView iv_weibo = (ImageView) window.findViewById(R.id.iv_weibo);
        tvMessage.setText("分享到");
        iv_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        sendMultiMessageToWeibo(true, true);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        alertDialog.dismiss();
                    }
                }.execute();
            }
        });
    }

    /**
     * 发送多种消息到微博
     *
     * @param hasText：是否有文本
     * @param hasImage：是否有图片
     */
    private void sendMultiMessageToWeibo(boolean hasText, boolean hasImage) {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();//初始化微博的分享消息
        if (hasText) {
            weiboMessage.textObject = getTextObj();
        }
        if (hasImage) {
            weiboMessage.mediaObject = getImageObj();
        }
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        boolean isSuccess = iWeiboShareAPI.sendRequest(this, request);//发送请求消息到微博，唤起微博分享界面
    }

    /**
     * 获取文本信息对象
     *
     * @return TextObject
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = "iFitDiet这个应用不错哦，快来下载：http://android.myapp.com/myapp/detail.htm?apkName=com.orange.ifitdiet";//这里输入文本
        return textObject;
    }

    /**
     * 获取图片信息对象
     *
     * @return ImageObject
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = null;
        try {
            bitmap=BitmapFactory.decodeResource(this.getApplicationContext().getResources(),R.drawable.campaign);
            if (bitmap.getByteCount() > 4096000) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = bitmap.getByteCount() / 4096000;//缩放比例
                options.inJustDecodeBounds = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageObject.setImageObject(bitmap);
        return imageObject;
    }
}
