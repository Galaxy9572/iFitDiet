package com.orange.ifitdiet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间获取和格式转化工具
 * Created by 廖俊瑶 on 2016/10/13.
 */
public class TimeUtil {
    private static SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public TimeUtil() {

    }

    /**
     * 获取当前的日期，格式为年-月-日
     *
     * @return 返回当前日期
     */
    public static String getCurrentDate() {
        return dateFormatter.format(new Date().getTime());
    }

    /**
     * 获取当前的时间，格式为年-月-日 时：分
     *
     * @return 返回当前时间
     */
    public static String getCurrentTime() {
        return timeFormatter.format(new Date().getTime());
    }
}
