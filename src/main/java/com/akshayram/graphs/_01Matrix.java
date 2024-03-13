package com.akshayram.graphs;

class _01Matrix {
    static int[][] ans;
    static boolean[][] visitedMat;
    static int m;
    static int n;

    public static void main(String[] args) {
        System.out.println("Started...");
        int[][] mat = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] ans = updateMatrix(mat);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println(" ");
        }


    }

    public static int[][] updateMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int MAX_DISTANCE = rows + cols + 1;

        // First pass: check for left and top neighbours
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 0) {
                    grid[i][j] = MAX_DISTANCE;
                    grid[i][j] = Math.min(grid[i][j], Math.min(i > 0 ? grid[i - 1][j] + 1 : MAX_DISTANCE,
                            j > 0 ? grid[i][j - 1] + 1 : MAX_DISTANCE));
                }
            }
        }

        // Second pass: check for the bottom and right neighbours.
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                grid[i][j] = Math.min(grid[i][j], Math.min(i < rows - 1 ? grid[i + 1][j] + 1 : MAX_DISTANCE,
                        j < cols - 1 ? grid[i][j + 1] + 1 : MAX_DISTANCE));
            }
        }
        return grid;
    }


}