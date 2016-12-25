package com.example.wangxiaojian.subway;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/10.
 */

public class HadGetTicketsFragment extends Fragment {
    private TextView mTextView;
    private TicketsAdapter mTicketsAdapter;
    private ListView mListView;
    private StationDatabaseHelper mStationDatabaseHelper;
    private RelativeLayout empt_view;
    private List<TicketRecord> mList=new ArrayList<TicketRecord>();
    public HadGetTicketsFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.hadget_ticket, container, false);
       // mTextView=(TextView)view.findViewById(R.id.text_view);
        mListView=(ListView)view.findViewById(R.id.list_view);
        empt_view=(RelativeLayout) view.findViewById(R.id.empt_view);
        mStationDatabaseHelper=new StationDatabaseHelper(getActivity(),"Stations.db",null,1);
        init();
        mTicketsAdapter=new TicketsAdapter(getActivity(),R.layout.tickets_item,mList);
        mListView.setAdapter(mTicketsAdapter);
        mListView.setEmptyView(empt_view);
        return view;
    }
    private void init() {
        SQLiteDatabase db = mStationDatabaseHelper.getWritableDatabase();
        // 查询ticket表中所有的数据
        Cursor cursor = db.query("ticket", null, "status=?", new String[]{"已取票"}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String start = cursor.getString(cursor.
                        getColumnIndex("start"));
                String end = cursor.getString(cursor.
                        getColumnIndex("end"));
                String status = cursor.getString(cursor.
                        getColumnIndex("status"));
                int num = cursor.getInt(cursor.
                        getColumnIndex("num"));
                double price = cursor.getDouble(cursor.
                        getColumnIndex("price"));
                String time = cursor.getString(cursor.
                        getColumnIndex("time"));
                TicketRecord ticketRecord1 = new TicketRecord(start, end, status, num, price, time);
                mList.add(ticketRecord1);
            } while (cursor.moveToNext());

        }
    }
}
