package com.orange.ifitdiet.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orange.ifitdiet.common.MyDBHelper;

import java.util.LinkedHashMap;

/**
 * 数据库工具
 * Created by 廖俊瑶 on 2016/10/13.
 */
public class DBUtil {
    private SQLiteDatabase db;

    public DBUtil(Context context) {
        MyDBHelper myDBHelper = new MyDBHelper(context, "health_date.db", null, 1);
        db = myDBHelper.getWritableDatabase();
    }

    /**
     * 往Step（步数）数据表中插入数据
     *
     * @param date 当前日期
     * @param step 当前已行走的步数
     */
    public void insert2Step(String date, int step) {
        db.execSQL("insert into Step (Date,Step)values(?, ?)", new Object[]{date, step});
    }

    /**
     * 删除指定日期的步数数据
     *
     * @param date 指定的日期
     */
    public void deleteFromStep(String date) {
        db.execSQL("delete from Step where Date=?", new Object[]{date});
    }

    public void update2Step() {

    }

    /**
     * 按指定的日期查询已记录的当天步数
     *
     * @param date 要查询的数据的日期
     * @return 已记录的步数，没有结果则返回0
     */
    public int queryStep(String date) {
        Cursor cursor = db.query("Step", null, "where Date=?", new String[]{date}, null, null, null, "order by Date");
        String step = "";
        while (cursor.moveToNext()) {
            step = cursor.getString(1);
        }
        if (step == null || "".equals(step)) {
            return 0;
        }
        return Integer.parseInt(step);
    }

    /**
     * 往Heartbeat（心率）数据表中插入数据
     *
     * @param time      当前时间
     * @param heartbeat 已记录的单次心率
     */
    public void insert2Heartbeat(String time, int heartbeat) {
        db.execSQL("insert into Heartbeat (Time,heartbeat)values(?, ?)", new Object[]{time, heartbeat});
    }

    public void deleteFromHeartbeat(String date) {
        db.execSQL("delete from Heartbeat where Date=?", new Object[]{date});
    }

    public void update2Heartbeat() {

    }

    /**
     * 获取某一天中测得的所有心率数据
     *
     * @param date 要查询的数据的日期
     * @return 某天的所有心率数据，没有结果则返回null
     */
    public LinkedHashMap<String, Integer> queryHeartbeat(String date) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        Cursor cursor = db.query("Heartbeat", null, "where Date=?", new String[]{date}, null, null, null, "order by Time");
        while (cursor.moveToNext()) {
            String hb_date = cursor.getString(0);
            String heartbeat = cursor.getString(1);
            map.put(hb_date, Integer.valueOf(heartbeat));
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    /**
     * 往Bloodpressure（血压）数据表中插入数据
     *
     * @param time          当前时间
     * @param bloodpressure 已记录的单次血压
     */
    public void insert2Bloodpressure(String time, int bloodpressure) {
        db.execSQL("insert into Bloodpressure (Time,Bloodpressure) values(?, ?)", new Object[]{time, bloodpressure});
    }

    public void deleteFromBloodpressure(String date) {
        db.execSQL("delete from Bloodpressure where Date=?", new Object[]{date});
    }

    public void update2Bloodpressure() {

    }

    /**
     * 获取某一天中测得的所有血压数据
     *
     * @param date 要查询的数据的日期
     * @return 某天的所有血压数据，没有结果则返回null
     */
    public LinkedHashMap<String, Integer> queryBloodpressure(String date) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        Cursor cursor = db.query("Bloodpressure", null, "where Date=?", new String[]{date}, null, null, null, "order by Time");
        while (cursor.moveToNext()) {
            String bp_date = cursor.getString(0);
            String bloodpressure = cursor.getString(1);
            map.put(bp_date, Integer.valueOf(bloodpressure));
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

}
