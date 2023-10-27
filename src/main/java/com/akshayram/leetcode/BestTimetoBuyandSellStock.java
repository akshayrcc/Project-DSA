package com.akshayram.leetcode;

public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int min = prices[0]; // min so far
        int result = 0;

        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return result;
    }

    public int maxProfit_2(int[] prices) {
        int maxProfit = 0;
        int currMin = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price > currMin) {
                maxProfit = Math.max(maxProfit, price - currMin);
            } else {
                currMin = price;
            }
        }
        return maxProfit;
    }
}
