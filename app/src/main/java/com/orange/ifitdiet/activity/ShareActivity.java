package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orange.ifitdiet.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Button bt_share= (Button) findViewById(R.id.bt_share);

    }
    public void share(View v){
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
