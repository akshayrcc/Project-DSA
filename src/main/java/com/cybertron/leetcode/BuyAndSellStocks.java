package com.cybertron.leetcode;

public class BuyAndSellStocks {

    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        /*int ans = maxProfit_2(arr);
        System.out.println(ans);*/
    }

    public static int maxProfit_1a(int[] prices) {
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

    //Time Complexity O(n), Space Complexity O(1)
    public static int maxProfit_1b(int[] prices) {

        //APPROACH : One pass
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i]; //update new min
            else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }

        return max;
    }

    public static int maxProfit_2(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1])
                ans += prices[i] - prices[i - 1];
        }
        return ans;
    }


}
