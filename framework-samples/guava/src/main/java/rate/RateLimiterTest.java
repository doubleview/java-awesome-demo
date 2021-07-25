package rate;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(10, 5, TimeUnit.SECONDS);
        Thread.sleep(6000);
        System.out.println("start");
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int count = 20;
        while (count-- > 0) {
            executorService.execute(() -> {
                rateLimiter.acquire(1);
                System.out.println("time: " + (System.currentTimeMillis() - start));
            });
        }
    }
}