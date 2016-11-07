package com.orange.ifitdiet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.Zxing.CaptureActivity;
import com.orange.ifitdiet.R;
import com.orange.ifitdiet.activity.GroupDialogActivity;
import com.orange.ifitdiet.common.GroupListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GroupFragment extends Fragment {
    private ListView listView;
    private GroupListAdapter adapter;
    private List<Map<String,Object>> item_list;
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
        View v = inflater.inflate(R.layout.fragment_group, null);
        listView= (ListView) v.findViewById(R.id.lv_groups);
        item_list=getData();
        adapter=new GroupListAdapter(getContext(),item_list);
        Button bt_scan = (Button) v.findViewById(R.id.bt_scan);

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getActivity(), CaptureActivity.class));
            }
        });

        Button bt_create_group = (Button) v.findViewById(R.id.bt_create_group);
        bt_create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getContext(), GroupDialogActivity.class));
            }
        });
        listView.setAdapter(adapter);
        return v;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("groupImg", R.drawable.group);
            map.put("groupName", "这是一个群组"+i);
            map.put("people", "人数："+i);
            map.put("gathered", "已聚餐：："+i);
            list.add(map);
        }
        return list;
    }
}