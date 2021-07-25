package com.doubleview;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class LockDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
//        config
//                .useClusterServers()
//                // 集群状态扫描间隔时间，单位是毫秒
//                .setScanInterval(2000)
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://ecs01:6379")
//                .addNodeAddress("redis://ecs02:6379")
//                .addNodeAddress("redis://ecs03:6379")
//                .addNodeAddress("redis://ecs01:6380")
//                .addNodeAddress("redis://ecs02:6380")
//                .addNodeAddress("redis://ecs03:6380");

        RedissonClient redisson = Redisson.create(config);
//        RLock rLock1 = redisson.getLock("myLock1");
//        RLock rLock2 = redisson.getLock("myLock2");
//        RLock multiLock = redisson.getMultiLock(rLock1, rLock2);
//        multiLock.lock();
//        multiLock.unlock();
//
//        RLock rLock1 = redisson.getLock("myLock1");
//        RLock rLock2 = redisson.getLock("myLock2");
//        RLock redLock = redisson.getRedLock(rLock1, rLock2);
//        redLock.lock();
        RReadWriteLock rwLock = redisson.getReadWriteLock("myLock");
        rwLock.readLock().lock();
        rwLock.readLock().unlock();
        rwLock.writeLock().lock();
        rwLock.writeLock().unlock();
    }
}
