package com.orange.ifitdiet.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.Zxing.CaptureActivity;
import com.orange.ifitdiet.R;
import com.orange.ifitdiet.activity.GroupDialogActivity;
import com.orange.ifitdiet.activity.LoginActivity;
import com.orange.ifitdiet.activity.MainActivity;
import com.orange.ifitdiet.activity.SmallGroupActivity;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.common.GroupListAdapter;
import com.orange.ifitdiet.common.ListPool;
import com.orange.ifitdiet.domain.GroupBean;
import com.orange.ifitdiet.domain.UserBean;
import com.orange.ifitdiet.util.NetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GroupFragment extends Fragment {
    private ListView listView;
    private GroupListAdapter adapter;
    private List<Map<String,Object>> item_list;
    private NetUtil netUtil= (NetUtil) MainActivity.getUtilPool().getUtilMap().get("netUtil");
    private BeanPool beanPool=MainActivity.getBeanPool();
    private ListPool listPool=MainActivity.getListPool();
    private UserBean userBean;
    public GroupFragment() {

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
        try{
            userBean= (UserBean) beanPool.getBeanMap().get("user");
            netUtil.getGroup(userBean);
            item_list=getData(userBean);
            adapter=new GroupListAdapter(getContext(),item_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent().setClass(getContext(), SmallGroupActivity.class));
                }
            });
        }catch (Exception e){
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setTitle("提示").setMessage("你未登录,是否登录?").setPositiveButton("登录", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent().setClass(getContext(), LoginActivity.class));
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
            e.printStackTrace();
        }
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

        return v;
    }

    public List<Map<String, Object>> getData(UserBean userBean){
        List<Map<String, GroupBean>> list_group=listPool.getListMap().get("groupList");
        List<Map<String, Object>> list=new ArrayList<>();
        Map<String, GroupBean> map_group=list_group.get(0);
        Map<String, Object> map=new HashMap<>();
        Set set = map_group.keySet();
        for(Iterator i = set.iterator(); i.hasNext();)
        {
            String key = (String)i.next();
            GroupBean groupBean=map_group.get(key);
            map.put("id",groupBean.getId());
            map.put("groupImg", R.drawable.group);
            map.put("groupName", groupBean.getName());
            map.put("people", "人数："+groupBean.getNumber());
            map.put("gathered", "已聚餐：："+1);
            list.add(map);
        }
        return list;
    }
}