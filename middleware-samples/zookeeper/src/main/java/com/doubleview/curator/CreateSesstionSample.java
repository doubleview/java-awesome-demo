package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CreateSesstionSample {

    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.newClient("localhost:2181", 5000, 3000, retryPolicy);
        curatorFramework.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}