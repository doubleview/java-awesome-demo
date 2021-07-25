package com.doubleview;

/**
 * 爱吃香蕉的可可
 * @author huchengchao
 */
public class Solution_875 {

    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1;
            else
                hi = mi;
        }

        return lo;
    }

    // Can Koko eat all bananas in H hours with eating speed K?
    private boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p : piles)
            time += (p - 1) / K + 1;
        return time <= H;
    }
}
