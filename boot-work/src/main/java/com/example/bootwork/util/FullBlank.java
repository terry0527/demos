package com.example.bootwork.util;

import com.example.bootwork.statistics.entity.StasBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FullBlank {

    /**
     * 补全指定小时的周期性数据
     * @param length list 已有数据（不完全）
     * @param list list 已有数据（不完全）
     */
    public static List<Integer> fullBlank(List<StasBean> list, int length) {
        List<Integer> series = new ArrayList<>(length);
        int[] arrays = new int[length];
        for (int i = 0; i < length; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - (length - 1 - i));
            SimpleDateFormat df = new SimpleDateFormat("HH");
            String format = df.format(calendar.getTime());
            if(list != null && list.size() > 0){
                for (StasBean bean : list) {
                    if (bean.getName().equals(format)) {
                        arrays[i] = bean.getValue();
                    }
                }
            }
            series.add(arrays[i]);
        }
        return series;
    }

    /**
     * 补全最近7天的周期性数据
     * @param list list 已有数据（不完全）
     */
    public static List<Integer> fullBlank(List<StasBean> list) {
        List<Integer> series = new ArrayList<>(7);
        int[] arrays = new int[7];
        if (list != null && list.size() > 0){
            for (StasBean bean : list) {
                int i = Integer.parseInt(bean.getName());
                switch (i){
                    case 1:
                        bean.setName("星期一");
                        break;
                    case 2:
                        bean.setName("星期二");
                        break;
                    case 3:
                        bean.setName("星期三");
                        break;
                    case 4:
                        bean.setName("星期四");
                        break;
                    case 5:
                        bean.setName("星期五");
                        break;
                    case 6:
                        bean.setName("星期六");
                        break;
                    case 0:
                        bean.setName("星期日");
                        break;
                }
            }
        }
        //补全数据
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) - (6 - i));
            Locale locale = new Locale("zh", "ZN");
            SimpleDateFormat df = new SimpleDateFormat("EE", locale);
            String format = df.format(calendar.getTime());
            if (list != null && list.size() > 0){
                for (StasBean bean : list) {
                    if (bean.getName().equals(format)) {
                        arrays[i] = bean.getValue();
                    }
                }
            }
            series.add(arrays[i]);
        }
        return series;
    }

    /**
     * 补全周期性数据
     * @param list  已有数据（不完全）
     * @param start 遍历所需变量（初值已赋）
     * @param end   遍历最大值
     */
    public static List<Integer> fullBlank(List<StasBean> list, int start, int end) {
        List<Integer> series = new ArrayList<>(end);
        int[] arrays = new int[end];
        for (int i = 0; i < end; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - (29 - i));
            SimpleDateFormat df = new SimpleDateFormat("dd");
            String format = df.format(calendar.getTime());
            if (list != null && list.size() > 0){
                for (StasBean bean : list) {
                    if (bean.getName().equals(format)) {
                        arrays[i] = bean.getValue();
                    }
                }
            }
            series.add(arrays[i]);
        }
        return series;
    }
}
