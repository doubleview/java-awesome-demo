package com.doubleview;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ConstructorUsage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ConstructorUsage());
        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphore.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ZooKeeper session established");
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watch event" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
