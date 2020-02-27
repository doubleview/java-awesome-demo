package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class LockSample {

    public static void main(String[] args) {
        String path = "/lock_path";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();

        InterProcessMutex lock = new InterProcessMutex(curatorFramework, path);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        IntStream.range(0, 30).forEach(value -> new Thread(() -> {
            try {
                countDownLatch.await();
                lock.acquire();
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss | SSS")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
        countDownLatch.countDown();
    }
}