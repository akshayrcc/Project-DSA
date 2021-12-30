package com.cybertron.leetcode;

import java.util.Arrays;

public class PlusOne {

    public static int[] plusOne(int[] digits) {
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
    }

    public static void main(String[] args) {
        //int[] intArr = new int[]{0, 0, 0, 0};
        int[] intArr = new int[]{9,8,7,6,5,4,3,2,1,0};
        int[] intAnswer = plusOne(intArr);

        System.out.println(" " + Arrays.toString(intAnswer));
    }

}
