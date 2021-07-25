package com.doubleview;

/**
 * 寻找旋转排序数组中的最小值
 * @author huchengchao
 */
public class Solution_153 {

    public static void main(String[] args) {
        System.out.println(new Solution_153().findMin1(new int[]{3, 4, 5, 1, 2}));
        System.out.println(new Solution_153().findMin1(new int[]{2 ,1}));
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2 + 1;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[(left + 1) % nums.length];
    }

    public int findMin1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
