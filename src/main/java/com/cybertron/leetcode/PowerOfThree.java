package com.cybertron.leetcode;

import java.util.HashMap;
import java.util.Map;

public class PowerOfThree {
    public static boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        if (n == 1)
            return true;
        if (n % 3 == 0)
            return isPowerOfThree(n / 3);
        return false;
    }

    public boolean isPowerOfThree_2(int n) {
        if (n <= 0)
            return false;
        // The maximum power of 3 value that integer can hold is 1162261467 ( 3^19 ) .
        return 1162261467 % n == 0;
    }

    public static boolean isPowerOfThree_3(int n) {
        if (n == 1) return true;
        int lastDigit = n % 10;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(3, 1);
        map.put(9, 2);
        map.put(7, 3);
        map.put(1, 4);

        if (map.get(lastDigit) == null) return false;

        int power = map.get(lastDigit);
        double powerOfThree = Math.pow(3, power);
        while (powerOfThree <= n) {
            if (powerOfThree == n)
                return true;
            power = power + 4;
            powerOfThree = Math.pow(3, power);
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 81;
        System.out.println(isPowerOfThree_3(n));
        n = 91;
        System.out.println(isPowerOfThree_3(n));
    }
}
