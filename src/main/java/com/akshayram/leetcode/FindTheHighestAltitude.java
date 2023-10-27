package com.akshayram.leetcode;

public class FindTheHighestAltitude {

	public static void main(String[] args) {
		int[] gain1 = new int[] {-4,-3,-2,-1,4,3,2};
		int[] gain2 = new int[] {-5,1,5,0,-7};
		int[] gain3 = new int[]{-4,-3,-2,-1,4,3,2};
		System.out.println("largestAltitude: "+largestAltitude(gain3)); 
	}

	public static int largestAltitude(int[] gain) {
		int[] altitudes = new int[gain.length + 1];
		int current_altitude = 0;
		int max_altitude = Integer.MIN_VALUE;
		altitudes[0] = current_altitude;
		for (int i = 0; i < gain.length; i++) {
			current_altitude += gain[i];
			altitudes[1 + i] = current_altitude;
			if (current_altitude > max_altitude) {
				max_altitude = current_altitude;
			}
		}
		if(max_altitude <0) {
			max_altitude=0;
		}
		//System.out.println(altitudes.length);
		for (int i = 0; i < altitudes.length; i++) {
			System.out.print(altitudes[i] + ", ");
		}
		System.out.println();
		return max_altitude;
	}
}
