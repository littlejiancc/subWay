package com.example.wangxiaojian.subway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wangxiaojian on 2016/12/19.
 */

public class QRCodeActivity extends AppCompatActivity{
    private TextView start,end,num,price;
    private ImageView mImageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_main);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mImageView = (ImageView)findViewById(R.id.image_view);
        start=(TextView)findViewById(R.id.tv_start);
        end=(TextView)findViewById(R.id.tv_end);
        num=(TextView)findViewById(R.id.tv_num);
        price=(TextView)findViewById(R.id.tv_pr);
        //得到上个活动传下来的数据
        Intent intent=getIntent();
        String mStart=intent.getStringExtra("start");
        String mEnd=intent.getStringExtra("end");
        int mNum=intent.getIntExtra("num",0);
        double mPrice=intent.getDoubleExtra("price",0.0);
        start.setText(mStart);
        end.setText(mEnd);
        num.setText(String.valueOf(mNum));
       price.setText(String.valueOf(mPrice));
        Bitmap bitmap = QRCode.createQRCode(String.valueOf(mPrice), 500);
        mImageView.setImageBitmap(bitmap);
    }
}
