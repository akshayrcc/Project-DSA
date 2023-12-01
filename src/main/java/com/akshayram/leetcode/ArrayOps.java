package com.akshayram.leetcode;

import java.util.*;

class ArrayOps {

  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return intersect(nums2, nums1);
    }
    Map<Integer, Integer> m = new HashMap<>();
    for (int val : nums1) {
      m.put(val, m.getOrDefault(val, 0) + 1);
    }
    int k = 0;
    for (int val : nums2) {
      if (m.getOrDefault(val, 0) > 0) {
        nums1[k++] = val;
        m.put(val, m.get(val) - 1);
      }
    }
    int[] ans = new int[k];
    ans = Arrays.copyOf(nums1, k);
    return ans;
  }

  public int[] intersect_2(int[] nums1, int[] nums2) {
    int[] freq = new int[1001];
    // Setting the freq of each number in nums1
    for (int i : nums1) {
      freq[i]++;
    }
    int[] res = new int[1001];
    int k = 0;
    for (int j : nums2) {
      if (freq[j] > 0) {
        freq[j]--;
        res[k++] = j;
      }
    }
    return Arrays.copyOfRange(res, 0, k);
  }

  public void rotate(int[] nums, int k) {
    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  public void reverse(int[] arr, int start, int end) {
    while (start < end) {
      int temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
  }

  // compare if two string arrays are same string pieces
  // TC: O(n * k) SC: O(1)
  public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
    int len1 = word1.length;
    int len2 = word2.length;
    int i = 0, j = 0, char_i = 0, char_j = 0, w1count = 0, w2count = 0;
    String w1;
    String w2;
    while (i < len1 && j < len2) {
      w1 = word1[i];
      w2 = word2[j];

      if (char_i >= w1.length()) {
        char_i = 0;
        i++;
        continue;
      }
      if (char_j >= w2.length()) {
        char_j = 0;
        j++;
        continue;
      }

      char c1 = w1.charAt(char_i);
      char c2 = w2.charAt(char_j);

      char_i++;
      char_j++;

      w1count++;
      w2count++;

      if (c1 != c2) {
        return false;
      }
    }

    if (i < len1) {
      w1 = word1[i];
      if (char_i < w1.length()) w1count++;
    }

    if (j < len2) {
      w2 = word2[j];
      if (char_j < w2.length()) w2count++;
    }

    if (w1count != w2count) return false;
    return true;
  }
}
