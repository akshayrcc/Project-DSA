package com.akshayram.dp;

import java.util.Arrays;

public class PerfectSquares {

    //TC: O(n* sqrt(n)) SC:O(n)
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //TC: O(n) SC:O(n)
    public int numSquares2(int n) {
        while (n % 4 == 0) {  // Eliminate factors of 4
            n /= 4;
        }
        if (n % 8 == 7) {  // Can only be represented by 4 numbers
            return 4;
        }
        for (int i = 0; i * i <= n; i++) {  // Go and see if it's 2 numbers or 1 number
            int a = (int) Math.sqrt(n - i * i);
            if (a * a + i * i == n) {
                return i > 0 && a > 0 ? 2 : 1;
            }
        }
        return 3;  // The rest can only be represented by 3 numbers
    }
}


