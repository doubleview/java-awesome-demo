package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程的计划任务调度
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);

        ses.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);
    }


}
