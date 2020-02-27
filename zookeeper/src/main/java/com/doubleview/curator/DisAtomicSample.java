package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

public class DisAtomicSample {

    public static void main(String[] args) throws Exception {
        String path = "/dis_atomic_path";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();
        DistributedAtomicInteger distributedAtomicInteger =
                new DistributedAtomicInteger(curatorFramework, path, new RetryNTimes(1000, 3));
        AtomicValue<Integer> atomicValue = distributedAtomicInteger.add(8);
        System.out.println("Result : " + atomicValue.succeeded());
    }
}