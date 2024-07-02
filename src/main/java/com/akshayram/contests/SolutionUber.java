package com.akshayram.contests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionUber {

//    public static void main(String[] args) {
//
//        int[] numbers1 = {1, 23, 156, 1650, 651, 165, 32};
//        System.out.println("Test case 1: Expected: 3, Actual: " + solution(numbers1));
//        // Test case 1: The one you provided
//
//
////        int[] numbers1 = {278, 872, 827, 253, 221, 523, 526, 523, 221, 296};
////        System.out.println("Test case 1: Expected: 6, Actual: " + solution(numbers1));
////
////        // Test case 2: Simple case with one pair
////        int[] numbers2 = {123, 321};
////        System.out.println("Test case 2: Expected: 1, Actual: " + solution(numbers2));
////
////        // Test case 3: No pairs
////        int[] numbers3 = {100, 200, 300};
////        System.out.println("Test case 3: Expected: 0, Actual: " + solution(numbers3));
////
////        // Test case 4: Multiple pairs
////        int[] numbers4 = {112, 121, 211, 221};
////        System.out.println("Test case 4: Expected: 3, Actual: " + solution(numbers4));
////
////        // Test case 5: Identical numbers (should not count)
////        int[] numbers5 = {111, 111, 111};
////        System.out.println("Test case 5: Expected: 0, Actual: " + solution(numbers5));
//
//    }
    static int solution(int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (canBeObtainedBySwapping(numbers[i], numbers[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean canBeObtainedBySwapping(int a, int b) {
        if (a == b) return false;  // Changed this line

        String strA = String.valueOf(a);
        String strB = String.valueOf(b);

        if (strA.length() != strB.length()) return false;

        int diffCount = 0;
        int[] diffIndices = new int[2];

        for (int i = 0; i < strA.length(); i++) {
            if (strA.charAt(i) != strB.charAt(i)) {
                if (diffCount >= 2) return false;
                diffIndices[diffCount++] = i;
            }
        }

        return diffCount == 2 &&
                strA.charAt(diffIndices[0]) == strB.charAt(diffIndices[1]) &&
                strA.charAt(diffIndices[1]) == strB.charAt(diffIndices[0]);
    }

    public static List<int[]> findSwappablePairs(int[] arr) {
        int n = arr.length;
        List<int[]> uniquePairs = new ArrayList<>();
        Set<String> seenSwaps = new HashSet<>(); // Track seen swaps to avoid duplicates

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                String str1 = String.valueOf(arr[i]);
                String str2 = String.valueOf(arr[j]);

                if (str1.length() != str2.length()) {
                    continue; // Skip pairs with different lengths
                }

                char[] charArr1 = str1.toCharArray();
                char[] charArr2 = str2.toCharArray();

                int diffCount = 0;
                for (int k = 0; k < str1.length(); k++) {
                    if (charArr1[k] != charArr2[k]) {
                        diffCount++;
                        if (diffCount > 2) {
                            break; // More than two digits differ, move on
                        }
                    }
                }

                // Check if exactly two digits differ and avoid duplicates
                if (diffCount == 2 && !seenSwaps.contains(str1 + "-" + str2)) {
                    uniquePairs.add(new int[]{arr[i], arr[j]});
                    seenSwaps.add(str1 + "-" + str2);  // Add sorted order for uniqueness
                    seenSwaps.add(str2 + "-" + str1);  // (a, b) and (b, a) are the same swap
                }
            }
        }

        return uniquePairs;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 23, 156, 1650, 651, 165, 32};
        List<int[]> pairs = findSwappablePairs(numbers);

        System.out.println("Swappable pairs:");
        for (int[] pair : pairs) {
            System.out.println(pair[0] + ", " + pair[1]);
        }
    }

}
