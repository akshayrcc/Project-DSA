package com.akshayram.leetcode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MaxNumberOfKSumPairs_TL_exceeded {

	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 2, 3, 4 };
		int[] arr2 = new int[] { 3, 1, 3, 4, 3 };
		int[] arr3 = new int[] { 3, 5, 1, 5 };
		int[] arr4 = new int[] { 4, 4, 1, 3, 1, 3, 2, 2, 5, 5, 1, 5, 2, 1, 2, 3, 5, 4 };// 2
		System.out.println("max Operations=" + maxOperations(arr4, 2));
	}

	/* Hashmap stores distinct numbers from array and its count of occurrences */
	static ConcurrentHashMap<Integer, Integer> hmap = new ConcurrentHashMap<>();

	public static int maxOperations(int[] nums, int k) {
		int operations = 0;

		/* Initialise hashmap with keys and counts */
		for (int number : nums) {
			Integer i = hmap.get(number);
			if (i == null)
				hmap.put(number, 1);
			else
				hmap.put(number, i + 1);
		}

		displayCurrMap();
		
		// for (Map.Entry<Integer, Integer> entry : hmap.entrySet()) {
		while (!hmap.isEmpty()) {
			int key = hmap.keySet().iterator().next();
			removeKey(key);
			if (hmap.containsKey(k - key)) {
				// found pair .. key already removed...now remove the pair
				removeKey(k - key);
				operations++;
			}

		}
		return operations;
	}

	public static void removeKey(int key) {
		if (hmap.containsKey(key)) {
			int count = hmap.get(key);
			if (count > 1) {
				count--;
				hmap.replace(key, count);
			} else if (count == 1) {
				hmap.remove(key);
			}
		}
		System.out.println("Removed Key "+key);
		displayCurrMap();
	}

	public static void displayCurrMap() {
		System.out.println("Current Hashmap is here:");
		for (Map.Entry<Integer, Integer> entry1 : hmap.entrySet()) {
			System.out.println("=>" + entry1.getKey() + " " + entry1.getValue());
		}
	}

}
