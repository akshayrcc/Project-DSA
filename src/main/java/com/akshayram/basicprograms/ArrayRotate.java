package com.akshayram.basicprograms;

public class ArrayRotate {
  public static void main(String[] args) {
    // Test case 1
    int[] nums1 = {1, 2, 3, 4, 5};
    int k1 = 2;
    rotate(nums1, k1);
    for (int val : nums1) {
      System.out.println("num1:");
      System.out.print(" " + val);
    }

    //        System.out.println(assertEquals([4, 5, 1, 2, 3], nums1));

    // Test case 2
    int[] nums2 = {7, 6, 5, 4, 3, 2, 1};
    int k2 = 3;
    rotate(nums2, k2);
    //        assertEquals([5, 4, 3, 2, 1, 7, 6], nums2);

  }

  public static void rotate(int[] nums, int k) {
    if (k > nums.length) k = k % nums.length;
    int[] result = new int[nums.length];
    for (int i = 0; i < k; i++) {
      result[i] = nums[nums.length - k + i];
    }
    int j = 0;
    for (int i = k; i < nums.length; i++) {
      result[i] = nums[j];
      j++;
    }
    System.arraycopy(result, 0, nums, 0, nums.length);
  }
}
