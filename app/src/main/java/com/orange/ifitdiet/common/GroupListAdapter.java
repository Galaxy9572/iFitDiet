package com.orange.ifitdiet.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orange.ifitdiet.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 廖俊瑶 on 2016/10/31.
 */

public class GroupListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> item_list;

    public final class ListItemView {                //自定义控件集合
        public ImageView iv_groupImg;//群组头像
        public TextView tv_groupName;
        public TextView tv_shareGroup;//分享此群组
        public TextView tv_people;//群组人数
        public TextView tv_gathered;//聚餐次数
        public ImageView iv_shareGroup;//分享按钮
        public TextView tv_invite;
        public TextView tv_exitGroup;
    }

    public GroupListAdapter(Context context, List<Map<String, Object>> item_list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.item_list = item_list;
    }

    @Override
    public int getCount() {
        return item_list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        ListItemView listItemView = null;
        if (view == null) {
            listItemView = new ListItemView();
            view = inflater.inflate(R.layout.list_item_group, null);
            listItemView.iv_groupImg = (ImageView) view.findViewById(R.id.iv_group);
            listItemView.tv_groupName = (TextView) view.findViewById(R.id.tv_groupName);
            listItemView.tv_people = (TextView) view.findViewById(R.id.tv_people);
            listItemView.tv_invite = (TextView) view.findViewById(R.id.tv_invite);
            listItemView.tv_gathered = (TextView) view.findViewById(R.id.tv_gathered);
            listItemView.tv_exitGroup = (TextView) view.findViewById(R.id.tv_exitGroup);
            listItemView.iv_shareGroup = (ImageView) view.findViewById(R.id.iv_shareGroup);
            listItemView.tv_shareGroup = (TextView) view.findViewById(R.id.tv_shareGroup);
            view.setTag(listItemView);
        } else {
            listItemView = (ListItemView) view.getTag();
        }
        listItemView.iv_groupImg.setBackgroundResource((Integer) item_list.get(position).get("groupImg"));
        listItemView.tv_groupName.setText((String) item_list.get(position).get("groupName"));
        listItemView.tv_people.setText((String) item_list.get(position).get("people"));
        listItemView.tv_gathered.setText((String) item_list.get(position).get("gathered"));
        return view;
    }
}
