package com.example.wangxiaojian.subway;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_login_in;
    private TextView mLink_signup;
    private EditText name,password;
    private UserDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        btn_login_in=(Button)findViewById(R.id.btn_login_in);
        mLink_signup=(TextView)findViewById(R.id.link_signup);
        name=(EditText)findViewById(R.id.edit_name);
        password=(EditText)findViewById(R.id.edit_password);
        dbHelper=new UserDatabaseHelper(this,"Users.db",null,1);
        btn_login_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;//用来记录有无账号
                String mName=name.getText().toString();
                String mPassword=password.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询contact表中所有的数据
                Cursor cursor = db.query("contacts", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.
                                getColumnIndex("name"));
                        String password = cursor.getString(cursor.
                                getColumnIndex("password"));
                        if(mName.length()==0||password.length()==0){
                            Toast.makeText(MainActivity.this,"用户名或者密码不能为空",Toast.LENGTH_LONG).show();
                        }
                        if(mName.equals(name)&&mPassword.equals(password)){
                            count++;
                            Intent intent =new Intent(MainActivity.this,TicketsActivity.class);
                            startActivity(intent);
                        }
                    } while (cursor.moveToNext());
                }
                if(count==0){
                    Toast.makeText(MainActivity.this,"对不起！用户名或密码错误",Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        });
        mLink_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
