package com.zlw.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dirk
 * @date 2020-04-26 8:35
 */
public class DateUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 返回当前时间，字符串形式
     * @return
     */
    public static String getStringTime(){
        String time = format.format(new Date());
        return time;
    }

}
