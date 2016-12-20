package com.example.wangxiaojian.subway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/15.
 */

public class TicketsAdapter extends ArrayAdapter<TicketRecord> {
    private int resourceId;
    public TicketsAdapter(Context context, int textViewResourceId, List<TicketRecord> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View conwertView, ViewGroup parent){
        View view;
        TicketRecord ticketRecord=getItem(position);
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView start=(TextView)view.findViewById(R.id.text_ticket_start);
        TextView end=(TextView)view.findViewById(R.id.text_ticket_end);
        TextView status=(TextView)view.findViewById(R.id.text_ticket_status);
        TextView num=(TextView)view.findViewById(R.id.text_ticket_num);
        TextView price=(TextView)view.findViewById(R.id.text_ticket_price);
        TextView time=(TextView)view.findViewById(R.id.text_ticket_time);
        start.setText(ticketRecord.getStart().toString());
        end.setText(ticketRecord.getEnd().toString());
        status.setText(ticketRecord.getStatus().toString());
        num.setText(ticketRecord.getNum()+"张");
        price.setText(String.valueOf(ticketRecord.getPrice())+"元");
        time.setText(ticketRecord.getTime());
        return view;
    }
}
