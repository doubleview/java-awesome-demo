package com.doubleview;

/**
 * 2的幂
 * @author huchengchao
 */
public class Solution_231 {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & n - 1) == 0;
    }
}
