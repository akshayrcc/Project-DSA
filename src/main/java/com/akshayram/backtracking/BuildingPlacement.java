package com.akshayram.backtracking;

import java.util.LinkedList;
import java.util.Queue;

public class BuildingPlacement {

    public static void main(String[] args) {
        System.out.print(findMinDistance(7, 7, 3));
    }

    static int H;
    static int W;
    static int min;

    public static int findMinDistance(int h, int w, int n) {
        H = h;
        W = w;
        min = Integer.MAX_VALUE;
        int[][] grid = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = -1;
            }
        }
        backtrack(grid, n, 0);
        int result = 0;
        return result;

    }

    private static void bfs(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if (nr < H && nr >= 0 && nc < W && nc >= 0 && grid[nr][nc] == -1 && !visited[nr][nc]) {
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
        min = Math.min(min, dist - 1);
    }

    private static void backtrack(int[][] grid, int n, int pivot) {
        // base
        if (n == 0) {
            bfs(grid);
            return;
        }
        for (int i = pivot; i < H * W; i++) {
            int r = i / W;
            int c = i % W;
            //action
            grid[r][c] = 0;
            //rec
            backtrack(grid, n - 1, i + 1);
            // backtracking
            grid[r][c] = -1;
        }

    }


}
