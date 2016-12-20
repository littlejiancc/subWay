package com.example.wangxiaojian.subway;

/**
 * Created by Wangxiaojian on 2016/12/16.
 */

public class TicketRecord {
    private String start,end,status,time;
    private int num;
    private double price;
    public String getStart(){
        return start;
    }
    public String getEnd(){
        return end;
    }
    public String getStatus(){
        return status;
    }
    public String getTime(){return time;}
    public int getNum(){return num;}
    public double getPrice(){return price;}
    public TicketRecord(String start,String end,String status,int num,double price,String time){
        this.start=start;
        this.end=end;
        this.status=status;
        this.num=num;
        this.price=price;
        this.time=time;
    }
}
