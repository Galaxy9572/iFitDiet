package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.util.CommUtil;

public class CommActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);
        Button bt_comm = (Button) findViewById(R.id.bt_comm);
        final TextView tv_deviceIP = (TextView) findViewById(R.id.tv_deviceIP);
        final TextView tv_deviceData = (TextView) findViewById(R.id.tv_deviceData);
        new MyThread().start();
    }
}

class MyThread extends Thread {
    public void run() {
        new CommUtil();
    }
}