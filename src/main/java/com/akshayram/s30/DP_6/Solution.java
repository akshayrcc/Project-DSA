package com.akshayram.s30.DP_6;

class LongestPalindromicSubStr {
    int start, end;

    // TC: O(n^2) SC: O(1)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < n; i++) {
            extendPalindrome(s, i, i); // odd palindrome check
            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                extendPalindrome(s, i, i + 1); // even palindrome check
            }
        }
        return s.substring(start, end + 1);
    }

    public void extendPalindrome(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        left++;
        right--;
        if (right - left > end - start) {
            start = left;
            end = right;
        }
    }
}

class LongestPalindromicSubStr_DP {
    int start, end;

    // TC: O(n^2) SC: O(n^2)
    public String longestPalindrome_2D(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        if (s.length() < 2) {
            return s;
        }
        boolean dp[][] = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i - j < 2 || dp[i - 1][j + 1]) {
                        dp[i][j] = true;
                        if (end - start < i - j) {
                            start = j;
                            end = i;
                        }
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    // TC: O(n^2) SC: O(n)
    public String longestPalindrome_1D(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        if (s.length() < 2) {
            return s;
        }
        boolean dp[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i - j < 2 || dp[j + 1]) {
                        dp[j] = true;
                        if (end - start < i - j) {
                            start = j;
                            end = i;
                        }
                    } else {
                        dp[j] = false;
                    }
                } else {
                    dp[j] = false;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}

class UglyNumber2 {
    // TC: O(n) SC: O(1)
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            if (factor2 == min)
                factor2 = 2 * ugly[++index2];
            if (factor3 == min)
                factor3 = 3 * ugly[++index3];
            if (factor5 == min)
                factor5 = 5 * ugly[++index5];
        }
        return ugly[n - 1];
    }
}

public class Solution {
}