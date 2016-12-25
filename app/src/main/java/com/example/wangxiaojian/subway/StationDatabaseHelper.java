package com.example.wangxiaojian.subway;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Wangxiaojian on 2016/12/18.
 */

public class StationDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACT = "create table station ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "line text)";
    public static final String CREATE_CONTACT2 = "create table station2 ("
            + "id integer primary key autoincrement, "
            + "start text, "
            + "end text, "
            + "price double)";
    public static final String CREATE_CONTACT3 = "create table ticket ("
            + "id integer primary key autoincrement, "
            + "owner_id integer, "
            + "start text, "
            + "end text, "
            + "price double, "
            + "time text, "
            + "status text, "
            + "num int, "
            + "foreign key (owner_id) references contacts(id))";
    public static final String CREATE_CONTACT4= "create table contacts ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "mobile text, "
            + "password text)";
    private Context mContext;
    String[] stations1={"湘湖","滨康路","西兴","滨河路","江陵路","近江","婺江路","城站","定安路","龙翔桥","凤起路","武林广场"
            ,"西湖文化广场","打铁关","闸弄口","火车东站","彭埠","七堡","九合路","九堡","客运中心","下沙西","乔司南","金沙湖"
            ,"乔司","高沙路","翁梅","文泽路","余杭高铁站","文海南路","南苑","云水","临平","下沙江滨"};
    String[] stations2={"朝阳","曹家桥","潘水","人民路","杭发厂","人民广场","建设一路","建设三路","振宁路","飞虹路","盈丰路","钱江世纪城","钱江路"};
    String[] stations4={"近江","城新路","市民中心","江锦路","钱江路","人民广场","景芳","新塘","新风","火车东站","彭埠"};
    public StationDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT);
        db.execSQL(CREATE_CONTACT2);
        db.execSQL(CREATE_CONTACT3);
        db.execSQL(CREATE_CONTACT4);
        ContentValues values = new ContentValues();
        // 开始组装数据
        for(int i =0;i<stations1.length;i++) {
            values.put("name", stations1[i]);
            values.put("line", "一号线");
            db.insert("station", null, values); // 插入数据
            values.clear();
        }
        for(int i =0;i<stations2.length;i++) {
            values.put("name", stations1[i]);
            values.put("line", "二号线");
            db.insert("station", null, values); // 插入数据
            values.clear();
        }
        for(int i =0;i<stations4.length;i++) {
            values.put("name", stations4[i]);
            values.put("line", "四号线");
            db.insert("station", null, values); // 插入数据
            values.clear();
        }
        for(int i=0;i<stations1.length;i++){
            for(int j=0;j<stations1.length;j++){
                if(i!=j){
                    values.put("start",stations1[i]);
                    values.put("end",stations1[j]);
                    values.put("price",2);
                    db.insert("station2",null,values);
                }
            }
            values.clear();
        }
        values.put("start","西湖");
        values.put("end","仓前");
        values.put("status","已取票");
        values.put("num",2);
        values.put("price",2);
        values.put("time","2016-2-2");
        db.insert("ticket", null, values); // 插入数据
        //Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public int getStations1Length(){
        return stations1.length;
    }
    public int getStations2Length(){
        return stations2.length;
    }
    public int getStations4Length(){
        return stations4.length;
    }
}
