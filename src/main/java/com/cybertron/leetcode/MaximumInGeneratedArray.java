package com.cybertron.leetcode;

public class MaximumInGeneratedArray {

	public static void main(String[] args) {

		System.out.println("Max = " + getMaximumGenerated(0));
		// System.out.println(Integer.MIN_VALUE);
	}

	public static int getMaximumGenerated(int n) {

		int arraySize = n + 1;
		if (arraySize <= 1)
			return 0;

		int nums[] = new int[arraySize];
		nums[0] = 0;
		if (arraySize > 1)
			nums[1] = 1;

		int evenCount = 0, oddCount = 0;
		if (n % 2 == 0) {
			evenCount = n / 2;
			oddCount = n / 2;
		} else {
			evenCount = n / 2;
			oddCount = (n / 2) + 1;
		}

		for (int i = 1; i <= Math.max(oddCount, evenCount); i++) {
			if (i <= evenCount) {
				nums[i * 2] = nums[i];
			}
			if (i < oddCount) {
				nums[(i * 2) + 1] = nums[i] + nums[i + 1];
			}
		}

//		for (int j = 1; j <= evenCount; j++) { //fill even values from 2
//			nums[j * 2] = nums[j];
//		}
//		
//		for (int k = 1; k < oddCount; k++) { //fill odd values from 3
//			nums[(k * 2) + 1] = nums[k] + nums[k + 1];
//		}

		int max = Integer.MIN_VALUE;
		for (int m = 0; m < nums.length; m++) {
			System.out.println(m + "th loc is" + nums[m] + " ");
			if (nums[m] > max)
				max = nums[m];
		}

		return max;
	}
}
