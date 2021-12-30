package com.cybertron.leetcode;

import java.util.Arrays;

public class PlusOne {

    //For small Integers arrays
    /*public static int[] plusOne(int[] digits) {
        String str = Arrays.toString(digits).replaceAll("\\[|]|,|\\s", "");
        long ans = Long.parseLong(str);
        ++ans;
        String temp = Long.toString(ans);
        int[] ans2 = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
            ans2[i] = temp.charAt(i) - '0';
        }
        return ans2;
    }*/

    //For Large int arrays
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] dig_2 = new int[digits.length + 1];
        //for (int i = 0; i < dig_2.length; i++) dig_2[i] = 0;
        dig_2[0] = 1;
        return dig_2;
    }

    public static void main(String[] args) {
        int[] intArr = new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        //int[] intArr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] intAnswer = plusOne(intArr);
        System.out.println(" " + Arrays.toString(intAnswer));
    }

}
