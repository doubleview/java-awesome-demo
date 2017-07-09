package optimised;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class AtomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }

        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            t.join();
        }
        System.out.println(i);
    }
}
