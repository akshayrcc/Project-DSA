package com.akshayram.hackerrank;

class GlobalMaximum {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int arr[] = new int[]{2, 3, 5, 9};
        int n = arr.length;
        int m = 3;

        int dp[][] = new int[n][m];
        //dp[i][j] means maximum for subsequence of length (j+1) ending at index i
        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            int max = 0;
        }

        double max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < i; k++) {
                    System.out.println(k+" "+i);
                    dp[i][j] = Math.max(dp[i][j], Math.min(arr[i] - arr[k], dp[k][j - 1]));
                }
            }
            //final result is subsequece of length(m) ending at any index
            max = Math.max(max, dp[i][m - 1]);
        }
        System.out.println(max);
    }
}