package com.akshayram.s30.BitManipulation_1;

public class BitManipulation_1 {
    // TC: O(logn + logk) //SC: O(1)
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return (Integer.MAX_VALUE);
        }
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int res = 0;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        while (ldividend >= ldivisor) {
            int shifts = 0;
            while ((ldivisor << shifts) <= ldividend) {
                shifts++;
            }
            shifts--;
            res += (1 << shifts);
            ldividend = ldividend - (ldivisor << shifts);
        }
        if (dividend < 0 && divisor < 0)
            return res;
        else if (dividend > 0 && divisor > 0)
            return res;
        else
            return -res;
    }

    // TC: O(n) SC: O(1)
    public int singleNumber(int[] nums) {
        int ans = 0; // since XOR with 0 returns same number
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i]; // ans = (ans) XOR (array element at i)
        }
        return ans;
    }
}