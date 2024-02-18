package com.akshayram.heap;

import java.util.PriorityQueue;

public class KthSmallest {

    // TC: O(n log k)  SC: O(1)
    public static int kthSmallest_1(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a));  //max heap
        //loop through all numbers and build heap...
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                pq.add(matrix[i][j]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo < hi) {
            int mid = lo + (hi - 10) / 2;
            int rank = getRank(matrix, mid);
            if (rank < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    //calculate rank of # in matrix
    public int getRank(int[][] matrix, int target) {
        int cnt = 0, N = matrix.length, i = N - 1, j = 0;
        while (i >= 0 && j < N) {
            if (matrix[i][j] > target) i--;
            else {
                cnt = cnt + i + 1;
                j++;
            }
        } return cnt;
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{0, 15}, {9, 18}, {16, 25}, {26, 30}, {31, 35}};
    }

}
