package com.doubleview;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出字符串中所有字母异位词
 */
public class Solution_438 {

    public static void main(String[] args) {
        System.out.println(new Solution_438().findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resultList = new ArrayList<>();
        // 计算字符串p中各元素的出现次数
        int[] need = new int[26];
        for(int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }

        int[] window = new int[26];

        // 窗口区间为[start,end]
        int start = 0, end = 0;
        while (end < s.length()) {
            int endIndex = s.charAt(end) - 'a';
            if (need[endIndex] == 0) {
                while (start < end) {
                    window[s.charAt(start) - 'a']--;
                    start++;
                }
                start = end + 1;
                end++;
            } else if (end - start + 1 < p.length()) {
                window[endIndex]++;
                end++;
            } else {
                window[endIndex]++;
                if (isSame(window, need)) {
                    resultList.add(start);
                }
                window[s.charAt(start) - 'a']--;
                start++;
                end++;
            }
        }
        return resultList;
    }

    private boolean isSame(int[] window, int[] need) {
        for (int i = 0; i < need.length; i++) {
            if (window[i] != need[i]) {
                return false;
            }
        }
        return true;
    }
}
