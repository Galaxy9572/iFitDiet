package com.orange.ifitdiet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.MyImageView;

public class BusinessActivity extends AppCompatActivity {
    private  int[] imgs = {//初始化图片资源
            R.drawable.zzy,
            R.drawable.dbjzg,
            R.drawable.hdjtdf,
            R.drawable.jhd,
            R.drawable.ljkr,
    };
    private static int r=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        MyImageView imv = (MyImageView)findViewById(R.id.iv_business);
        imv.setImageResource(imgs[r]);//设置图片
    }
    public static void setR(int R){
        r = R;
    }
}
