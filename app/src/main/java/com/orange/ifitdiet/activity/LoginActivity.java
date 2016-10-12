package com.orange.ifitdiet.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.LoginUserBean;
import com.orange.ifitdiet.util.NetUtil;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private CheckBox cb_remember;
    private EditText et_username;
    private EditText et_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("users",MODE_PRIVATE);
        editor = sp.edit();
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);
        et_username= (EditText) findViewById(R.id.et_username);
        et_psw= (EditText) findViewById(R.id.et_psw);
        readAccount();

    }

    private void readAccount(){
        et_username.setText(sp.getString("userName",""));
        et_psw.setText(sp.getString("psw",""));
        cb_remember.setChecked(sp.getBoolean("isChecked",false));
    }

    public void login(View v){
        final String userName=et_username.getText().toString().trim();
        final String psw=et_psw.getText().toString().trim();
        LoginUserBean userBean=new LoginUserBean();
        userBean.setUserName(userName);
        userBean.setPsw(psw);
        boolean success=new NetUtil().login(userBean);
        if(success) {
            Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
            cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(cb_remember.isChecked()){
                        editor.putBoolean("isChecked",true);
                        editor.putString("userName",userName);
                        editor.putString("psw",psw);
                        editor.commit();
                    }
                }
            });
        }else{
            Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
        }
    }
}
