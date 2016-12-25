package com.example.wangxiaojian.subway;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/10.
 */
public class NoGetTicketsFragment extends Fragment {
    private TicketsAdapter mTicketsAdapter;
    private ListView mListView;
    private RelativeLayout empt_view;
    private StationDatabaseHelper mStationDatabaseHelper;
    private List<TicketRecord> mList=new ArrayList<TicketRecord>();
    public NoGetTicketsFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.notget_tickets, container, false);
        String user_name="e";
        mListView=(ListView)view.findViewById(R.id.list_view);
        empt_view=(RelativeLayout)view.findViewById(R.id.empt_view);
        mStationDatabaseHelper=new StationDatabaseHelper(getActivity(),"Stations.db",null,1);
        if(getArguments()!=null) {
            Log.d("yuuu","333");
            user_name = getArguments().getString("name");
            Log.d("34",user_name);
        }

        init(user_name);
        mTicketsAdapter=new TicketsAdapter(getActivity(),R.layout.tickets_item,mList);
        mListView.setAdapter(mTicketsAdapter);
        mListView.setEmptyView(empt_view);
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
                //Toast.makeText(getActivity(),ticketRecord.getStart(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void init(String s){
        SQLiteDatabase db = mStationDatabaseHelper.getWritableDatabase();
        // 查询ticket表中所有的数据
        Cursor cursor = db.query("ticket", null, "status=? and owner_id=?", new String[]{"未取票",s}, null, null, null);
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
