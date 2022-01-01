package com.cybertron.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println("Enter Roman");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int num = romanToInt(str);
        System.out.println("Value is:" + num);
    }

    private static final Map<Character, Integer> roman = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /**
     * Constraint:
     * <p>
     * 1 <= s.length <= 15
     * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
     * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
     */
    public static int romanToInt(String s) {
        int intValue = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {

            // If present value is less than next value, subtract present from next value and add the
            // resultant to the sum variable.
            if (i != n - 1 && roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                intValue += roman.get(s.charAt(i + 1)) - roman.get(s.charAt(i));
                i++;
            } else {
                intValue += roman.get(s.charAt(i));
            }
        }
        return intValue;
    }

}
