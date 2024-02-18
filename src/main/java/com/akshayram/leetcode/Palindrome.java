package com.akshayram.leetcode;

public class Palindrome {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //System.out.println("Ans " + isPalindrome(-1234));
        System.out.println("Ans " + isPalindrome(12321));
        System.out.println("Ans " + isValidPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("Ans " + isValidPalindrome("race a car"));
        System.out.println("Ans " + isValidPalindrome(" "));
        System.out.println("Ans " + isValidPalindrome("0P"));


    }


    public String firstPalindrome(String[] words) {
        for (String w : words) {
            if (isPalindrome(w)) {
                return w;
            }
        }
        return "";
    }

    private static boolean isPalindrome(String s) {
        final char[] A = s.toCharArray();
        final int N = A.length;
        for (int i = 0, j = N - 1; i < j; ++i, --j) {
            if (A[i] != A[j]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        final int N = str.length();
        for (int i = 0; i < N / 2 + 1; i++) {
            if (str.charAt(i) != str.charAt(N - 1 - i)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isValidPalindrome(String str) {
        String strLower = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        char ch_a = 'a';
        char ch_z = 'z';
        char ch_0 = '0';
        char ch_9 = '9';
        int val_a = (int) ch_a;
        int val_z = (int) ch_z;
        int val_0 = (int) ch_0;
        int val_9 = (int) ch_9;
        for (char ch : strLower.toCharArray()) {
            int val_ch = (int) ch;
            if (val_a <= val_ch && val_ch <= val_z) {
                //if given char is alpha, then consider it
                sb.append(ch);
            } else if (val_0 <= val_ch && val_ch <= val_9) {
                //if given char is numeric, then consider it
                sb.append(ch);
            }
        }

        //check if palindrome
        String finalString = sb.toString();
        if (finalString.length() == 0) return true;
        System.out.println("Given considered string is " + finalString);
        int strLength = finalString.length();
        for (int i = 0; i < strLength / 2 + 1; i++) {
            if (finalString.charAt(i) != finalString.charAt(strLength - 1 - i)) {
                System.out.println("Here at i" + i);
                return false;
            }
        }
        return true;
    }

}
