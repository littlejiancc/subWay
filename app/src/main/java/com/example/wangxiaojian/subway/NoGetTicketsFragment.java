package com.example.wangxiaojian.subway;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/10.
 */

public class NoGetTicketsFragment extends Fragment {
    private TicketsAdapter mTicketsAdapter;
    private ListView mListView;
    private StationDatabaseHelper mStationDatabaseHelper;
    private List<TicketRecord> mList=new ArrayList<TicketRecord>();
    public NoGetTicketsFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.notget_tickets, container, false);
        mListView=(ListView)view.findViewById(R.id.list_view);
        mStationDatabaseHelper=new StationDatabaseHelper(getActivity(),"Stations.db",null,1);
        init();
        mTicketsAdapter=new TicketsAdapter(getActivity(),R.layout.tickets_item,mList);
        mListView.setAdapter(mTicketsAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TicketRecord ticketRecord=mList.get(position);
                Intent intent=new Intent(getActivity(),QRCodeActivity.class);
                intent.putExtra("start",ticketRecord.getStart());
                intent.putExtra("end",ticketRecord.getEnd());
                intent.putExtra("num",ticketRecord.getNum());
                intent.putExtra("price",ticketRecord.getPrice());
                startActivity(intent);
                Toast.makeText(getActivity(),ticketRecord.getStart(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void init(){
        SQLiteDatabase db = mStationDatabaseHelper.getWritableDatabase();
        // 查询contact表中所有的数据
        Cursor cursor = db.query("ticket", null, null, null, null, null, null);
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
                double price=cursor.getDouble(cursor.
                        getColumnIndex("price"));
                String time=cursor.getString(cursor.
                        getColumnIndex("time"));
                TicketRecord ticketRecord1=new TicketRecord(start,end,status,num,price,time);
                mList.add(ticketRecord1);
            }while (cursor.moveToNext());

        }

        //TicketRecord ticketRecord2=new TicketRecord("钱江市场","莫干山路","未取票");
       // mList.add(ticketRecord2);
    }
}
