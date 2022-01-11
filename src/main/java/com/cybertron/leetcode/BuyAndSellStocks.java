package com.cybertron.leetcode;

public class BuyAndSellStocks {

    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit(arr);
        System.out.println(ans);
    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1])
                ans += prices[i] - prices[i - 1];
        }
        return ans;
    }
}
