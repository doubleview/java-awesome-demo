package com.doubleview;

/**
 * 只出现一次的数字2
 * @author huchengchao
 */
public class Solution_137 {

    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }
}
