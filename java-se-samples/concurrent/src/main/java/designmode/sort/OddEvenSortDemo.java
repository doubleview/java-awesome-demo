package designmode.sort;


public class OddEvenSortDemo {

    /**
     * 涓茶濂囧伓浜ゆ崲
     *
     * @param arr
     */
    public static void oddEvenSort(int[] arr) {
        int exchFlag = 1, start = 0;

        while (exchFlag == 1 || start == 1) {
            exchFlag = 0;
            for (int i = start; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchFlag = 1;
                }
            }
            if (start == 0)
                start = 1;
            else
                start = 0;
        }
    }

}
