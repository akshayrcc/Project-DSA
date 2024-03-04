package com.akshayram.matrix;

import java.util.HashSet;
import java.util.Set;

public class CountSubmatrix {

    public int countSubmatrices_bruteforce(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        // Iterate through all submatrices with the top-left element
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //calculate SUM
                int sum = 0;
                for (int x = 0; x <= i; x++) {
                    for (int y = 0; y <= j; y++) {
                        sum += grid[x][y];
                    }
                }
                if (sum <= k) count++;
            }

        }
        return count;
    }

    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        //prefix sum matrix
        int[][] prefix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    prefix[i][j] = grid[i][j];
                    continue;
                }
                prefix[i][j] = grid[i][j] + prefix[i][j - 1];
            }
        }

        // Iterate through all submatrices with the top-left element
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //calculate SUM
                int sum = prefix[i][j];
                if (i - 1 > 0 && j - 1 > 0) {
                    sum = prefix[i][j] + prefix[i][j - 1] + prefix[i - 1][j];
                }
                if (i - 1 > 0) {
                    sum = prefix[i][j] + prefix[i - 1][j];
                }
                if (j - 1 > 0) {
                    sum = prefix[i][j] + prefix[i][j - 1];
                }
                if (sum <= k) count++;
            }
        }
        return count;
    }

    public static int maxSumSubmatrix(int[][] matrix, int size) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        Set<Integer> maxSumSubmatrixSet = new HashSet<>();

        for (int i = 0; i <= rows - size; i++) {
            for (int j = 0; j <= cols - size; j++) {
                // Calculate the sum of the current size x size submatrix
                int submatrixSum = 0;
                Set<Integer> submatrixSet = new HashSet<>();
                for (int x = i; x < i + size; x++) {
                    for (int y = j; y < j + size; y++) {
                        submatrixSum += matrix[x][y];
                        submatrixSet.add(matrix[x][y]);
                    }
                }

                // Update maxSum and maxSumSubmatrixSet if necessary
                if (submatrixSum > maxSum) {
                    maxSum = submatrixSum;
                    maxSumSubmatrixSet = new HashSet<>(submatrixSet);
                }
            }
        }

        // Sum up distinct numbers from maxSumSubmatrixSet
        int distinctSum = 0;
        for (int num : maxSumSubmatrixSet) {
            distinctSum += num;
        }

        return distinctSum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 5, 6},
                {3, 3, 0, 3, 3},
                {2, 9, 2, 1, 2},
                {0, 2, 4, 2, 0}
        };
        int size = 2;
        int result = maxSumSubmatrix(matrix, size);
        System.out.println(result);  // Output: 29
    }
}
