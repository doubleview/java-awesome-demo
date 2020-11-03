package rate;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(100);
        System.out.println(rateLimiter.tryAcquire(1));
    }
}