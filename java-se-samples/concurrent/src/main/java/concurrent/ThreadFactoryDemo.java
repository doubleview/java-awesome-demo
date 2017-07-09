package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义的 线程池工厂
 */
public class ThreadFactoryDemo {

    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread ID : " + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        Task task = new Task();

        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new SynchronousQueue<>(),
                (r) -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    System.out.println("create " + t.getId());
                    return t;
                });
        for (int i = 0; i < 10; i++) {
            es.submit(task);
        }
    }
}
