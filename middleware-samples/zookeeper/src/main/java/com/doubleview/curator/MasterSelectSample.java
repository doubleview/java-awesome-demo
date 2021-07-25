package com.doubleview.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class MasterSelectSample {

    public static void main(String[] args) throws Exception {
        String path = "/master_select_path";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("localhost:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(retryPolicy)
                        .build();
        curatorFramework.start();
        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("成为Master节点");
                Thread.sleep(3000);
                System.out.println("完成Master操作, 释放权利");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
