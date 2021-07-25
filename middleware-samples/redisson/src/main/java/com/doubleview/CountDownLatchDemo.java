package com.doubleview;

import org.redisson.Redisson;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redisson = Redisson.create(config);
        RCountDownLatch countDownLatch = redisson.getCountDownLatch("countDownLatch");
        countDownLatch.trySetCount(3);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]尝试获取锁");
                    countDownLatch.countDown();
                    System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]成功获取锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("成功执行");
        Thread.sleep(1000000);
    }
}
