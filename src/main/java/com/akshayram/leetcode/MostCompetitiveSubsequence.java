package com.akshayram.leetcode;

import java.util.Arrays;

public class MostCompetitiveSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[] nums = new int[] { 2, 4, 3, 3, 5, 4, 9, 6 }; //4
		int[] nums = new int[] { 3, 5, 2, 6 }; // 2
		System.out.println(Arrays.toString(mostCompetitive(nums, 2)));
	}

	public static int[] mostCompetitive(int[] nums, int k) {
		int[] stack = new int[k];
		for (int i = 0, j = 0; i < nums.length; i++) {
			while (j > 0 && stack[j - 1] > nums[i] && j + nums.length - i > k) {
				j--;
			}
			if (j < k) {
				stack[j++] = nums[i];
			}
		}
		return stack;
	}
}
