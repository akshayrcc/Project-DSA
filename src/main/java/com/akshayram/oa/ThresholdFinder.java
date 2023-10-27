package com.akshayram.oa;

import java.util.Arrays;

public class ThresholdFinder {
//    public static double findThreshold(int[] nums, int desiredSum, double precision) {
//        double low = 0;
//        double high = Arrays.stream(nums).max().getAsInt();
//        while (Math.abs(low - high) > precision) {
//            double mid = low + (high - low) / 2;
//            double newSum = 0;
//            for (int num : nums) {
//                newSum += Math.min(num, mid);
//            }
//            if (newSum > desiredSum) {
//                high = mid;
//            } else {
//                low = mid;
//            }
//        }
//        return low;
//    }

//    public static double findThreshold(double[] nums, double desiredSum, double precision) {
//        double low = 0;
//        double high = getMax(nums);
//
//        while (Math.abs(low - high) > precision) {
//            double mid = low + (high - low) / 2;
//            double newSum = 0;
//            for (double num : nums) {
//                newSum += Math.min(num, mid);
//            }
//            if (newSum > desiredSum) {
//                high = mid;
//            } else {
//                low = mid;
//            }
//        }
//
//        return low;
//    }

    public static final double EPSILON = 1e-9; // EPSILON is very small non-zero value
    public static double findThreshold(double[] nums, double desiredSum) {
        double low = 0;
        //double high = getMax(nums);
        double high = Arrays.stream(nums).max().orElse(0.0);
        // Perform binary search to find the threshold
        while (Math.abs(low - high) > EPSILON) {
            double mid = low + (high - low) / 2;
            // Calculate the sum of modified values using the midpoint as the threshold
            double newSum = 0;
            for (double num : nums) {
                newSum += Math.min(num, mid);
            }
            // Adjust the search range based on the calculated sum
            if (newSum > desiredSum) {
                high = mid;
            } else {
                low = mid;
            }
        }
        // Return the calculated threshold value
        return low;
    }

    private static double getMax(double[] nums) {
        double max = Double.NEGATIVE_INFINITY;
        for (double num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

//    public static double findThreshold(double[] nums, double desiredSum) {
//        // Sort the input array in ascending order
//        Arrays.sort(nums);
//
//        // Calculate the threshold value
//        int n = nums.length;
//        double remainingSum = desiredSum;
//        for (int i = 0; i < n - 1; i++) {
//            double diff = nums[i + 1] - nums[i];
//            double potentialSum = (i + 1) * diff;
//            if (remainingSum >= potentialSum) {
//                remainingSum -= potentialSum;
//            } else {
//                return nums[i] + remainingSum / (i + 1);
//            }
//        }
//
//        // If we've processed all elements and remainingSum is not zero, adjust the last element
//        return nums[n - 1] + remainingSum / n;
//    }



    public static void main(String[] args) {
//        int[] input1 = {2, 1, 5};
//        int desiredSum1 = 6;
//        double threshold1 = findThreshold(input1, desiredSum1, 0.001);
//        System.out.println(threshold1);  // Output: 3.0

//        int[] input2 = {2, 1, 5};
//        int desiredSum2 = 2;
//        double threshold2 = findThreshold(input2, desiredSum2, 0.001);
//        System.out.println(threshold2);  // Output: 0.666

//        int[] input2 = {4, 1, 3, 2, 6};
//        int desiredSum2 = 10;
//        double threshold2 = findThreshold(input2, desiredSum2, 0.001);
//        System.out.println(threshold2);  // Output: 2.332763671875

        double[] input2 = {0, 2, 2.567, 4, 6};
        double desiredSum2 = 10;
        double threshold2 = findThreshold(input2, desiredSum2);
        System.out.println(threshold2);  // Output: 2.72
    }
}
