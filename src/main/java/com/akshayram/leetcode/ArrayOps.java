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
        ans = Arrays.copyOf(nums1,k);
        return ans;
    }

    public int[] intersect_2(int[] nums1, int[] nums2) {
        int[] freq= new int[1001];
        //Setting the freq of each number in nums1
        for(int i:nums1){
            freq[i]++;
        }
        int[] res = new int[1001];
        int k=0;
        for (int j: nums2)
        {
            if(freq[j]>0){
                freq[j]--;
                res[k++]=j;
            }
        }
        return Arrays.copyOfRange(res,0,k);
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
}