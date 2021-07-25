package com.doubleview;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GetChildrenAsync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        String path = "/hcc";
        zk = new ZooKeeper("localhost:2181", 5000, new GetChildrenAsync());
        connectedSemaphore.await();
        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.getChildren(path, false, new IChildren2Callback(), null);
        zk.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    zk.getChildren(watchedEvent.getPath(), true, new IChildren2Callback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class IChildren2Callback implements AsyncCallback.Children2Callback {

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println(String.format("Get Children znode result:  reponse code:%s, path:%s, ctx:%s, children list:%s , stat:{}", rc, path, ctx, children, stat));
    }
}
