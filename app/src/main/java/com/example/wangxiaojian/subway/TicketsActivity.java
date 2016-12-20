package com.example.wangxiaojian.subway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Wangxiaojian on 2016/12/9.
 */

public class TicketsActivity extends AppCompatActivity {
    private Button btn_start,btn_end,btn_confirmPay;
    private RelativeLayout mRelativeLayout;
    private AmountView mAmountView;
    private StationDatabaseHelper mStationDatabaseHelper;
    private TextView startStation,endStation,price;
    DecimalFormat df = new DecimalFormat("0.00");
    double Pri=0;
    int ticket_num=1;//用来记录地铁票张数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mStationDatabaseHelper=new StationDatabaseHelper(this,"Stations.db",null,1);
        //
        mRelativeLayout=(RelativeLayout)findViewById(R.id.content_main);
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_end=(Button)findViewById(R.id.btn_end) ;
        btn_confirmPay=(Button)findViewById(R.id.confirm_pay);
        startStation=(TextView)findViewById(R.id.text_start);
        endStation=(TextView)findViewById(R.id.text_end);
        price=(TextView)findViewById(R.id.text_price);
        /*
        * 这里写的是选择起始站
        * */
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TicketsActivity.this,SubwayActivity.class);
                startActivityForResult(intent,1);
            }
        });

         /*
        * 这里写的是选择终点站
        * */
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TicketsActivity.this,SubwayActivity.class);
                startActivityForResult(intent,2);
            }
        });
        /*
        * 这里写的是从底部弹出支付界面
        * */
        btn_confirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayDetailFragment payDetailFragment=new PayDetailFragment();
                payDetailFragment.p=getPrice();
                payDetailFragment.pr=getPrice()*0.95;
                payDetailFragment.num=ticket_num;
                payDetailFragment.ticket_start=startStation.getText().toString();
                payDetailFragment.ticket_end=endStation.getText().toString();
                payDetailFragment.ticket_status="未取票";
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = sDateFormat.format(new java.util.Date());
                payDetailFragment.ticet_time=date;
                payDetailFragment.show(getSupportFragmentManager(),"payDetailFragment");
            }
        });
        /*
        * 这里写的是设置地铁票张数
        * */
        mAmountView = (AmountView) findViewById(R.id.amount_view);
        mAmountView.setGoods_storage(50);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                //Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                double p=Pri*amount;
                ticket_num=amount;
                price.setText(String.valueOf(df.format(p)));
            }
        });
    }
    public double getPrice(){
        return Double.valueOf(price.getText().toString());
    }
    /*
    * 这里重写onActivityResult方法来得到地铁站数据
    * */
    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnedData=data.getStringExtra("data_return_start");
                    startStation.setText(returnedData);
                }
                break;
            case 2:
                if(resultCode==RESULT_OK){
                    String returnedData=data.getStringExtra("data_return_end");
                    endStation.setText(returnedData);
                }
                break;
            default:
        }
        SQLiteDatabase db = mStationDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("station2", null, null, null, null, null, null);
        boolean flag=false;
        if(cursor.moveToFirst()){
            do{
                String start = cursor.getString(cursor.
                        getColumnIndex("start"));
                String end = cursor.getString(cursor.
                        getColumnIndex("end"));
                int price = cursor.getInt(cursor.
                        getColumnIndex("price"));
                if(startStation.getText().equals(start)&&endStation.getText().equals(end)){
                    flag=true;
                    Pri=price;
                }
            }
            while (cursor.moveToNext());
        }
        if (flag){
            price.setText(String.valueOf(df.format(Pri)));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(TicketsActivity.this,TicketsRecordActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
