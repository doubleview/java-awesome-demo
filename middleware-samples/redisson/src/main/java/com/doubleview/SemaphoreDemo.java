package com.doubleview;

import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Date;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redisson = Redisson.create(config);
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        semaphore.trySetPermits(3);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]尝试获取锁");
                    semaphore.acquire();
                    System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]成功获取锁");
                    Thread.sleep(3000);
                    semaphore.release();
                    System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(1000000);
    }
}
