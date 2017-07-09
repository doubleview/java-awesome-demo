package optimised;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 实例
 */
public class ThreadLocalDemo implements Runnable {

    private ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();

    int i = 0;

    public ThreadLocalDemo(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            if (tl.get() == null) {
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            Date t = tl.get().parse("2016-01-01 19:23:23");
            System.out.println(i + ":" + t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ThreadLocalDemo(i));
        }
    }
}
