package com.doubleview;

import java.util.Arrays;

/**
 * 加1
 */
public class Solution_66 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_66().plusOne(new int[]{1, 2, 3, 9})));
        System.out.println(Arrays.toString(new Solution_66().plusOne(new int[]{9})));
        System.out.println(Arrays.toString(new Solution_66().plusOne(new int[]{0, 0, 1})));
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] == 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}