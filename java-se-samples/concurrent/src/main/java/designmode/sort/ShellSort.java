package designmode.sort;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 甯屽皵鎺掑簭
 */
public class ShellSort {

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[]{3 , 5 , 1 , 56 , 34 , 78 , 23 , 89 , 6 , 35 , 4 , 26 , 15 , 9 , 45 , 32 , 98 , 76  , 64};;
        pShellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static class ShellSortTask implements  Runnable{
        int i = 0;
        int h = 0;
        int[] arr;
        CountDownLatch l;

        public ShellSortTask(int i, int h,  int[] arr  , CountDownLatch latch) {
            this.i = i;
            this.h = h;
            this.arr = arr;
            this.l = latch;
        }

        @Override
        public void run() {
            if (arr[i] < arr[i - h]) {
                int tmp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = tmp;
            }
            l.countDown();
        }
    }

    static ExecutorService es = Executors.newCachedThreadPool();

    public static void pShellSort(int[] arr) throws InterruptedException {
        int h = 1;
        CountDownLatch latch = null;
        while (h < arr.length / 3) {
            h = h*3 + 1;
        }
        while (h > 0) {
            System.out.println("h = " + h);
            if(h > 4)
                latch = new CountDownLatch(arr.length - h);

            for(int i = h ; i< arr.length;i++) {
                if (h >= 4) {
                    es.execute(new ShellSortTask(i , h ,arr ,  latch));
                }else {
                    if (arr[i] < arr[i - h]) {
                        int tmp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > tmp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j+h] = tmp;
                    }
                }
            }
            latch.await();
            h = (h - 1)/3;
        }
    }
}
