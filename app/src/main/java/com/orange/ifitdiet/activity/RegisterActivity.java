package com.orange.ifitdiet.activity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.RegisterUserBean;
import com.orange.ifitdiet.util.NetUtil;

import java.io.FileNotFoundException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_birth = (Button) findViewById(R.id.btn_birth);
        ImageView iv_avator = (ImageView) findViewById(R.id.iv_avatar);
        iv_avator.setOnClickListener(new View.OnClickListener() {
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
                ImageView imageView = (ImageView) findViewById(R.id.iv_avatar);
                /* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void chooseDate(View v) {
        new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            }
        }, 2016, 0, 1).show();
    }

    protected void register(View v) {
        EditText et_userName = (EditText) findViewById(R.id.et_username);
        RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
        RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
        RegisterUserBean userBean = new RegisterUserBean();
        userBean.setUserName(et_userName.getText().toString().trim());
        if (rb_male.isSelected()) {
            userBean.setSex(0);
        } else {
            userBean.setSex(1);
        }
        //TODO

        new NetUtil().register(userBean);
    }
}
