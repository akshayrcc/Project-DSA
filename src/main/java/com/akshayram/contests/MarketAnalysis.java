package com.akshayram.contests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MarketAnalysis {


    public int getMaxNegativePnL(int[] PnL) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int sum = 0;
        int n = PnL.length;
        int ans = 0;
        for(int i=0; i<n; i++){
            sum -= PnL[i];
            pq.offer(-PnL[i]);
            while(sum < 0){
                sum -= pq.poll() + 2;
            }
            ans = Math.max(ans, pq.size());
        }
        return ans;
    }

    public int getMaxNegativePnL1(int[] PnL) {
        int n = PnL.length;

        // Calculate the prefix sums
        int[] prefixSum = new int[n];
        prefixSum[0] = PnL[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + PnL[i];
        }

        // Now we need to consider when turning positive values negative might
        // lead to a positive cumulative sum. This involves checking which
        // values have the least impact on the prefix sums.

        // We keep track of the smallest prefix sum we can achieve by turning
        // some values negative.
        int minPrefix = Integer.MAX_VALUE;
        int maxNegations = 0;

        // Iterate over the prefix sums in reverse order to see if turning some
        // numbers negative can keep the cumulative sum positive
        for (int i = n - 1; i >= 0; i--) {
            if (prefixSum[i] > minPrefix) {
                // If turning a value negative keeps the cumulative sum positive,
                // count it as a valid negation
                maxNegations++;
            }
            // Update the smallest prefix sum
            minPrefix = Math.min(minPrefix, prefixSum[i] - 2 * PnL[i]);
        }

        return maxNegations;
    }

    public int getMaxNegativePnL2(int[] PnL) {
        int n = PnL.length;
        int[] cumulativeSum = new int[n];

        // Create cumulative sum array
        cumulativeSum[0] = PnL[0];
        for (int i = 1; i < n; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + PnL[i];
        }

        // We can consider flipping the elements with the least impact on the cumulative sum.
        // To maximize the allowed negative numbers, sort PnL array in ascending order
        // as we would like to flip the smallest numbers first.
        Integer[] sortedPnL = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedPnL[i] = PnL[i];
        }
        Arrays.sort(sortedPnL, Collections.reverseOrder());

        int countFlips = 0;
        int cumulative = 0;

        // Iterate through the sorted PnL and calculate the maximum flips we can make
        for (int i = 0; i < n; i++) {
            cumulative += sortedPnL[i];
            if (cumulative < sortedPnL[i]) {  // Check if flipping this value results in negative cumulative
                break;  // If so, we can't flip any more values
            }
            countFlips++;
            cumulative -= 2 * sortedPnL[i];  // Adjust cumulative sum with flip
        }

        return countFlips;
    }


//    public static int maxLosses(int[] pnl) {
////        List<Integer> pnlList
////        int n = pnlList.size();
////        int[] pnl = pnlList.stream().mapToInt(i -> i).toArray();
//
//        int n = pnl.length;
//        Arrays.sort(pnl);
//        int[] prefixSum = new int[n];
//        int[] suffixSum = new int[n];
//
//        prefixSum[0] = pnl[0];
//        suffixSum[n - 1] = pnl[n - 1];
//        for (int i = 1; i < n; i++) {
//            prefixSum[i] = prefixSum[i - 1] + pnl[i];
//            suffixSum[n - i - 1] = suffixSum[n - i] + pnl[n - i - 1];
//        }
//
//        int low = 0;
//        int high = n - 1;
//        while (low <= high) {
//            int mid = low + (high - low) / 2;
//            if (suffixSum[mid + 1] > prefixSum[mid]) {
//                if (mid + 2 < n && suffixSum[mid + 2] <= prefixSum[mid + 1]) {
//                    return mid + 1;
//                }
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//
//        return -1;
//    }

    public static int maxLosses(List<Integer> pnlList) {
        int n = pnlList.size();

        // Sort the list
        Collections.sort(pnlList);

        // Initialize prefix and suffix arrays
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];

        // Compute prefix and suffix sums
        prefixSum[0] = pnlList.get(0);
        suffixSum[n - 1] = pnlList.get(n - 1);

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + pnlList.get(i);
            suffixSum[n - i - 1] = suffixSum[n - i] + pnlList.get(n - i - 1);
        }

        // Use binary search to find the max number of possible losses
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (suffixSum[mid + 1] > prefixSum[mid]) {
                if (mid + 2 < n && suffixSum[mid + 2] <= prefixSum[mid + 1]) {
                    return mid + 1;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        MarketAnalysis analysis = new MarketAnalysis();

        List<Integer> PnL1 = Arrays.asList(5, 2, 3, 5, 2, 3);
        int ans1 = maxLosses(PnL1);
        System.out.println("Test Case 1: " + ans1); // Expected: 2

        List<Integer> PnL2 = Arrays.asList(1, 1, 1, 1, 1);
//        int[] PnL2 = {1, 1, 1, 1, 1};
        int ans2 = maxLosses(PnL2); //analysis.getMaxNegativePnL2(PnL2);
        System.out.println("Test Case 2: " + ans2); // Expected: 2

        List<Integer> PnL3 = Arrays.asList(5, 2, 3, 5, 2, 3);
//        int[] PnL3 = {5, 2, 3, 5, 2, 3};
        int ans3 =  maxLosses(PnL3); //analysis.getMaxNegativePnL2(PnL3);
        System.out.println("Test Case 3: " + ans3); // Expected: 3
    }
}
