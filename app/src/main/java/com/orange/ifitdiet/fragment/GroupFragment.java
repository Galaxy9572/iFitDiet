package com.orange.ifitdiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.Zxing.CaptureActivity;
import com.orange.ifitdiet.R;
import com.orange.ifitdiet.activity.QRActivity;
import com.orange.ifitdiet.activity.SmallGroupActivity;


public class GroupFragment extends Fragment {

    public GroupFragment() {
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
        View v = inflater.inflate(R.layout.fragment_group, null);
        View share = v.findViewById(R.id.line_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent().setClass(getActivity(), QRActivity.class));
            }
        });
        Button btn = (Button)v.findViewById(R.id.bt_sao);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getActivity(),CaptureActivity.class));
            }
        });
        RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.group_created);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getActivity() , SmallGroupActivity.class));
            }
        });

        return v;
    }


}