package com.doubleview;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class AuthSample  {

    public static void main(String[] args) throws Exception {
        String path = "/zk-test-auth";
        ZooKeeper zooKeeper1 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper1.create(path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper2.addAuthInfo("digest", "foo:true".getBytes());
        System.out.println(new String(zooKeeper2.getData(path, false, null)));

        ZooKeeper zooKeeper3 = new ZooKeeper("localhost:2181", 5000, null);
        zooKeeper3.addAuthInfo("digest", "foo:false".getBytes());
        zooKeeper3.getData(path, false, new Stat());
    }
}
