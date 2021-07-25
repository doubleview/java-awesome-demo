package com.doubleview;

/**
 * 验证回文串
 */
public class Solution_125 {
    public boolean isPalindrome(String s) {
        if (s.equals("")) {
            return true;
        }
        char[] array = s.toCharArray();
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(array[left])) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(array[right])) {
                right--;
                continue;
            }
            if (Character.toLowerCase(array[left]) != Character.toLowerCase(array[right])) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
