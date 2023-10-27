package com.akshayram.oa;

import java.util.*;

public class SubEncryptedString {
    public static String getSubEncryptedString(int messageCount, String[] messages) {
        int k = messages[0].length();
        int minError = Integer.MAX_VALUE;
        String minErrorString = "";

        for (int i = 0; i < Math.pow(26, k); i++) {
            StringBuilder potentialSubEncrypted = new StringBuilder();
            int temp = i;
            for (int j = 0; j < k; j++) {
                char ch = (char) ('a' + temp % 26);
                potentialSubEncrypted.insert(0, ch);
                temp /= 26;
            }

            int totalError = 0;
            for (String message : messages) {
                for (int l = 0; l < k; l++) {
                    if (l < message.length()) { // Check bounds before accessing the character
                        totalError += Math.abs(message.charAt(l) - potentialSubEncrypted.charAt(l));
                    }
                }
            }

            if (totalError < minError) {
                minError = totalError;
                minErrorString = potentialSubEncrypted.toString();
            }
        }

        return minErrorString;
    }


    String generatePalindrome(List<String> inputStrings, int length) {
        StringBuilder palindrome = new StringBuilder();

        // Fill first half
        for (int i = 0; i < length/2; i++) {
            int[] counts = new int[26];
            // Calculate character frequencies at this position
            for (String s : inputStrings) {
                counts[s.charAt(i) - 'a']++;
            }

            int medianIndex = -1;
            int medianCount = 0;

            // Choose median character with highest frequency
            for (int j = 0; j < 26; j++) {
                medianCount += counts[j];
                if (medianCount > inputStrings.size()/2) {
                    medianIndex = j;
                    break;
                }
            }

            // Break ties lexicographically
            if (medianCount == inputStrings.size()/2) {
                for (int j = medianIndex; j >= 0; j--) {
                    if (counts[j] > 0) {
                        medianIndex = j;
                        break;
                    }
                }
            }

            palindrome.append((char) ('a' + medianIndex));
        }

        // Fill second half by reversing first half
        palindrome.append(palindrome.reverse());
        return palindrome.toString();
    }

    public static void main(String[] args) {
        int messageCount = 3;
        //String[] messages = {"aba", "bd"};
        //String[] messages = {"aba", "cbd"};
        //String[] messages = {"abc", "cdf"};
        String[] messages = {"aa", "yy", "mm"};
        String result = getSubEncryptedString(messageCount, messages);
        System.out.println(result);  // Output: "aba"
    }
}
