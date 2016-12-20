package com.example.wangxiaojian.subway;


import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Wangxiaojian on 2016/12/12.
 */

public class PayDetailFragment extends DialogFragment {
    private RelativeLayout rePayWay, rePayDetail,re_root,reBalance,reBalance2;
    private LinearLayout LinPayWay;
    private TextView textPayWay,tvBalance,tvJtBank,pay,price,number;
    private ListView lv;
    private Button btnPay;
    private StationDatabaseHelper mStationDatabaseHelper;
    public double p=0,pr=0;
    public int num=1;
    public String ticket_start,ticket_end,ticet_time,ticket_price,ticket_status;
    DecimalFormat df = new DecimalFormat("0.00");
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.setContentView(R.layout.pay_detail);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
        window.setAttributes(lp);
        LinPayWay = (LinearLayout) dialog.findViewById(R.id.lin_pay_way);//付款方式
        lv = (ListView) dialog.findViewById(R.id.lv_bank);//付款方式（银行卡列表）
        reBalance = (RelativeLayout) dialog.findViewById(R.id.re_balance);//付款方式（余额）
        reBalance2=(RelativeLayout)dialog.findViewById(R.id.re_balance2);
        re_root=(RelativeLayout)dialog.findViewById(R.id.re_root);
        rePayWay = (RelativeLayout) dialog.findViewById(R.id.re_pay_way);
        rePayDetail = (RelativeLayout) dialog.findViewById(R.id.re_pay_detail);//付款详情
        textPayWay=(TextView)dialog.findViewById(R.id.text_pay_way);
        btnPay = (Button) dialog.findViewById(R.id.btn_confirm_pay);
        tvBalance=(TextView)dialog.findViewById(R.id.tv_balance);
        tvJtBank=(TextView)dialog.findViewById(R.id.tv_jtbank);
        pay=(TextView)dialog.findViewById(R.id.ticket_pay);
        price=(TextView)dialog.findViewById(R.id.ticket_price);
        number=(TextView)dialog.findViewById(R.id.ticket_num);

        mStationDatabaseHelper=new StationDatabaseHelper(getActivity(),"Stations.db",null,1);

        pay.setText(String.valueOf(df.format(pr)));
        price.setText(String.valueOf(df.format(p)));
        number.setText("杭州地铁单程票"+String.valueOf(num)+"张");


        rePayWay.setOnClickListener(listener);
        reBalance.setOnClickListener(listener);
        reBalance2.setOnClickListener(listener);


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(re_root,"支付成功",Snackbar.LENGTH_LONG).show();
                SQLiteDatabase db = mStationDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装数据
                values.put("start",ticket_start);
                values.put("end",ticket_end);
                values.put("status",ticket_status);
                values.put("num",num);
                values.put("price",pr);
                values.put("time",ticet_time);
                db.insert("ticket", null, values); // 插入数据
                values.clear();
            }
        });
        return dialog;

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Animation slide_left_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left);
            Animation slide_right_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_to_left);
            Animation slide_left_to_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_right);
            Animation slide_left_to_left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left_in);
            switch (view.getId()) {
                case R.id.re_pay_way:
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    LinPayWay.startAnimation(slide_right_to_left);
                    LinPayWay.setVisibility(View.VISIBLE);
                    break;
                case R.id.re_balance:
                    rePayDetail.startAnimation(slide_left_to_left_in);
                    rePayDetail.setVisibility(View.VISIBLE);
                    LinPayWay.startAnimation(slide_left_to_right);
                    LinPayWay.setVisibility(View.GONE);
                    textPayWay.setText(tvBalance.getText());
                    break;
                case R.id.re_balance2:
                    rePayDetail.startAnimation(slide_left_to_left_in);
                    rePayDetail.setVisibility(View.VISIBLE);
                    LinPayWay.startAnimation(slide_left_to_right);
                    LinPayWay.setVisibility(View.GONE);
                    textPayWay.setText(tvJtBank.getText());
                    break;
                default:
                    break;
            }
        }
    };
}
