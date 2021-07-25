package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.stream.IntStream;

public class CyclicBarrierSample2 {

    public static void main(String[] args) throws Exception {
        String path = "/barrier_path_2";
        IntStream.range(0, 5).forEach(value -> new Thread(() -> {
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
                DistributedDoubleBarrier distributedBarrier = new DistributedDoubleBarrier(curatorFramework, path, 5);
                Thread.sleep(Math.round(Math.random() * 3000));
                System.out.println(Thread.currentThread().getName() + " 进入barrier");
                distributedBarrier.enter();
                System.out.println("启动.....");
                Thread.sleep(Math.round(Math.random() * 3000));
                distributedBarrier.leave();
                System.out.println("退出.....");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
    }
}