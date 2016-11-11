package com.orange.ifitdiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.StepService;

import static com.orange.ifitdiet.common.StepDetector.CURRENT_STEP;

public class HealthFragment extends Fragment {
    private int total_step = 0;
    private Thread thread;
    private int Type = 1;
    private int calories = 0;
    private int step_length = 50;
    private int weight = 70;
    private boolean flag = true;// 来判断第三个页面是否开启动画
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total_step = CURRENT_STEP;
            if (Type == 1) {
                tv_steped.setText(total_step + "");//将步数显示到软件UI
                calories = (int) (weight * total_step * step_length * 0.01 * 0.01);//卡路里消耗公式
                tv_calories.setText(calories + "");//将消耗卡路里显示到软件UI
            } else if (Type == 3) {
                if (flag) {
                    flag = false;
                }
            }
        }
    };
    private TextView tv_steped;
    private TextView tv_calories;
    private TextView tv_heartdata;

    public HealthFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);
        tv_steped = (TextView) v.findViewById(R.id.tv_steped);
        tv_calories = (TextView) v.findViewById(R.id.tv_calories);
        tv_heartdata = (TextView) v.findViewById(R.id.tv_heartdata);
        int heartrate = (int) (Math.random() * 15) + 65;
        tv_heartdata.setText(heartrate + "次/分");
        Intent intent = new Intent(getActivity(), StepService.class);
        getActivity().startService(intent);
        mThread();
        tv_calories.setText(calories + "");
//        new Thread() {
//            public void run() {
//        Looper.prepare();
//                while (true) {
//                    try {
//                        healthBean = (HealthBean) MainActivity.getBeanPool().getBeanMap().get("healthBean");
//                        HealthBean healthBean = new HealthBean(67);
//
//                    } catch (Exception e) {
//                        Log.e("HealthBean", "没获得");
//                        e.printStackTrace();
//                    }
//                    Looper.loop();
//                }
//            }
//        }.start();
        return v;
    }

    private void mThread() {
        if (thread == null) {

            thread = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (StepService.flag) {
                            Message msg = new Message();
                            handler.sendMessage(msg);
                        }
                    }
                }
            });
            thread.start();
        }
    }


}
