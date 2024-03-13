package com.akshayram.graphs;

class _01Matrix {
    static int[][] ans;
    static boolean[][] visitedMat;
    static int m;
    static int n;

    public static void main(String[] args) {
        System.out.println("Started...");
        int[][] mat = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] ans = updateMatrix(mat);

        System.out.println();
    }

    public static int[][] updateMatrix(int[][] mat) {
        m = mat.length;
        n = mat[0].length;

        ans = new int[m][n];
        visitedMat = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                    continue;
                }
                // one case
                int temp = bfs(mat, i, j);
                ans[i][j] = temp;
            }
        }
        return ans;
    }

    public static int bfs(int[][] inMat, int r, int c) {
        //check if out of boundary
        if ((r >= m || r < 0) || (c >= n || c < 0)) {
            return 0;
        }

        //if visited
        if (visitedMat[r][c]) {
            return ans[r][c];
        } else {
            visitedMat[r][c] = true;
        }

        //base condition
        if (inMat[r][c] == 0) {
            return 1;
        }

        //explore children
        if (inMat[r][c] != 0) {
            return 1 + Math.min(bfs(inMat, r - 1, c), Math.min(Math.min(bfs(inMat, r - 1, c), bfs(inMat, r + 1, c)), Math.min(bfs(inMat, r, c - 1), bfs(inMat, r, c + 1))));
        }
        return 0;
    }
}