package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.domain.UserBean;

public class PersonalInfoActivity extends AppCompatActivity {
    private BeanPool beanPool=MainActivity.getBeanPool();
    private UserBean user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        user= (UserBean) beanPool.getBeanMap().get("user");
        TextView tv_userName = (TextView) findViewById(R.id.tv_username);
        TextView tv_sex = (TextView) findViewById(R.id.tv_sex);
        TextView tv_psw = (TextView) findViewById(R.id.tv_psw);
        TextView tv_taste = (TextView) findViewById(R.id.tv_taste);
        TextView tv_loginName = (TextView) findViewById(R.id.tv_loginName);
        TextView tv_hometown = (TextView) findViewById(R.id.tv_hometown);
        TextView tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        TextView tv_height = (TextView) findViewById(R.id.tv_height);
        TextView tv_weight = (TextView) findViewById(R.id.tv_weight);
        tv_userName.setText("昵称："+user.getName());
        if(user.getSex()==0){
            tv_sex.setText("性别：男");
        }else{
            tv_sex.setText("性别：女");
        }
        tv_psw.setText("密码：******");
        tv_taste.setText("口味："+user.getTaste());
        tv_loginName.setText("登录名："+user.getLoginName());
        tv_hometown.setText("故乡："+user.getHometown());
        tv_birthday.setText("生日："+user.getBirthday());
        tv_height.setText("身高："+user.getHeight()+"cm");
        tv_weight.setText("体重："+user.getWeight()+"kg");
    }
}
