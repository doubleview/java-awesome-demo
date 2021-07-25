package com.doubleview;

/**
 * 按奇偶排序数组
 */
public class Solution_905 {

    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        for (int j = 0; j < A.length; j++) {
            if (A[j] % 2 == 0) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        return A;
    }
}
