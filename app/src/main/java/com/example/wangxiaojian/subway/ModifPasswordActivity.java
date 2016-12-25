package com.example.wangxiaojian.subway;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wangxiaojian on 2016/12/25.
 */

public class ModifPasswordActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private StationDatabaseHelper dbHelper;
    private Button mButton;
    private TextView old_password,txt_password1,txt_password2;
    private String mName,newPassword1,newPassword2,oldPassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_password);
        txt_password1=(TextView)findViewById(R.id.new_password);
        txt_password2=(TextView)findViewById(R.id.new_password2);
        old_password=(TextView)findViewById(R.id.old_password);
        dbHelper=new StationDatabaseHelper(this,"Stations.db",null,1);
        mToolbar=(Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //得到上个活动传下来的数据
        Intent intent=getIntent();
        mName=intent.getStringExtra("name");
        mButton=(Button)findViewById(R.id.btn_confirm);

        mButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  newPassword1=txt_password1.getText().toString();
                  newPassword2=txt_password2.getText().toString();
                  oldPassword=old_password.getText().toString();
                  SQLiteDatabase db = dbHelper.getWritableDatabase();
                  String password=null;
//                  Cursor cursor = db.rawQuery("select password from contacts where name=" + mName, null);
                  Cursor cursor = db.query("contacts", null, "name=?", new String[]{mName}, null, null, null);
                  if (cursor.moveToFirst()) {
                      do {
                          password = cursor.getString(cursor.
                                  getColumnIndex("password"));
                      } while (cursor.moveToNext());
                  }
                  if (newPassword1.length() != 0 && newPassword2.length() != 0 && oldPassword.length() != 0) {
                      if (oldPassword.equals(password)) {
                          if (newPassword1.equals(newPassword2)) {
                              ContentValues values = new ContentValues();
                              values.put("password", newPassword1);
                              db.update("contacts", values, "name=?", new String[]{mName});
                              Toast.makeText(ModifPasswordActivity.this,
                                      "密码修改成功！", Toast.LENGTH_LONG).show();
                          } else {
                              Toast.makeText(ModifPasswordActivity.this,
                                      "两次输入的新密码不一致！", Toast.LENGTH_LONG).show();
                          }
                      }
                      else {
                          Toast.makeText(ModifPasswordActivity.this,
                                  "输入的旧密码不正确！", Toast.LENGTH_LONG).show();
                      }
                  }
                  else {
                      Toast.makeText(ModifPasswordActivity.this,
                              "输入的内容不能为空！", Toast.LENGTH_LONG).show();
                  }
              }
         });
    }
}
