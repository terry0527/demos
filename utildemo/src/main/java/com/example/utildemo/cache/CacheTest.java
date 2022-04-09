package com.example.utildemo.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description  设计一个缓存，读写锁
 * @param
 * @return null
 */
public class CacheTest {

    // 缓存的map
    private Map<String, Object> map = new HashMap<String, Object>();
    // 读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 从缓存中获取数据的方法
     * @param key
     * @return
     */
    public Object getData(String key) {
        readWriteLock.readLock().lock();//读锁，只对写的线程互斥
        Object value = null;
        try {
            // 尝试从缓存中获取数据
            value = map.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();//发现目标值为null,释放掉读锁
                readWriteLock.writeLock().lock();//发现目标值为null,需要取值操作,上写锁
                try {
                    value = map.get(key);// 很严谨这一步。再次取目标值
                    if (value == null) {//很严谨这一步。再次判断目标值,防止写锁释放后，后面获得写锁的线程再次进行取值操作
                        // 模拟DB操作
                        value = new Random().nextInt(10000) + "test";
                        map.put(key, value);

                        System.out.println("db completed!");
                    }
                    readWriteLock.readLock().lock();//再次对读进行锁住，以防止写的操作，造成数据错乱
                } finally {
                    /*
                     * 先加读锁再释放写锁读作用：
                     * 防止在43行出多个线程获得写锁进行写的操作，所以在写锁还没有释放前要上读锁
                     */
                    readWriteLock.writeLock().unlock();
                }
            }

        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    /**
     *  test main
     * @param args
     */
    public static void main(String[] args) {
        final CacheTest cache = new CacheTest();
        final String key = "user";
        for (int i = 0; i < 1000; i++) {
            new Thread(){
                public void run() {
                    System.out.println(cache.getData(key));
                };
            }.start();
        }
    }

}
