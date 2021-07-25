package com.doubleview;

/**
 * 爬楼梯
 */
public class Solution_70 {

    public static void main(String[] args) {
        System.out.println(new Solution_70().climbStairs(100));
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
