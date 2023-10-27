package com.akshayram.s30.DP_1;

public class Solution {

  // TC: O(n*m) SC: O(n*m)
  public int coinChange(int[] coins, int amount) {
    int m = amount;
    int n = coins.length;
    int[][] dp = new int[n + 1][m + 1];
    for (int j = 1; j <= m; j++) {
      dp[0][j] = amount + 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (j < coins[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
        }
      }
    }
    if (dp[n][m] > amount) return -1;
    return dp[n][m];
  }

  // TC: O(n) O(1)
  public int rob(int[] nums) {
    if (nums.length < 2) return nums[0];
    int n = nums.length;
    int[] dp = new int[n];
    int prev = nums[0];
    int current = Math.max(nums[0], nums[1]);
    for (int i = 2; i < n; i++) {
      int temp = current;
      current = Math.max(current, nums[i] + prev);
      prev = temp;
    }
    return current;
  }
}
