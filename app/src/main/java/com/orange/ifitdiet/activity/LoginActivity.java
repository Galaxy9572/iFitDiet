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
        et_username.setText(sp.getString("username",""));
        et_psw.setText(sp.getString("psw",""));
        cb_remember.setChecked(sp.getBoolean("isChecked",false));
    }

    protected void login(View v){
        cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_remember.isChecked()){
                    editor.putBoolean("isChecked",true);
                    editor.putString("username",et_username.getText().toString());
                    editor.putString("psw",et_psw.toString());
                    editor.commit();
                }
            }
        });
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }
}
