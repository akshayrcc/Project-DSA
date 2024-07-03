package com.akshayram.contests;

public class IntuitBasic {
//Number of digits in a number -
// Create a function that will return an integer number corresponding to the amount of digits in the given integer num.
// Constraints: num >= 0

    // int num = 12345 output = 5
    // int num = 123 output = 5

    public static void main(String[] args) {
        int num = 123;
//        System.out.println("Ans is "+ getCountOfDigits(num));
//        System.out.println("Ans is " + getCountOfDigits(null));
//        System.out.println("Ans is " + getCountOfDigits("null"));
        System.out.println("Ans is " + getCountOfDigits("12345"));

    }

    private static int getCountOfDigits(String input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("Input is null");
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input is not a number");
        }
        int len = input.length();
        if (input.charAt(0) == '-') {
            len--;
        }
        return len;
    }


}
