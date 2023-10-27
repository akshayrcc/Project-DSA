package com.akshayram.s30.DP_7;

class Solution {
  // TC: O(mn) SC: O(mn)
  public boolean isMatch(String source, String pattern) {
    if (source.equals(pattern)) return true;
    int m = source.length();
    int n = pattern.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    // top row
    for (int j = 1; j <= n; j++) {
      char c = pattern.charAt(j - 1);
      if (c == '*') {
        dp[0][j] = dp[0][j - 2];
      }
    }

    // rest of matrix*
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        char pChar = pattern.charAt(j - 1);
        if (pChar != '*') {
          if (pChar == source.charAt(i - 1) || pChar == '.') {
            dp[i][j] = dp[i - 1][j - 1];
          }
        } else {
          if (pattern.charAt(j - 2) == source.charAt(i - 1) || pattern.charAt(j - 2) == '.') {
            dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
          } else {
            dp[i][j] = dp[i][j - 2];
          }
        }
      }
    }
    return dp[m][n];
  }

  // TC: O(mn) SC: (n)
  public int minDistance(String word1, String word2) {
    if (word1.equals(word2)) return 0;
    int m = word1.length();
    int n = word2.length();
    if (m < n) return minDistance(word2, word1);
    int[] dp = new int[n + 1];
    for (int j = 0; j <= n; j++) {
      dp[j] = j;
    }
    int diagUp = 0;
    for (int i = 1; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        int temp = dp[j];
        if (j == 0) {
          dp[j] = i;
        } else {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[j] = diagUp;
          } else {
            dp[j] = 1 + Math.min(dp[j - 1], Math.min(dp[j], diagUp));
          }
        }
        diagUp = temp;
      }
    }
    return dp[n];
  }
}
