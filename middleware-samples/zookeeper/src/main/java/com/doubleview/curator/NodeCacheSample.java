package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCacheSample {

    public static void main(String[] args) throws Exception {
        String path = "/zk-curator-test/node-cache";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);
        nodeCache.start(true);
        nodeCache.getListenable()
                .addListener(() -> System.out.println("node data update, new data " + new String(nodeCache.getCurrentData().getData())));
        curatorFramework.setData().forPath(path, "uuu".getBytes());
        Thread.sleep(1000);
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Integer. MAX_VALUE);
    }
}
