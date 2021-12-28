package com.cybertron.basicprograms;

public class PatternPrint {

	/*

	   *
	  ***
	 ******
	  ***
	   *

	 * */
	public static void main(String[] args) {
		int i, j, k;
		for (i = 1; i <= 3; i++) {
			for (j = 1; j <= 3 - i; j++)

			{
				System.out.print(" ");
			}

			for (k = 1; k <= 2 * i - 1; k++)

			{
				System.out.print("*");
			}
			if (i == 3 && k == 6)
				System.out.print("*");
			System.out.println();

		}
		for (i = 2; i > 0; i--) {
			for (j = 1; j <= 3 - i; j++)

			{
				System.out.print(" ");
			}
			for (j = 1; j <= 2 * i - 1; j++)

			{
				System.out.print("*");
			}
			System.out.println();
		}

	}
}