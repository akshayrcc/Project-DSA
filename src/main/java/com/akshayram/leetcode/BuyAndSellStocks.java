package com.akshayram.leetcode;

public class BuyAndSellStocks {

    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        int ans = maxProfit_2(arr);
        System.out.println(ans);
        int[] arr3 = {1, 2, 3, 4, 5};
        int[] arr3b = {3, 3, 5, 0, 0, 3, 1, 4};
        int ans3 = maxProfit_3(arr3b);
        System.out.println("Stock3: " + ans3);
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

        if (prices == null || prices.length <= 1) return 0;

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

        if (prices == null || prices.length <= 1) return 0;

        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1])
                ans += prices[i] - prices[i - 1];
        }
        return ans;
    }

    public static int maxProfit_3(int[] prices) {

        if (prices == null || prices.length == 0) return 0;

        int firstcost = Integer.MAX_VALUE;
        int secondcost = Integer.MAX_VALUE;

        int firstprofit = 0;
        int secondprofit = 0;

        for (int price : prices) {

            // first
            firstcost = Math.min(price, firstcost);
            firstprofit = Math.max(price - firstcost, firstprofit);

            // second
            secondcost = Math.min(price - firstprofit, secondcost);
            secondprofit = Math.max(price - secondcost, secondprofit);
        }

        return secondprofit;
    }
}