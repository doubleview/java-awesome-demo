package com.doubleview;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ConstructorUsageWith implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ConstructorUsageWith());
        connectedSemaphore.await();
        long sesstionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();
        //use illegal
        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ConstructorUsageWith(), 1L, "test".getBytes());
        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ConstructorUsageWith(), sesstionId, passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watch event" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
