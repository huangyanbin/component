package com.lemon.jujin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David on 2016/10/18.
 */

public class TimeUtils {

    public static long ONEDAYMILLIS = 24 * 60 * 60 * 1000;
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public static String getShowTimeString(long time) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        long timeDistance = currentTimeMillis - time;
        long todayStartMillis = currentTimeMillis
                - (now.getHours() * 3600 + now.getMinutes() * 60 + now
                .getSeconds()) * 1000;
        if (timeDistance < 3600000) {
            if (timeDistance / (60000) <= 0) {
                return "刚刚";
            }
            return timeDistance / (60000) + "分钟前";
        } else if (timeDistance < ONEDAYMILLIS) {
            return timeDistance / (3600000) + "小时前";
        } else if (time > todayStartMillis - ONEDAYMILLIS) {
            return "1天前 ";
        } else if (time > todayStartMillis - 2 * ONEDAYMILLIS) {
            return "2天前 ";
        } else if (time > todayStartMillis - 3 * ONEDAYMILLIS) {
            return "3天前 ";
        } else if (time > todayStartMillis - 4 * ONEDAYMILLIS) {
            return "4天前 ";
        } else if (time > todayStartMillis - 5 * ONEDAYMILLIS) {
            return "5天前 ";
        } else if (time > todayStartMillis - 6 * ONEDAYMILLIS) {
            return "6天前 ";
        } else if (time > todayStartMillis - 7 * ONEDAYMILLIS) {
            return "7天前 ";
        } else {
            return getSimpleDateFormat("yyyy-MM-dd", new Date(time));
        }
    }
    public static String getSimpleDateFormat(String pattern, Date time) {
        return new SimpleDateFormat(pattern).format(time);
    }

}
