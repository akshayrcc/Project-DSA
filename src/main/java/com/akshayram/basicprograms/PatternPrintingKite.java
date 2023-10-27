package com.akshayram.basicprograms;

import java.util.Scanner;

public class PatternPrintingKite {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of lines for Kite Square to print: ");
        int x = s.nextInt();
        int i, j, k;
        for (i = 0; i <= x; i++) {
            for (j = i; j <= x; j++) {
                System.out.print("*");
            }
            for (k = 0; k < i; k++) {
                System.out.print("00");
            }
            for (j = i; j <= x; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (i = x - 1; i >= 0; i--) {
            for (j = i; j <= x; j++) {
                System.out.print("*");
            }
            for (k = 0; k < i; k++) {
                System.out.print("00");
            }
            for (j = i; j <= x; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
