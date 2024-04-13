package com.akshayram.matrix;

/*
 * It's a Java program that calculates the total amount of rainwater that can be trapped between buildings
 * represented by an array of integers. Each integer represents the height of a building.
 * */
public class TrappingRainWater {
    //TC: O(n) SC: O(n)
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        // pre-compute
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0]; // init
        rightMax[n - 1] = height[n - 1];
        for (int i = 1, j = n - 2; i < n; ++i, --j) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }
        // water
        int totalWater = 0;
        for (int k = 1; k < n - 1; ++k) { // do not consider the first and the last places
            int water = Math.min(leftMax[k - 1], rightMax[k + 1]) - height[k];
            totalWater += Math.max(water, 0);
        }
        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater trw = new TrappingRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}; //6
        System.out.println(trw.trap(height));
        int[] height2 = {4, 2, 0, 3, 2, 5}; //9
        System.out.println(trw.trap(height2));
    }
}
