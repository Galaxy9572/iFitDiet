package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.HealthBean;
import com.orange.ifitdiet.util.CommUtil;

public class CommActivity extends AppCompatActivity {
    String data;
    private HealthBean healthBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);
        final TextView tv_deviceIP = (TextView) findViewById(R.id.tv_deviceIP);
        final TextView tv_deviceData = (TextView) findViewById(R.id.tv_deviceData);

        new Thread() {
            public void run() {
                Looper.prepare();
                CommUtil commUtil=new CommUtil(getApplicationContext());
                while (true) {
                    try {
                        data = commUtil.receiveData();
//                        healthBean=new HealthBean(valueOf(data));
//                        MainActivity.getBeanPool().getBeanMap().put("healthBean",healthBean);
                        Toast.makeText(CommActivity.this, data, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                Looper.loop();
            }
        }.start();
    }
}

