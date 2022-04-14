package com.example.utildemo.time;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ChangeUtil {

    public static void main(String[] args) throws Exception{
           // date();
            DTL();
    }

    public static void date() throws Exception{
        String date = "2018-03-03 15:20:12";
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        System.out.println(parse);
    }

    /**
     * 将长整型数字转换为日期格式的字符串
     * @param time
     * @param format
     * @return
     */
    public static String convert2String(long time, String format) {
        if (time > 0l) {
            format = "yyyy-MM-dd";
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String convertTimeToString(Long time){
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    private static SimpleDateFormat sf = null;

    // 将时间戳转成字符串
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    //获取当前时间
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    //将字符串转换为时间戳
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    //直接获取当前时间戳
    public static String getTimeStamp() {
        String currentDate = getCurrentDate();
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date.getTime());
    }

    // Date 转 LocalDateTime
    public static void DTL() throws Exception{
        Date date = new Date();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime time1 = date.toInstant().atZone(zoneId).toLocalDateTime();
        System.out.println("DTLtime1"+time1);

        String date1 = "2018-03-03 15:20:12";
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date1);
        LocalDateTime time2 = LocalDateTimeUtil.of(parse);
        System.out.println("DTLtime2"+time2);
    }

    // LocalDateTime 转 Date
    public static void LTD() {
        LocalDateTime localDateTime = LocalDateTime.now();
        //获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        //时区的日期和时间
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        //获取时刻
        Date date = Date.from(zonedDateTime.toInstant());
        System.out.println("格式化前：localDateTime:" + localDateTime + "  Date:" + date);
        //格式化LocalDateTime、Date
        DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("格式化后：localDateTime:" + localDateTimeFormat.format(localDateTime) + "  Date:" + dateFormat.format(date));

    }
}
