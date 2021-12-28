package com.cybertron.java8;

import java.util.Arrays;

public class Solution1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Ans" + solution(new int[] {1, -2, -3, 5}));
	}
	
	
	
	public static int solution(int[] A) {
        // write your code in Java SE 8
		int retVal = 1;
		for (int i = 0; i < A.length; i++) {
			retVal += Integer.signum(A[i]);
		}
		return retVal;	
		
		//int prod = Arrays.stream(A).reduce(1, (a, b) -> (a * b));
		
		
    }
}
