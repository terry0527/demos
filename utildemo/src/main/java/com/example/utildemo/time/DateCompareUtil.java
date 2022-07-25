package com.example.utildemo.time;



import cn.hutool.core.collection.CollectionUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DateCompareUtil {

    /**
     * 获取最大时间
     * @return
     */
    public static String getMax(String val1, String val2, String val3) {
        SimpleDateFormat format = new SimpleDateFormat();
        String max = "";
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        try {
            //判断非空加入
//            if (StringUtils.isNotBlank(val1)) {
//                date1 = format.parse(val1);
//            }
            date1 = format.parse(val1);
            date2 = format.parse(val2);
            date3 = format.parse(val3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Date> dates = new ArrayList<>();
//        if (StringUtils.isNotBlank(val1)) {
//            dates.add(date1);
//        }
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);
        if (CollectionUtil.isNotEmpty(dates)) {
            Date maxDate = Collections.max(dates);
            max = format.format(maxDate);
        }
        return max;
    }
}
