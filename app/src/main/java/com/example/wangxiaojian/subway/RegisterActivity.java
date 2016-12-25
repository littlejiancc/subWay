package com.example.wangxiaojian.subway;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Wangxiaojian on 2016/12/11.
 */

public class RegisterActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private StationDatabaseHelper mUserDatabaseHelper;
    private Button register;
    private EditText name,mobile,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        register=(Button)findViewById(R.id.btn_login_in);
        name=(EditText)findViewById(R.id.edit_name);
        mobile=(EditText)findViewById(R.id.edit_mobile);
        password=(EditText)findViewById(R.id.edit_password);
        mUserDatabaseHelper=new StationDatabaseHelper(this,"Stations.db",null,1);
        mToolbar=(Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                SQLiteDatabase db = mUserDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("contacts", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        String name1 = cursor.getString(cursor.
                                getColumnIndex("name"));
                        if (name1.equals(name.getText().toString())) {
                            flag = true;
                        }
                    } while (cursor.moveToNext());
                }
                if (flag) {
                    Toast.makeText(RegisterActivity.this, "对不起，该用户已存在", Toast.LENGTH_SHORT).show();
                } else {
                    String mName = name.getText().toString();
                    String mMobile = mobile.getText().toString();
                    String mPassword = password.getText().toString();
                    if (mName.length() == 0 || mMobile.length() == 0 || mPassword.length() == 0) {
                        Toast.makeText(RegisterActivity.this, "用户名，手机号，密码不能为空！", Toast.LENGTH_SHORT).show();
                    } else {
                        ContentValues values = new ContentValues();
                        // 开始组装数据
                        values.put("name", mName);
                        values.put("mobile", mMobile);
                        values.put("password", mPassword);
                        db.insert("contacts", null, values); // 插入数据
                        Toast.makeText(RegisterActivity.this, "添加联系人成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}
