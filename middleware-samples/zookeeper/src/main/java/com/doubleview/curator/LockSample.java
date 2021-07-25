package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author huchengchao
 */
public class LockSample {

    public static void main(String[] args) throws Exception {
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

        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(curatorFramework, path);
        lock.readLock().acquire();
        lock.readLock().release();
    }
}