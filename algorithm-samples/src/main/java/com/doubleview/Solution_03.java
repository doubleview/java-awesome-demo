package com.doubleview;

/**
 * 无重复字符的最长子
 */
public class Solution_03 {

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int[] charIndex = new int[256];
        for (int left = 0, right = 0; right < s.length(); right++) {
            left = Math.max(charIndex[s.charAt(right)], left);
            result = Math.max(result, right - left + 1);
            charIndex[s.charAt(right)] = right + 1;
        }
        return result;
    }
}
