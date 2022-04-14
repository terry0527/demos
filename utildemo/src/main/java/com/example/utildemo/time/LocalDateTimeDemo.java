package com.example.utildemo.time;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Slf4j
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        calculate();
        add();
        cal();
    }

    /**
     * java计算时间差
     */
    public static void calculate(){
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.parse("2022-04-13 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Duration duration = Duration.between(dateTime1,dateTime2);
        //总天数
       long a= duration.toDays();
        //总小时数
        duration.toHours();
        //总分钟数
        duration.toMinutes();
        //总毫秒数
        duration.toMillis();
        log.info("value:{}",a);

    }

    /**
     * java8时间加减
     */
    public static void add(){
        //加一天
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        //减5小时,减30分钟
        LocalDateTime dateTime = LocalDateTime.now().minusHours(5).minusMinutes(30);

        log.info("tomorrow:{}，dateTime:{}",tomorrow,dateTime);

    }

    /**
     * LocalDate/LocalDateTime自带方法直接算计时间差
     */
    public static void cal(){
        LocalDate start = LocalDate.parse("2018-08-28");
        LocalDate end = LocalDate.parse("2018-09-30");
        long year = start.until(end, ChronoUnit.YEARS);
        long month = start.until(end, ChronoUnit.MONTHS);
        long days = start.until(end, ChronoUnit.DAYS);
        System.out.println("间隔：" + year + "年");
        System.out.println("间隔：" + month + "月");
        System.out.println("间隔：" + days + "天");
    }
}
