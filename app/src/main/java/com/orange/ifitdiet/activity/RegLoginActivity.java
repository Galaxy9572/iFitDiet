package com.orange.ifitdiet.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orange.ifitdiet.R;

public class RegLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_login);
        TextView tv_new_reg= (TextView) findViewById(R.id.tv_new_reg);
        TextView tv_old_login= (TextView) findViewById(R.id.tv_old_login);
        tv_new_reg.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tv_old_login.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
    }
    public void reg(View v){
        startActivity(new Intent().setClass(RegLoginActivity.this, RegisterActivity.class));
    }
    public void login(View v){
        startActivity(new Intent().setClass(RegLoginActivity.this, LoginActivity.class));
    }
}
