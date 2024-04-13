package com.akshayram.matrix;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MaximalRectangle {
    //TC: O(R * C) SC: O(min(R,C))
    public int maximalRectangle1(char[][] matrix) {
        //null check
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix[0].length; //columns
        int[] heights = new int[n + 1];
        int maxArea = 0;

        for (char[] row : matrix) {
            for (int i = 0; i < n; i++) {
                heights[i] = row[i] == '1' ? heights[i] + 1 : 0;
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < n + 1; i++) {
                while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                    int h = heights[stack.pop()];
                    int w = i - stack.peek() - 1;
                    maxArea = Math.max(maxArea, h * w);
                }
                stack.push(i);
            }
        }

        return maxArea;
    }


    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Input matrix is null");
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (cols < rows) {
            return maximalRectangleHelper(matrix, rows, cols, true);
        } else {
            return maximalRectangleHelper(matrix, cols, rows, false);
        }
    }

    private int maximalRectangleHelper(char[][] matrix, int big, int small, boolean isColsSmall) {
        int[] heights = new int[small];
        int largestRectangle = 0;
        for (int i = 0; i < big; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= small; j++) {
                if (j < small) {
                    if (isColsSmall) {
                        heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
                    } else {
                        heights[j] = matrix[j][i] == '0' ? 0 : heights[j] + 1;
                    }

                }
                while (!stack.isEmpty() && (j == small || heights[stack.peek()] >= heights[j])) {
                    int h = heights[stack.pop()];
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    largestRectangle = Math.max(largestRectangle, (j - 1 - left) * h);
                }
                stack.push(j);
            }
        }
        return largestRectangle;
    }

    public static void main(String[] args) {
        //Test case 1
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximalRectangle maximalRectangle = new MaximalRectangle();
        System.out.println(maximalRectangle.maximalRectangle(matrix)); //Expected output: 6

    }
}