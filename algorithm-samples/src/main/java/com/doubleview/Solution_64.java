package com.doubleview;

/**
 * 最小路径和
 */
public class Solution_64 {

    public int minPathSum(int[][] grid) {
        int[] pathResult = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    pathResult[j] = grid[i][j];
                } else if (i == 0) {
                    pathResult[j] = grid[i][j] + pathResult[j - 1];
                } else if (j == 0) {
                    pathResult[j] = grid[i][j] + pathResult[j];
                } else {
                    pathResult[j] = grid[i][j] + Math.min(pathResult[j], pathResult[j - 1]);
                }
            }
        }
        return pathResult[grid[0].length - 1];
    }

}
