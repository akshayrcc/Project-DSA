package com.cybertron.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static void main(String[] args) {
        int number = 333;
        for (int i = 1; i <= number; i++) {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    /**
     * If number is divisible by 3, return "Fizz". If divisible by 5,
     * return "Buzz", and if divisible by both, return "FizzBuzz". Otherwise,
     * return the number itself.
     *
     * @Throws IllegalArgumentException for values < 1
     */
    public String fizzBuzz(int number) throws IllegalArgumentException {

        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        if (number % 15 == 0) {
            //System.out.print("FizzBuzz");
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            //System.out.print("Fizz");
            return "Fizz";
        } else if (number % 5 == 0) {
            //System.out.print("Buzz");
            return "Buzz";
        } else {
            //System.out.print(i);
            return String.valueOf(number);//Integer.toString(i);
        }
    }

    public List<String> fizzBuzz_2(int n) {
        List<String> lst = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                lst.add("FizzBuzz");
            } else if (i % 3 == 0) {
                lst.add("Fizz");
            } else if (i % 5 == 0) {
                lst.add("Buzz");
            } else {
                lst.add(String.valueOf(i));
            }
        }
        return lst;
    }
}
