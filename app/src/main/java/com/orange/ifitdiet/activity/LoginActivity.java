package com.orange.ifitdiet.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.UserBean;
import com.orange.ifitdiet.util.NetUtil;

public class LoginActivity extends AppCompatActivity {
    private NetUtil netUtil = (NetUtil) MainActivity.getUtilPool().getUtilMap().get("netUtil");
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private CheckBox cb_remember;
    private EditText et_username;
    private EditText et_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("users", MODE_PRIVATE);
        editor = sp.edit();
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);
        et_username = (EditText) findViewById(R.id.et_username);
        et_psw = (EditText) findViewById(R.id.et_psw);
        readAccount();

    }

    private void readAccount() {
        et_username.setText(sp.getString("userName", ""));
        et_psw.setText(sp.getString("psw", ""));
        cb_remember.setChecked(sp.getBoolean("isChecked", false));
    }

    public void login(View v) {
        final String userName = et_username.getText().toString().trim();
        final String psw = et_psw.getText().toString().trim();
        UserBean userBean = new UserBean();
        userBean.setLoginName(userName);
        userBean.setPassword(psw);
        netUtil.login(userBean);
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserBean user = null;
                try {
                    user = (UserBean) MainActivity.getBeanPool().getBeanMap().get("user");
                } catch (Exception e) {
                }
                try {
                    if (!user.getId().equals("") || user.getId() != null) {
                        Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
//                        netUtil.getGroup(user);
                        cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (cb_remember.isChecked()) {
                                    editor.putBoolean("isChecked", true);
                                    editor.putString("userName", userName);
                                    editor.putString("psw", psw);
                                    editor.commit();
                                }
                            }
                        });
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

