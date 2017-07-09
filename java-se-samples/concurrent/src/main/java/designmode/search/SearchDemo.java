package designmode.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 并行模式下的搜索
 */
public class SearchDemo {

    private static int[] arr = new int[]{3 , 5 , 1 , 56 , 34 , 78 , 23 , 89 , 6 , 35 , 4 , 26 , 15 , 9 , 45 , 32 , 98 , 76  , 64};

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Arrays.toString(arr));
        System.out.println(pSearch(4));
    }

    /**
     * 并行搜索
     * @param searchValue
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static int pSearch(int searchValue) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newCachedThreadPool();
        int Thread_Num = 2;//线程数量

        int subArrSize = arr.length / Thread_Num + 1;
        List<Future<Integer>> re = new ArrayList<>();

        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length) end = arr.length;
            re.add(es.submit(new SearchTask(searchValue, i, end)));
        }
        for (Future<Integer> future : re) {
            if (future.get() >= 0)
                return future.get();
        }
        return -1;
    }

    /**
     * 搜索任务
     */
    public static class SearchTask implements Callable<Integer> {

        int begin;
        int end;
        int searchValue;

        private static volatile int result = -1;

        public SearchTask(int searchValue, int begin, int end) {
            this.searchValue = searchValue;
            this.begin = begin;
            this.end = end;
        }

        public static int search(int searchValue, int beginPos, int endPos) {
            int i = 0;
            for (i = beginPos; i < endPos; i++) {
                //别的线程已经找到目标值
                if (result > 0) {
                    return result;
                }
                if(arr[i] == searchValue){
                    result = i;
                    return result;
                }
            }
            return -1;
        }

        @Override
        public Integer call() throws Exception {
            int re = search(searchValue, begin, end);
            return re;
        }
    }

}


