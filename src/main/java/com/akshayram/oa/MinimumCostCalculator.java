package com.akshayram.oa;

public class MinimumCostCalculator {
  public static int getMinimumCost(int[] arr) {
    int cost = 0;
    int maxCost = 0;
    int firstIndex = 0;
    int secondIndex = 0;

    // Find max difference between neighboring elements
    for (int i = 0; i < arr.length - 1; i++) {
      int first = arr[i];
      int second = arr[i + 1];
      if (Math.abs(second - first) > maxCost) {
        maxCost = Math.abs(second - first);
        firstIndex = i;
        secondIndex = i + 1;
      }
    }

    // Calculate the new number between the max difference
    int mid = (arr[firstIndex] + arr[secondIndex]) / 2;

    // Add the cost
    cost += (arr[firstIndex] - mid) * (arr[firstIndex] - mid);
    cost += (arr[secondIndex] - mid) * (arr[secondIndex] - mid);

    // Add the cost for the rest of the elements
    for (int i = 0; i < arr.length - 1; i++) {
      if (i == firstIndex) {
        continue;
      }
      int first = arr[i];
      int second = arr[i + 1];
      cost += (first - second) * (first - second);
    }

    return cost;
  }

  public static void main(String[] args) {
    int[] arr = {4, 7, 1, 4};
    int result = getMinimumCost(arr);
    System.out.println(result); // Output: 36
  }
}
