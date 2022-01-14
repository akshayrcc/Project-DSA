package com.cybertron.leetcode;

public class ReverseInteger {
    public int reverse_1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public int reverse_2(int x) {
        boolean negative = x < 0;
        if (negative) {
            x *= -1;
        }
        int r = 0;
        while (x != 0) {
            int mod = x % 10;
            if (r > Integer.MAX_VALUE / 10) {
                return 0;
            }
            r = (r * 10) + mod;
            x = x / 10;
            if (r < 0) {
                return 0;

            }
        }
        System.out.println(r);

        if (negative) {
            r *= -1;
            if (r > 0) {
                return 0;
            }
        }
        return r;
    }
}
