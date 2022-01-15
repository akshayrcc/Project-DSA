package com.cybertron.stringops;

public class StringtoInteger {


    private static int myAtoi(String str) {
        // Base condition
        if (str == null || str.length() < 1) {
            return 0;
        }
        // MAX and MIN values for integers
        final int INT_MAX = 2147483647;
        final int INT_MIN = -2147483648;
        // Trimmed string
        str = str.replaceAll("^\\s+", "");
        // Counter
        int i = 0;
        // Flag to indicate if the number is negative
        boolean isNegative = str.startsWith("-");
        // Flag to indicate if the number is positive
        boolean isPositive = str.startsWith("+");
        if (isNegative) {
            i++;
        } else if (isPositive) {
            i++;
        }
        // This will store the converted number
        double number = 0;
        // Loop for each numeric character in the string iff numeric characters are leading
        // characters in the string
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            number = number * 10 + (str.charAt(i) - '0');
            i++;
        }
        // Give back the sign to the converted number
        number = isNegative ? -number : number;

        if (number < INT_MIN) {
            return INT_MIN;
        }
        if (number > INT_MAX) {
            return INT_MAX;
        }
        return (int) number;
    }


    public int myAtoi_2(String s) {
        Boolean negative = null;
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                if (negative != null) {
                    break;
                }
                negative = Boolean.TRUE;
            } else if (c == '+') {
                if (negative != null) {
                    break;
                }
                negative = Boolean.FALSE;
            } else if (c == ' ') {
                if (negative != null) {
                    break;
                }
            } else if (c >= '0' && c <= '9') {
                if (negative == null) {
                    negative = Boolean.FALSE;
                }
                int num = c - '0';
                if (result >= Long.MAX_VALUE / 10) {
                    result = Long.MAX_VALUE;
                } else {
                    result = result * 10 + num;
                }

                //System.out.println("result:" + result);
            } else {
                break;
            }
        }

        if (negative == null) {
            return 0;
        }
        if (negative == Boolean.TRUE) {
            return -result < Integer.MIN_VALUE ? Integer.MIN_VALUE : -(int) result;
        }
        return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
    }
}
