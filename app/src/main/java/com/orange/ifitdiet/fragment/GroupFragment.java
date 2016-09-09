package com.orange.ifitdiet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.GroupBean;

import java.util.ArrayList;
import java.util.List;


public class GroupFragment extends Fragment {
    private List<GroupBean> groupList;

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
        groupList = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_group, null);
        ListView lv_group = (ListView) v.findViewById(R.id.lv_group);
        lv_group.setAdapter(new MyAdapter());


        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return groupList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GroupBean groupBean=groupList.get(position);
            View v=null;
            if(convertView==null){
                v=View.inflate(getActivity(),R.layout.group_list_item_card,null);
            }else{
                v=convertView;
            }

            return null;
        }
    }
}