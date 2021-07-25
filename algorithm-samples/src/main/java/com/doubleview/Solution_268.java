package com.doubleview;

/**
 * 丢失的数字
 * @author huchengchao
 */
public class Solution_268 {

    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
