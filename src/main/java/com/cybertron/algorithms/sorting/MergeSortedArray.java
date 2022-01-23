package com.cybertron.algorithms.sorting;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {
        System.out.println(" Started ");

        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        int m = 3;
        int n = 3;
        merge2(nums1, m, nums2, n);
        System.out.println(" Ans Array: ");
        for (int i : nums1) {
            System.out.print(" " + i);
        }

    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //nums1 is the final merged sorted array.
        if (n == 0) {
            //return num1 as num2 has nothing to merge
            return;
        } else if (m == 0) {
            //copy num2 into num1 and return
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        
        int p1 = m - 1;
        int p2 = n - 1;
        int idx = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] <= nums2[p2]) {
                nums1[idx--] = nums2[p2--];
            } else {
                nums1[idx--] = nums1[p1--];
            }
        }
        while (p2 >= 0) {
            nums1[idx--] = nums2[p2--];
        }
    }

    private static void swapValues(int[] nums1, int[] nums2, int i, int j) {
        int temp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = temp;
    }
}
