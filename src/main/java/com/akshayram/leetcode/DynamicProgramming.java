package com.akshayram.leetcode;

public class DynamicProgramming {

    //198. House Robber
    //TC: O(n) SC: O(n)
    public int rob_1(int[] nums) {
        int n = nums.length;
        if (n == 0)   return 0;
        int[] dp = new int[n+1];
        dp[0] = nums[0];
        dp[1] = nums[0];
        //dp[2] = Math.max(nums[0], nums[1]);
        for (int i = 1; i < n; i++) {
            dp[i+1] = Math.max(dp[i], nums[i] + dp[i-1]);
        }
        return dp[n];
    }

    //TC: O(n) SC: O(1)
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 2)   return nums[0];
        int prev = nums[0];
        int curr = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int temp = curr;
            curr = Math.max(curr, nums[i] + prev);
            prev = temp;
        }
        return curr;
    }

}
