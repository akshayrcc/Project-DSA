package com.akshayram.leetcode;

import java.util.HashSet;
import java.util.Set;

class BuddyStringsProgram {

    public static void main(String[] args) {
        String s = "abab";
        String goal = "acaa";
        System.out.println("Ans: " + buddyStrings(s, goal));
    }

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int n = s.length(), castAscii_1, castAscii_2, sum1 = 0, sum2 = 0, misMatchCount = 0;
        boolean isAllStringCharsSameForS = true;
        char ch1, ch2;
        Set<Character> st1 = new HashSet<>();
        Set<Character> st2 = new HashSet<>();
        int[] arr = new int[26];

        for (int i = 0; i < n; i++) {
            ch1 = s.charAt(i); //a =97
            ch2 = goal.charAt(i);
            castAscii_1 = (int) ch1;
            castAscii_2 = (int) ch2;
            sum1 += castAscii_1;
            sum2 += castAscii_2;
            if (ch1 != ch2) {
                misMatchCount++;
            }
            if (s.charAt(0) != ch1) {
                isAllStringCharsSameForS = false;
            }
            st1.add(ch1);
            st2.add(ch2);

        }

        if (sum1 == sum2 && st1.size() == st2.size() && st1.equals(st2)) { //solution possible
            if (misMatchCount == 2) {
                return true;
            }
            if (misMatchCount == 0) { //strings are identical
                if (isAllStringCharsSameForS && n >= 2) {
                    return true;
                } else if (st1.size() > 1 && n > 2 && st1.size() != n) {
                    return true;
                }
            }
        }

        return false;
    }
}
