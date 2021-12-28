package com.cybertron.basicprograms;

import java.util.Scanner;

public class PatternPrinting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int i,j,k;
		for(i=1;i<=x;i++) {
			for(j=i;j<=x;j++) {
				System.out.print("*");
			}
			for(k=x;k>=0;k--) {
				System.out.print(" ");
			}
			System.out.println();
		}
		for(i=x-1;i>=0;i--) {
			for(j=i;j<x;j++) {
				System.out.print("*");
			}
			for(k=x;k>=0;k--) {
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
