package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class PathChildCacheSample {

    public static void main(String[] args) throws Exception {
        String path = "/zk-curator-test";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED, " + event.getData().getPath());
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED, " + event.getData().getPath());
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED, " + event.getData().getPath());
                    break;
            }
        });
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(path);
        Thread.sleep(1000);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
        Thread.sleep(1000);
        curatorFramework.setData().forPath(path + "/c1", "uuu".getBytes());
        Thread.sleep(1000);
        curatorFramework.delete().forPath(path + "/c1");
        Thread.sleep(1000);
        curatorFramework.delete().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
