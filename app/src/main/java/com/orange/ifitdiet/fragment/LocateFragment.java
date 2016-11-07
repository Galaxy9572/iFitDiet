package com.orange.ifitdiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.activity.BusinessActivity;
import com.orange.ifitdiet.activity.MainActivity;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.domain.LocationBean;
import com.orange.ifitdiet.domain.WeatherBean;
import com.orange.ifitdiet.util.LocateUtil;

public class LocateFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View v;
    private BeanPool beanPool;
    private WeatherBean weatherBean;
    private TextView tv_location;
    private TextView tv_weather;
    private SwipeRefreshLayout mSwipeLayout;
    private String province;//省
    private String city;//市
    private String district;//区
    private String street;//街道
    private String weather;
    private String temperature;
    private LocateUtil locateUtil;
    private LocationBean locationBean;


    public LocateFragment() {
        beanPool = MainActivity.getBeanPool();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_locate, container, false);
        mSwipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_ref);
        mSwipeLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.white,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setProgressBackgroundColor(R.color.white); // 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小

        tv_location = (TextView) v.findViewById(R.id.tv_location);
        tv_weather = (TextView) v.findViewById(R.id.tv_weather);
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                locationBean = (LocationBean) beanPool.getBeanMap().get("locationBean");
                weatherBean = (WeatherBean) beanPool.getBeanMap().get("weatherBean");
                if (locationBean != null) {
                    province = locationBean.getProvince();//省信息
                    city = locationBean.getCity();//城市信息
                    district = locationBean.getDistrict();//城区信息
                    street = locationBean.getStreet();//街道信息
                    final String location = province + city + district + street;
                    weather = weatherBean.getWeather();
                    temperature = weatherBean.getTemperature();

                    tv_location.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_location.setText("当前位置：" + province + city + district + street);
                        }
                    });
                    tv_weather.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_weather.setText("当前天气：" + weather + "，" + temperature + "℃");
                        }
                    });

                    Log.e("位置", location);
                }
                Looper.loop();
            }
        }.start();

        RelativeLayout imv1 = (RelativeLayout) v.findViewById(R.id.no1);
        imv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(0);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv2 = (RelativeLayout) v.findViewById(R.id.no2);
        imv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(1);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv3 = (RelativeLayout) v.findViewById(R.id.no3);
        imv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(2);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv4 = (RelativeLayout) v.findViewById(R.id.no4);
        imv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(3);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv5 = (RelativeLayout) v.findViewById(R.id.no5);
        imv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(4);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });

        return v;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
//                locateUtil = new LocateUtil(getActivity());
//                locateUtil.locate(getActivity());
//                if (beanPool.getBeanMap().get("locationBean") != null) {
//                    locateUtil.locate(getContext());
//                    province = locationBean.getProvince();//省信息
//                    city = locationBean.getCity();//城市信息
//                    district = locationBean.getDistrict();//城区信息
//                    street = locationBean.getStreet();//街道信息
//                    String location = province + city + district + street;
//                    tv_location.setText("当前位置：" + location);
//                    weather = weatherBean.getWeather();
//                    temperature = weatherBean.getTemperature();
//                    tv_weather.setText("当前天气：" + weather + "，" + temperature + "℃");
//                    Log.e("位置下拉", location);
//                }
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000); // 5秒后发送消息，停止刷新
    }
}
