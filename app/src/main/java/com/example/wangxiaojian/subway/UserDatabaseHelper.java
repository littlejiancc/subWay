package com.example.wangxiaojian.subway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Wangxiaojian on 2016/12/12.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_CONTACT = "create table contacts ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "mobile text, "
            + "password text)";

    private Context mContext;

    public UserDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT);
        //Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
