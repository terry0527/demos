/*
package com.example.threadpooldemoserver.task3;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

*/
/**
 * description
 * history:
 *//*

@Component
@Log4j2
public class SyncBookHandler {

    @Autowired
   // private UserDao userDao;


    */
/**
     * syncMargePsr:(多线程同步处理数据方法). <br/>
     *
     * @param bookList  一段数据集合
     * @param pageIndex 段数
     * @return Future<String> future对象
     * @author LW
     * @since JDK 1.8
     *//*

    @Async(value = "BookTask")
    public Future <String> syncMargePsr(List<UserDomain> bookList, int pageIndex) {

        System.out.println("thread name " + Thread.currentThread().getName());
        log.info(String.format("此批数据的段数为:%s 此段数据的数据条数为:%s", pageIndex, bookList.size()));
        //声明future对象
        Future<String> result = new AsyncResult<String>("");
        //循环遍历该段旅客集合
        if (null != bookList && bookList.size() > 0) {
            for (UserDomain book : bookList) {
                try {
                    //数据入库操作
                   // userDao.insert(book);
                } catch (Exception e) {

                    //记录出现异常的时间，线程name
                    result = new AsyncResult <String>("fail,time=" + System.currentTimeMillis() + ",thread id=" + Thread.currentThread().getName() + ",pageIndex=" + pageIndex);
                    continue;
                }
            }
        }
        return result;
    }
}
*/
