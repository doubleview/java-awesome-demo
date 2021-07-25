package com.doubleview;

import java.util.Arrays;

/**
 * 供暖器
 */
public class Solution_475 {

    public static void main(String[] args) {
        System.out.println(new Solution_475().findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
    }

    public int findRadius(int[] houses, int[] heaters) {
        int res = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);

        for (int house : houses) {
            int left = 0;
            int right = heaters.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (house > heaters[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int dist1 = Math.abs(house - heaters[left]);
            int dist2 = left == 0 ? Integer.MAX_VALUE : Math.abs(house - heaters[left - 1]);
            res = Math.max(res, Math.min(dist1, dist2));
        }
        return res;
    }
}