package com.orange.ifitdiet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.activity.MainActivity;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.common.MyImageView;
import com.orange.ifitdiet.util.NetUtil;

public class RecommendFragment extends Fragment {
    private int[] imgs = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
    };
    private BeanPool beanPool =MainActivity.getBeanPool();
    private NetUtil netUtil= (NetUtil) MainActivity.getUtilPool().getUtilMap().get("netUtil");
    private View v;

    public RecommendFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recommend, container, false);
        final TextView tv_health_tips= (TextView) v.findViewById(R.id.tv_health_tips);
        int r = (int) (Math.random() * 8);
        MyImageView imv = (MyImageView) v.findViewById(R.id.iv_random);
        imv.setImageResource(imgs[r]);
        Button btn = (Button) v.findViewById(R.id.bt_change);
        final TextView tv_behaviour_run = (TextView) v.findViewById(R.id.tv_behaviour_run);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = (int) (Math.random() * 8);
                MyImageView imv = (MyImageView) v.findViewById(R.id.iv_random);
                imv.setImageResource(imgs[r]);
            }
        });
//        WeatherBean weatherBean= (WeatherBean) beanPool.getBeanMap().get("weatherBean");
//        netUtil.getLifeTips(weatherBean);
//        final TipBean tipBean = (TipBean) beanPool.getBeanMap().get("tipBean");
        tv_health_tips.setText("今天天气不错，建议穿着薄外套加T恤");
//        tv_health_tips.setText(tipBean.getProvinceInfo()+"\n"+tipBean.getTemperatureInfo());
//        new Thread() {
//            public void run() {
//                Looper.prepare();
//                try {
//                    tv_health_tips.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            WeatherBean weatherBean = (WeatherBean) beanPool.getBeanMap().get("weatherBean");
//                            netUtil.getLifeTips(weatherBean);
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            final TipBean tipBean = (TipBean) beanPool.getBeanMap().get("tipBean");
//                            tv_health_tips.setText(tipBean.getProvinceInfo() + "\n" + tipBean.getTemperatureInfo());
//                        }
//                    });
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Looper.loop();
//            }
//        }.start();
        return v;
    }

}