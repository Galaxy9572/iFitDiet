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
import com.orange.ifitdiet.common.StepDetector;
import com.orange.ifitdiet.common.StepService;

public class HealthFragment extends Fragment {
    private View view;
    private int total_step = 0;
    private Thread thread;
    private int Type = 1;
    private int calories = 0;
    private int step_length = 50;
    private int weight = 70;
    private String test;
    private boolean flag = true;// 来判断第三个页面是否开启动画
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            total_step = StepDetector.CURRENT_STEP;
            if (Type == 1) {
                tv_steped.setText(total_step);
                System.out.println(total_step+"ssssssssssssssssssssssssssssssssss");
            } else if (Type == 2) {
                calories = (int) (weight * total_step * step_length * 0.01 * 0.01);
                tv_calories_burn.setText(calories);
            } else if (Type == 3) {
                if (flag) {
                    flag = false;
                }

            }

        }

    };
    private TextView tv_steped;
    private TextView tv_calories_burn;

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_health, container, false);
        tv_steped = (TextView) v.findViewById(R.id.tv_steped);
        tv_calories_burn = (TextView) v.findViewById(R.id.tv_calories_burn);
        Intent intent = new Intent(getActivity(), StepService.class);
        getActivity().startService(intent);
        mThread();
        tv_calories_burn.setText("aaaa");
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
