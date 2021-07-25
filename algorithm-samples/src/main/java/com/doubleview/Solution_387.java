package com.doubleview;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 字符串中第一个唯一字符
 */
public class Solution_387 {

    public static void main(String[] args) {
        System.out.println(new Solution_387().firstUniqChar("dddccdbba"));
    }

    public int firstUniqChar(String s) {
        char[] array = s.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                map.put(array[i], -1);
            } else {
                map.put(array[i], i);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1) {
                return entry.getValue();
            }
        }
        return -1;
    }
}
