package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class SemaphoreSample {

    public static void main(String[] args) throws InterruptedException {
        String path = "/semaphore_path";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();

        InterProcessSemaphoreV2 interProcessSemaphoreV2 = new InterProcessSemaphoreV2(curatorFramework, path, 3);
        IntStream.range(0, 30).forEach(value -> new Thread(() -> {
            try {
                Lease lease = interProcessSemaphoreV2.acquire();
                System.out.println("成功进入: " + Thread.currentThread().getName());
                Thread.sleep(2000L);
                interProcessSemaphoreV2.returnLease(lease);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start());
        Thread.sleep(100000000L);
    }
}