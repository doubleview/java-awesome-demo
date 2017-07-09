package java8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 *  LongAddr的性能测试
 */
public class LongAddrPreformance {

    private static final int MAX_THREADS = 3;//线程数
    private static final int TASK_COUNT = 3;//任务数
    private static final int TARGET_COUNT = 10000000;//目标总数

    private AtomicLong account = new AtomicLong(0L);
    private LongAdder lacount = new LongAdder();

    private long count = 0;

    static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);


    protected synchronized long inc() {//有锁的加法
        return ++count;
    }

    protected synchronized long getCount() {//有锁的操作
        return count;
    }

    public class SyncThread implements Runnable{
        protected String name;
        protected  long startTime;
        LongAddrPreformance out;

        public SyncThread(LongAddrPreformance o , long startTime){
            out = o;
            this.startTime = startTime;
        }
        @Override
        public void run() {
            long v = out.getCount();
            while (v < TARGET_COUNT) {
                v = out.inc();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endTime - startTime)  + "ms" + "  v=" + v);
            cdlsync.countDown();
        }
    }

    /**
     * 使用同步锁的方式
     * @throws InterruptedException
     */
    public void testSync() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime= System.currentTimeMillis();
        SyncThread sync = new SyncThread(this, startTime);
        for(int i = 0; i < TASK_COUNT ;i++) {
            es.submit(sync);
        }
        cdlsync.await();
        es.shutdown();
    }

    public class AtomicThread implements  Runnable{
        protected  String name;
        protected  long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = account.get();
            while (v < TARGET_COUNT) {
                v = account.incrementAndGet();  //无锁的加法
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdlatomic.countDown();
        }
    }

    /**
     * 使用原子类AtomicLong
     * @throws InterruptedException
     */
    public void testAtomic() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);
        for(int i = 0; i < TASK_COUNT;i++) {
            es.submit(atomicThread);
        }
        cdlatomic.await();
        es.shutdown();
    }

    public class LongAddrThread implements Runnable {

        protected  long startTime;

        public LongAddrThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v < TARGET_COUNT) {
                lacount.increment();
                v=  lacount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("LongAdder spend:" + (endTime - startTime) + "ms" + "  v=" + v);
            cdladdr.countDown();
        }
    }

    /**
     * 使用LongAddr
     * @throws InterruptedException
     */
    public void testAtomicLong() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        LongAddrThread addrThread = new LongAddrThread(startTime);
        for(int i = 0; i< TASK_COUNT;i++) {
            es.submit(addrThread);
        }
        cdladdr.await();
        es.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        LongAddrPreformance longAddrPreformance = new LongAddrPreformance();
        longAddrPreformance.testSync();
        longAddrPreformance.testAtomic();
        longAddrPreformance.testAtomicLong();
    }

}
