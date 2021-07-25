package com.doubleview;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SoaServiceTest implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper("10.111.75.8:2181", 5000, new SoaServiceTest());
        connectedSemaphore.await();

        List<String> childrenList = zk.getChildren("/soav2/AppBosWorkCenterAccessService/dev", true);
        System.out.println(childrenList);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("GetChild: " + zk.getChildren(watchedEvent.getPath(), true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
