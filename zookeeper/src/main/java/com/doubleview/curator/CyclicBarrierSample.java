package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.stream.IntStream;

public class CyclicBarrierSample {

    public static void main(String[] args) throws Exception {
        String path = "/barrier_path";
        IntStream.range(0, 10).forEach(value -> new Thread(() -> {
            try {
                RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
                CuratorFramework curatorFramework =
                        CuratorFrameworkFactory.builder()
                                .connectString("localhost:2181")
                                .sessionTimeoutMs(5000)
                                .connectionTimeoutMs(3000)
                                .retryPolicy(retryPolicy)
                                .build();
                curatorFramework.start();
                DistributedBarrier distributedBarrier = new DistributedBarrier(curatorFramework, path);
                System.out.println(Thread.currentThread().getName() + " 号设置");
                distributedBarrier.setBarrier();
                distributedBarrier.waitOnBarrier();
                System.out.println("启动.....");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
        Thread.sleep(2000);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();
        DistributedBarrier distributedBarrier = new DistributedBarrier(curatorFramework, path);
        distributedBarrier.removeBarrier();
    }
}