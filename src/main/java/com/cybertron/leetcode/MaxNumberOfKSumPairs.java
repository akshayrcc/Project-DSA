package com.cybertron.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfKSumPairs {

	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 2, 3, 4 };
		int[] arr2 = new int[] { 3, 1, 3, 4, 3 };
		int[] arr3 = new int[] { 3, 5, 1, 5 };
		int[] arr4 = new int[] { 4, 4, 1, 3, 1, 3, 2, 2, 5, 5, 1, 5, 2, 1, 2, 3, 5, 4 };// 2
		System.out.println("max Operations=" + maxOperations(arr4, 2));
	}
	
	public static  int maxOperations(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap();
		int count = 0;
		
		for(int n : nums) {
			if(map.containsKey(k - n) && map.get(k -n) > 0 ) {
				count++;
				map.put(k - n, map.get(k - n) - 1);
			} else {
				map.put(n, map.getOrDefault(n, 0) + 1);
			}
		}
		return count;
	}
}
