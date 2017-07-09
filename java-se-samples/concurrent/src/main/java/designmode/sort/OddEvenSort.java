package designmode.sort;


import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并行模式的排序
 */
public class OddEvenSort {

    public static void main(String[] args) throws InterruptedException {
        int[] a = new int[]{3 , 5 , 1 , 56 , 34 , 78 , 23 , 89 , 6 , 35 , 4 , 26 , 15 , 9 , 45 , 32 , 98 , 76  , 64};
        pOddEvenSort(a);
        System.out.println(Arrays.toString(a));
    }

    static int exchFlag = 1;//1表示有数据交换

    static  void setExchFlag(int v) {
        exchFlag = v;
    }

    static  int getExchFlag() {
        return exchFlag;
    }

    /**
     * 并行排序
     * @param arr
     * @throws InterruptedException
     */
    public static void pOddEvenSort(int[] arr) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        int start = 0;
        while (getExchFlag() == 1 || start == 1) {
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? start : 0));
            for (int i = start; i < arr.length - 1; i+=2) {
                es.submit(new OddEvenSortTask(i,  arr ,latch));
            }
            latch.await();
            if (start == 0)
                start = 1;
            else
                start = 0;
        }
    }

    /**
     * 奇偶交换任务
     */
    public static class OddEvenSortTask implements Runnable {

        private int i;
        private CountDownLatch latch;
        private int[] arr;

        public OddEvenSortTask(int i, int[] arr , CountDownLatch latch) {
            this.i = i;
            this.arr =arr;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                setExchFlag(1);
            }
            latch.countDown();
        }
    }

}
