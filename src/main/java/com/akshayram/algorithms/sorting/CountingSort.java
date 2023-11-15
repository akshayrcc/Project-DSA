package com.akshayram.algorithms.sorting;

import java.util.Arrays;

public class CountingSort {
  public static void countingSort(int[] array) {
    // Find the maximum value to determine the range
    int max = getMax(array);

    // Create a counting array to store the count of each unique value
    int[] count = new int[max + 1];

    // Populate the counting array with the count of each element
    for (int value : array) {
      count[value]++;
    }

    // Modify the counting array to store cumulative count
    for (int i = 1; i <= max; i++) {
      count[i] += count[i - 1];
    }

    // Create an output array to store the sorted result
    int[] output = new int[array.length];

    // Traverse the input array to populate the output array
    for (int i = array.length - 1; i >= 0; i--) {
      int value = array[i];
      int position = count[value] - 1;
      output[position] = value;
      count[value]--;
    }

    // Copy the sorted array back to the original array
    System.arraycopy(output, 0, array, 0, array.length);
  }

  private static int getMax(int[] array) {
    int max = array[0];
    for (int value : array) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] array = {4, 2, 2, 8, 3, 3, 1};
    System.out.println("Original Array: " + Arrays.toString(array));

    countingSort(array);

    System.out.println("Sorted Array: " + Arrays.toString(array));
  }
}
