package com.akshayram.s30.DFS_1;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // TC : O(m*n) // SC : O(m*n)
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length; // rows
        int n = matrix[0].length; // cols
        int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) // Level 1
                {
                    q.add(new int[] { i, j });
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        int dist = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();

                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && matrix[nr][nc] == -1) {
                        matrix[nr][nc] = dist;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
            dist++;
        }
        return matrix;
    }

    // TC : O(n) // SC : O(n)
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (newColor != color) {
            dfs(image, sr, sc, color, newColor);
        }
        return image;
    }

    public void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) {
            image[r][c] = newColor;
            if (r >= 1)
                dfs(image, r - 1, c, color, newColor);
            if (c >= 1)
                dfs(image, r, c - 1, color, newColor);
            if (r + 1 < image.length)
                dfs(image, r + 1, c, color, newColor);
            if (c + 1 < image[0].length)
                dfs(image, r, c + 1, color, newColor);
        }
    }
}