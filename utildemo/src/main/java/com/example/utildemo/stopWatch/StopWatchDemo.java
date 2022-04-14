package com.example.utildemo.stopWatch;

import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

/**
 * https://juejin.cn/post/6844904198824263694
 */
public class StopWatchDemo {

    public static void main(String[] args) throws Exception{
       // testUseCurrentMills();
        //testUseCurrentStopWatch();
        testListStopWatch();
    }

    static  void testUseCurrentMills() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end1 = System.currentTimeMillis();
        System.out.println("代码块1执行时间"+(end1 - start));
        Thread.sleep(2000);
        long end2 = System.currentTimeMillis();
        System.out.println("代码块2执行时间"+(end2 - end1));
        Thread.sleep(3000);
        long end3 = System.currentTimeMillis();
        System.out.println("代码块3执行时间"+(end3 - end2));
        System.out.println("总共执行时间"+(end3 - start));
    }

    static void testUseCurrentStopWatch()throws InterruptedException {
        StopWatch stopWatch = new StopWatch("测试代码块组");
        stopWatch.start("代码块1");
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("代码块2");
        Thread.sleep(2000);
        stopWatch.stop();
        stopWatch.start("代码块3");
        Thread.sleep(3000);
        stopWatch.stop();
        System.out.println("耗时："+stopWatch.getTotalTimeSeconds());
        System.out.println(stopWatch.prettyPrint());
    }

    static void testListStopWatch()throws InterruptedException {
        StopWatch stopWatch = new StopWatch("测试代码块组");
        stopWatch.start("代码块1");
        Thread.sleep(1000);
        stopWatch.stop();

        List<String> list = Arrays.asList("a","b","c");
        for(Object obj:list){
            Thread.sleep(1000);
            stopWatch.start("遍历");
            Thread.sleep(1500);
            stopWatch.stop();
        }
        stopWatch.start("代码块3");
        Thread.sleep(500);
        stopWatch.stop();
        System.out.println("耗时："+stopWatch.getTotalTimeSeconds());
        System.out.println(stopWatch.prettyPrint());
    }

}
