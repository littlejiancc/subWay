package com.example.wangxiaojian.subway;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/9.
 */

public class SubwayAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;//外层的数据源
    private List<List<String>> childList;//里层的数据源
    private Context context;
    public SubwayAdapter(Context context, List<String> groupList,List<List<String>> childList ){
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }
    @Override
    public int getGroupCount() {
        return groupList.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.group_item, null);
        //分组名字
        TextView textView = (TextView) convertView.findViewById(R.id.group_textview);
        //子元素的个数
        TextView number = (TextView) convertView.findViewById(R.id.group_number);
        number.setText(childList.get(groupPosition).size()+"站");
        textView.setText(groupList.get(groupPosition));
        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.child_item, null);
        TextView textView = (TextView) view.findViewById(R.id.child_name);
        textView.setText(childList.get(groupPosition).get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
