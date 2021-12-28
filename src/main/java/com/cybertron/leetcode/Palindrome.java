package com.cybertron.leetcode;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Ans " + isPalindrome(-1234));
		System.out.println("Ans " + isPalindrome(12321));
	}

	public static boolean isPalindrome(int x) {
		
		String str = String.valueOf(x);
		System.out.println("Given "+ str);
		int strLength = str.length();	
		System.out.println("strLength "+ strLength);
		for(int i=0;i<strLength/2 + 1;i++) {
			if(str.charAt(i) != str.charAt(strLength-1-i)) {
				System.out.println("Here at i"+ i);
				return false;
			}
		}
		return true;
	}

}
