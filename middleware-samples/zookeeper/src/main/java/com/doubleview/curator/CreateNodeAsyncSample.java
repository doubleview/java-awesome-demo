package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateNodeAsyncSample {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(2);
    private static ExecutorService tp = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();
        System.out.println("Main Thread :" + Thread.currentThread().getName());

        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground((client, event) -> {
                    System.out.println(String.format("event code : %s, type: %s", event.getResultCode(), event.getType()));
                    System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    connectedSemaphore.countDown();
                }, tp)
                .forPath("/zk-curator-test", "init".getBytes());
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground((client, event) -> {
                    System.out.println(String.format("event code : %s, type: %s", event.getResultCode(), event.getType()));
                    System.out.println("Thread of processResult: " + Thread.currentThread().getName());
                    connectedSemaphore.countDown();
                })
                .forPath("/zk-curator-test", "init".getBytes());
        connectedSemaphore.await();
        tp.shutdown();
    }
}
