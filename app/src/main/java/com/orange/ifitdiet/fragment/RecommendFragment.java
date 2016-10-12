package com.orange.ifitdiet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.MyImageView;

public class RecommendFragment extends Fragment {
    private  int[] imgs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
    };
    private View v;
    public RecommendFragment() {

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_recommend, container, false);
        int r = (int) (Math.random() *8);
        MyImageView imv = (MyImageView)v.findViewById(R.id.iv_random);
        imv.setImageResource(imgs[r]);
        Button btn = (Button)v.findViewById(R.id.bt_change);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = (int) (Math.random() *8);
                MyImageView imv = (MyImageView)v.findViewById(R.id.iv_random);
                imv.setImageResource(imgs[r]);
            }
        });
        return v;
    }

}