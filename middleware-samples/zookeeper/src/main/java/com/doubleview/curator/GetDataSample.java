package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class GetDataSample {

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
        String path = "/zk-curator-test/c1";
        curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());
        System.out.println("创建成功 path: " + path);
        Stat stat = new Stat();
        curatorFramework.getData().storingStatIn(stat).forPath(path);
        System.out.println("success set node for " + path + ", new version : " +
                curatorFramework.setData().withVersion(stat.getVersion()).forPath(path).getVersion());
        try {
            curatorFramework.setData().withVersion(stat.getVersion()).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}