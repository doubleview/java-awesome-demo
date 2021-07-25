package com.doubleview;

/**
 * 寻找重复数
 * @author huchengchao
 */
public class Solution_287 {

    public static void main(String[] args) {
        System.out.println(new Solution_287().findDuplicate1(new int[]{1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10}));
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l < r) {
            int mid = ((l + r) >> 1) + 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] < mid) {
                    cnt++;
                }
            }
            if (cnt < mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }


    public int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        return slow;
    }
}
