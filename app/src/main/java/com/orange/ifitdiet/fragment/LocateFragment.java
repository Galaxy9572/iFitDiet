package com.orange.ifitdiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.activity.BusinessActivity;
import com.orange.ifitdiet.activity.MainActivity;
import com.orange.ifitdiet.domain.LocationBean;

public class LocateFragment extends Fragment {
    private View v;
    private LocationBean locationBean;
    private TextView tv_location;
    private String province;//省
    private String city;//市
    private String district;//区
    private String street;//街道

    public LocateFragment() {
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
        locationBean = (LocationBean) MainActivity.getBeanPool().getBeanMap().get("locationBean");
        v= inflater.inflate(R.layout.fragment_locate,container, false);
        tv_location= (TextView) v.findViewById(R.id.tv_location);

        province=locationBean.getProvince();//省信息
        city=locationBean.getCity();//城市信息
        district=locationBean.getDistrict();//城区信息
        street=locationBean.getStreet();//街道信息
        String location=province+city+district+street;
        tv_location.setText(location);
        Log.e("位置",location);
        RelativeLayout imv1 = (RelativeLayout)v.findViewById(R.id.no1);
        imv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(0);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv2 = (RelativeLayout)v.findViewById(R.id.no2);
        imv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(1);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv3 = (RelativeLayout)v.findViewById(R.id.no3);
        imv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(2);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv4 = (RelativeLayout)v.findViewById(R.id.no4);
        imv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(3);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        RelativeLayout imv5 = (RelativeLayout)v.findViewById(R.id.no5);
        imv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessActivity.setR(4);
                startActivity(new Intent().setClass(getActivity(), BusinessActivity.class));
            }
        });
        return v;
    }
}
