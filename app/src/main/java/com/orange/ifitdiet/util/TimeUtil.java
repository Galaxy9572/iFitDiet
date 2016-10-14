package com.orange.ifitdiet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转化工具
 * Created by 廖俊瑶 on 2016/10/13.
 */
public class TimeUtil {
    private SimpleDateFormat dateFormatter, timeFormatter;

    public TimeUtil() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    /**
     * 获取当前的日期，格式为年-月-日
     *
     * @return 返回当前日期
     */
    public String getCurrentDate() {
        return dateFormatter.format(new Date().getTime());
    }

    /**
     * 获取当前的时间，格式为年-月-日 时：分
     *
     * @return 返回当前时间
     */
    public String getCurrentTime() {
        return timeFormatter.format(new Date().getTime());
    }
}
