package com.orange.ifitdiet.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.SocialMsgBean;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendSocialMsgActivity extends AppCompatActivity {

    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_social_msg);
        Button bt_addImage= (Button) findViewById(R.id.bt_addImage);
        Button bt_send=(Button)findViewById(R.id.bt_send);
        EditText et_msg= (EditText) findViewById(R.id.et_msg);
        final String msg=et_msg.getText().toString();
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=sdf.format(date);
                iv_image.setDrawingCacheEnabled(true);
                Bitmap bitmap=Bitmap.createBitmap(iv_image.getDrawingCache());
                SocialMsgBean socialMsgBean=new SocialMsgBean(msg,time,bitmap);
            }
        });
        iv_image = (ImageView) findViewById(R.id.iv_image);
        bt_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                /* 将Bitmap设定到ImageView */
                iv_image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
