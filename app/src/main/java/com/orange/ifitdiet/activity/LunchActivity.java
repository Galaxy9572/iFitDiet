package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orange.ifitdiet.R;

public class LunchActivity extends AppCompatActivity {
    private int[] imgs = {//设置图片
            R.drawable.lc1,
            R.drawable.lc2,
            R.drawable.lc3,
            R.drawable.lc4,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        int r = (int) (Math.random() *4);
        ImageView imv = (ImageView)findViewById(R.id.iv_lunch);
        imv.setImageResource(imgs[r]);//设置图片资源

    }
    public void lc_change(View view){
        int r = (int) (Math.random() *4);
        ImageView imv = (ImageView)findViewById(R.id.iv_lunch);
        imv.setImageResource(imgs[r]);
    }
    public void share(View view){
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }
}
