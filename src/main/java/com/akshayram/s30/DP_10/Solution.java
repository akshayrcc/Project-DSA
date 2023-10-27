package com.akshayram.s30.DP_10;

class Solution {
  // TC: O(n*k) SC: O(n*k)
  public int superEggDrop(int k, int n) {
    int[][] dp = new int[n + 1][k + 1];
    int attempts = 0;
    while (dp[attempts][k] < n) {
      attempts++;
      for (int j = 1; j <= k; j++) {
        dp[attempts][j] = 1 + dp[attempts - 1][j - 1] + dp[attempts - 1][j];
      }
    }
    return attempts;
  }

  // TC: O(n^3) SC: O(n^2)
  public int maxCoins(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int n = nums.length;
    int[][] dp = new int[n][n];
    for (int le = 1; le <= n; le++) {
      // get the burstible arrays
      for (int i = 0; i <= n - le; i++) {
        int j = i + le - 1;
        for (int k = i; k <= j; k++) {
          // before + leftMain*ballonitself*rightMain + after
          // before -- All ballons on left of current ballon which are already burst
          int before = 0;
          if (i != k) {
            before = dp[i][k - 1];
          }
          int after = 0;
          if (k != j) {
            after = dp[k + 1][j];
          }
          int leftMain = 1;
          if (i != 0) {
            leftMain = nums[i - 1];
          }
          int rightMain = 1;
          if (j != n - 1) {
            rightMain = nums[j + 1];
          }
          int ballonitself = nums[k];
          dp[i][j] = Math.max(dp[i][j], before + leftMain * ballonitself * rightMain + after);
        }
      }
    }
    return dp[0][n - 1];
  }
}
