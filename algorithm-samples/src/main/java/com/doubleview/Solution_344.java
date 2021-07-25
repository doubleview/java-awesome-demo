package com.doubleview;

/**
 * 字符串反转
 */
public class Solution_344 {

    public void reverseString(char[] s) {
        for (int i = 0; i < (s.length + 1) / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }
}
