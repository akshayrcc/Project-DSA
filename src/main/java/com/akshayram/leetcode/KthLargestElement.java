package com.akshayram.leetcode;

import java.util.Arrays;

public class KthLargestElement {

	public static void main(String[] args) {
		System.out.println(findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));

	}

	public static int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[(nums.length - k)];
	}
}
