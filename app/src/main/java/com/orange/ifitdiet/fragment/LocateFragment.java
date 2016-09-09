package com.orange.ifitdiet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.util.LocateUtil;

public class LocateFragment extends Fragment {
    private View v;
    private TextView tv_location;
    private String province;//省
    private String city;//市
    private String district;//区
    private String street;//街道
    private LocateUtil locateUtil;

    public LocateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locateUtil = new LocateUtil(getActivity());
        locateUtil.locate(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v= inflater.inflate(R.layout.fragment_locate,container, false);
        tv_location= (TextView) v.findViewById(R.id.tv_location);

        province=locateUtil.getProvince();//省信息
        city=locateUtil.getCity();//城市信息
        district=locateUtil.getDistrict();//城区信息
        street=locateUtil.getStreet();//街道信息
//        tv_location.setText(province);
        System.out.println(province+city+street);
        return v;
    }
}
