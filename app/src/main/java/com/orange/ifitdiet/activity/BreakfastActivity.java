package com.orange.ifitdiet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orange.ifitdiet.R;

public class BreakfastActivity extends AppCompatActivity {
    private int[] imgs = {
            R.drawable.bf1,
            R.drawable.bf2,
            R.drawable.bf3,
            R.drawable.bf4,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        int r = (int) (Math.random() *4);
        ImageView imv = (ImageView)findViewById(R.id.iv_breakfast);
        imv.setImageResource(imgs[r]);

    }
    public void bf_change(View view){
        int r = (int) (Math.random() *4);
        ImageView imv = (ImageView)findViewById(R.id.iv_breakfast);
        imv.setImageResource(imgs[r]);
    }
    public void share(View view){
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }
}
