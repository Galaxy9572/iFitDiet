package com.orange.ifitdiet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.orange.ifitdiet.R;


public class RecommendFragment extends Fragment {
    private RollPagerView prv;

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
        View v_recommend = inflater.inflate(R.layout.fragment_recommend, null);
        prv = (RollPagerView) v_recommend.findViewById(R.id.rvp_today);
        //设置播放时间间隔
        prv.setPlayDelay(1000);
        //设置透明度
        prv.setAnimationDurtion(500);
        //设置适配器
        prv.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        prv.setHintView(new IconHintView(this.getActivity(), R.drawable.point_select, R.drawable.point_normal));

        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

}

class TestNormalAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    public int getCount() {
        return imgs.length;
    }
}