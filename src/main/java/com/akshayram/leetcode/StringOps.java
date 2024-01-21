package com.akshayram.leetcode;

import java.util.HashSet;

import javafx.util.Pair;

import java.util.*;

public class StringOps {

    // TC: O(n). SC: O(n)
    public static boolean isPathCrossing(String path) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        int x = 0;
        int y = 0;
        visited.add(new Pair<>(x, y));
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                y++;
            } else if (c == 'S') {
                y--;
            } else if (c == 'E') {
                x++;
            } else if (c == 'W') {
                x--;
            }
            Pair<Integer, Integer> coordinates = new Pair<>(x, y);
            if (visited.contains(coordinates)) {
                return true;
            } else {
                visited.add(new Pair<>(x, y));
            }
        }
        return false;
    }

    public static int minimumPushes(String word) {
        int n = word.length();
        System.out.println("n :" + n);
        if (n <= 8) return n;

        int[] counts = new int[26];  // Count occurrences of each letter
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }

        Arrays.sort(counts);  // Sort counts in ascending order

        int pushes = 8;
        int position = 2;

        //0 to 7 indexes are the highest freq chars to be mapped on first clicks
        //checking 8 onwards to be mapped to 2nd and 3rd positions
        for (int i = 0; i <= 17; i++) {
            while(counts[i] == 0){
                i++;
            }
            if(i<=1){
                position = 4;
            } else if (i<=9) {
                position = 3;
            } else {
                position = 2;
            }
            pushes += position * counts[i];  // Calculate pushes for this key
        }

        return pushes;
    }

    public static void main(String[] args) {
        System.out.println("MAIN EXEC...");
//        String input = "NESWW"; // "NES";
//        System.out.println("For input " + input + " " + isPathCrossing(input));

        String input = "amrvxnhsewkoipjyuclgtdbfq";//"amrvxnhsewkoipjyuclgtdbfq"; // "NES";
        System.out.println("For input " + input + " " + minimumPushes(input));


    }
}
