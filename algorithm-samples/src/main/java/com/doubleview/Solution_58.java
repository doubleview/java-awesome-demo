package com.doubleview;

/**
 * 最后一个单词的长度
 */
public class Solution_58 {

    public int lengthOfLastWord(String s) {
        s = s.trim();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (Character.isSpaceChar(s.charAt(i))) {
                return s.length() - 1 - i;
            }
        }
        return s.length();
    }
}