package com.doubleview;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class AuthDeleteSample {

    public static void main(String[] args) throws Exception {
        String path1 = "/zk-test-auth";
        String path2 = path1 + "/child";
        ZooKeeper zooKeeper1 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper1.create(path1, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zooKeeper1.create(path2, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 5000, null);
        try {
            zooKeeper2.delete(path2, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ZooKeeper zooKeeper3 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper3.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper3.delete(path2, -1);
        System.out.println("成功删除节点: " + path1);

        //删除节点本身不需要权限信息
        ZooKeeper zooKeeper4 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper4.delete(path1, -1);
        System.out.println("成功删除节点: " + path2);
    }
}
