package com.doubleview;

/**
 * 旋转字符串
 */
public class Solution_796 {

    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            return true;
        }
        for (int i = 1; i < A.length(); i++) {
            if (B.startsWith(A.substring(i)) && B.endsWith(A.substring(0, i))) {
                return true;
            }
        }
        return false;
    }

    public boolean rotateString1(String A, String B) {
        return (A + A).contains(B);
    }
}
