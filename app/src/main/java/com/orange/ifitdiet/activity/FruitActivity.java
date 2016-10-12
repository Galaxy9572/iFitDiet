package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orange.ifitdiet.R;

/**
 * 水果推荐Ativity
 * 廖俊瑶
 */

public class FruitActivity extends AppCompatActivity {
    private int[] imgs = {//初始化图片资源
            R.drawable.ft1,
            R.drawable.ft2,
            R.drawable.ft3,
            R.drawable.ft4,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        int r = (int) (Math.random() *4);
        ImageView imv = (ImageView)findViewById(R.id.iv_fruit);
        imv.setImageResource(imgs[r]);//设置图片资源

    }
    public void ft_change(View view){
        int r = (int) (Math.random() *4);
        ImageView imv = (ImageView)findViewById(R.id.iv_fruit);
        imv.setImageResource(imgs[r]);//设置图片资源
    }
    public void share(View view){
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }
}