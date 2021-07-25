package com.doubleview;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution_34 {

    public int searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left;
    }

    public int searchRange2(int[] nums, int target) {
        int left = 0;
        int right = nums.length + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1;
    }
}
