package com.doubleview.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.File;

public class TestingServerDemo {

    public static void main(String[] args) throws Exception {
        String path = "/zookeeper";
        TestingServer server = new TestingServer(2181, new File("/tmp/zookeeper"));
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(server.getConnectString())
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(3000)
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                        .build();
        client.start();
        System.out.println(client.getChildren().forPath(path));
        server.close();
    }
}
