package com.example.wangxiaojian.subway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.wangxiaojian.subway.R.layout.station;

/**
 * Created by Wangxiaojian on 2016/12/9.
 */

public class SubwayActivity extends AppCompatActivity {
    private ExpandableListView mExpandableListView;
    private SubwayAdapter mSubwayAdapter;
    private StationDatabaseHelper mStationDatabaseHelper;
    private List<String> groupList=new ArrayList<>();
    private List<List<String>> childList=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        mExpandableListView=(ExpandableListView)findViewById(R.id.expandablelistview);
        mStationDatabaseHelper=new StationDatabaseHelper(this,"Stations.db",null,1);
        initView();
        mSubwayAdapter=new SubwayAdapter(this,groupList,childList);
        mExpandableListView.setAdapter(mSubwayAdapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(SubwayActivity.this,
//                        mSubwayAdapter.getChild(groupPosition,childPosition).toString(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent() ;
                intent.putExtra("data_return_start",mSubwayAdapter.getChild(groupPosition,childPosition).toString());
                intent.putExtra("data_return_end",mSubwayAdapter.getChild(groupPosition,childPosition).toString());
                setResult(RESULT_OK,intent);
                finish();
                return true;
            }
        });
    }
    private void initView() {
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        SQLiteDatabase db = mStationDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("station", null, null, null, null, null, null);
        String[] sta1=new String[mStationDatabaseHelper.getStations1Length()];
        String[] sta2=new String[mStationDatabaseHelper.getStations2Length()];
        String[] sta4=new String[mStationDatabaseHelper.getStations4Length()];
        int k1=0,k2=0,k4=0;
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象
                String name = cursor.getString(cursor.
                        getColumnIndex("name"));
                String line = cursor.getString(cursor.
                        getColumnIndex("line"));
                if(line.equals("一号线")) {
                    sta1[k1] = name;
                    k1++;
                }
                if(line.equals("二号线")) {
                    sta2[k2] = name;
                    k2++;
                }
                if(line.equals("四号线")) {
                    sta4[k4] = name;
                    k4++;
                }
            } while (cursor.moveToNext());
        }
        addData("一号线",sta1);
        addData("二号线",sta2);
        addData("四号线",sta4);
        cursor.close();
    }
    private void addData(String group, String[] friend) {
        groupList.add(group);
        //每一个item打开又是一个不同的list集合
        List<String> childitem = new ArrayList<>();
        for (int i = 0; i < friend.length; i++) {
            childitem.add(friend[i]);
        }
        childList.add(childitem);
    }
}
