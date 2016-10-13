package com.orange.ifitdiet.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 廖俊瑶 on 2016/10/13.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Step(Date char(10) primary key,Step integer,Calories integer)");
        db.execSQL("create table Heartbeat(Date char(10), Time char(10) primary key,Heartbeat integer)");
        db.execSQL("create table Bloodpressure(Date char(10), Time char(10) primary key,Bloodpressure integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("数据库信息","数据库信息已更新");
    }
}
