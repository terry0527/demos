package com.example.threadpooldemoserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description
 * history:
 */
public class MultiThreadTest {

    public static void main(String[] args) throws Exception {
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            idList.add(i);
        }
        int threadNum = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        int perSize = idList.size() / threadNum;
        for (int i = 0; i < threadNum; i++) {
            MultiThread thread = new MultiThread();
            thread.setIdList(idList.subList(i * perSize, (i + 1) * perSize));
            thread.setCountDownLatch(countDownLatch);
            executorService.submit(thread);
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}

class MultiThread extends Thread {
    private List<Integer> idList;

    private CountDownLatch countDownLatch;

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(this.idList);
            //处理业务
            //doWork(this.batchRecDO);
            //logger.info("当前代发处理信息:{}",JSON.toJSONString(batchRecDO));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
