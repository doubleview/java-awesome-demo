package concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class FailLock implements Runnable {

    public static ReentrantLock failLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                failLock.lock();
                System.out.println(Thread.currentThread().getName() + ": 获得锁");
            } finally {
                failLock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        FailLock r1 = new FailLock();

        Thread t1 = new Thread(r1, "Thread_t1");
        Thread t2 = new Thread(r1, "Thread_t2");

        t1.start();
        t2.start();
    }
}
