package com.orange.ifitdiet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orange.ifitdiet.R;
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
       locationBean = (LocationBean) MainActivity.getBeanPool().getBean("locationBean");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v= inflater.inflate(R.layout.fragment_locate,container, false);
        tv_location= (TextView) v.findViewById(R.id.tv_location);

        province=locationBean.getProvince();//省信息
        city=locationBean.getCity();//城市信息
        district=locationBean.getDistrict();//城区信息
        street=locationBean.getStreet();//街道信息
//        tv_location.setText("当前位置："+province);
        Log.e("位置",province+city+street);
        return v;
    }
}
