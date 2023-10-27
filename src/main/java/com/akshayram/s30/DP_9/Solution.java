package com.akshayram.s30.DP_9;

import java.util.Arrays;

class Solution {
  // TC: O(nlogn) SC: O(n)
  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] arr = new int[n];
    arr[0] = nums[0];
    int len = 1;
    for (int i = 1; i < n; i++) {
      if (nums[i] > arr[len - 1]) {
        arr[len] = nums[i];
        len++;
      } else {
        // replace the number just grater than nums[i] in arr
        int bsIndex = binarySearch(arr, 0, len - 1, nums[i]);
        arr[bsIndex] = nums[i];
      }
    }
    return len;
  }

  private int binarySearch(int[] arr, int low, int high, int target) {
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  // TC: O(nlogn) SC: O(n)
  public int maxEnvelopes(int[][] envelopes) {
    int n = envelopes.length;
    int[] arr = new int[n];
    Arrays.sort(
        envelopes,
        (a, b) -> {
          if (a[0] == b[0]) {
            return b[1] - a[1];
          }
          return a[0] - b[0];
        });

    arr[0] = envelopes[0][1];
    int le = 1;
    for (int i = 1; i < n; i++) {
      if (envelopes[i][1] > arr[le - 1]) {
        arr[le] = envelopes[i][1];
        le++;
      } else {
        int bsIndex = binarySearch(arr, 0, le - 1, envelopes[i][1]);
        arr[bsIndex] = envelopes[i][1];
      }
    }
    return le;
  }
}
