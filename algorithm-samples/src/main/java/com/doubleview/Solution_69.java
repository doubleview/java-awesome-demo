package com.doubleview;

/**
 * x的平方根
 * @author huchengchao
 */
public class Solution_69 {

    public static void main(String[] args) {
        System.out.println(new Solution_69().mySqrt(4));
    }

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1;
        int right = x / 2;
        while (left < right) {
            int mid = (left + right) / 2 + 1;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
