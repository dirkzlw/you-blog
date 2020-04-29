package com.zlw.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dirk
 * @date 2020-04-26 8:35
 */
public class DateUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 返回当前时间，字符串形式，精确到分
     * @return
     */
    public static String getStringTime(){
        String time = format.format(new Date());
        return time;
    }
    /**
     * 返回当前时间，字符串形式，精确到日
     * @return
     */
    public static String getStringTime2(){
        String time = format2.format(new Date());
        return time;
    }

}
