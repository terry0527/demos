package com.example.bootwork.statistics;

import com.example.bootwork.statistics.entity.ChartInfo;
import com.example.bootwork.statistics.entity.StasBean;
import com.example.bootwork.util.FullBlank;
import java.text.SimpleDateFormat;
import java.util.*;

public class KingFullDemo {

    public static void main(String[] args) {
        padd(1);
    }


    public static ChartInfo padd(int type){

        ChartInfo chartInfo = new ChartInfo();
        List<String> axis = new ArrayList<>();
        List<Integer> series = null;

        List<StasBean> list = new ArrayList<>();
        StasBean bean2 = new StasBean();
        bean2.setName("13");
        bean2.setValue(10);
        list.add(bean2);

        switch (type) {

            case 2:
                //横轴封装最近一周名称
                for (int i = 0; i < 7; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK) - (6 - i));
                    //创建一个地域对象，过构造函数指定语言和国家代码
                    Locale locale = new Locale("zh", "ZN");
                    SimpleDateFormat df = new SimpleDateFormat("EE", locale);
                    String format = df.format(calendar.getTime());
                    axis.add(format);
                }
                series = FullBlank.fullBlank(list);
                break;

            case 1:
                //横轴封装最近24小时名称
                for (int i = 0; i < 24; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - (23 - i));
                    SimpleDateFormat df = new SimpleDateFormat("HH");
                    String format = df.format(calendar.getTime());
                    axis.add(format+"时");
                }
                series = FullBlank.fullBlank(list, 24);
                break;

            default:
                //横轴封装最近30天名称
                for (int i = 0; i < 30; i++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - (29 - i));
                    SimpleDateFormat df = new SimpleDateFormat("dd");
                    String format = df.format(calendar.getTime());
                    axis.add(format+"日");
                }
                series = FullBlank.fullBlank(list, 1, 30);
                break;
        }
        chartInfo.setAxis(axis);
        if (series != null) {
            chartInfo.setSeries(series);
        }
        return chartInfo;
    }


}
