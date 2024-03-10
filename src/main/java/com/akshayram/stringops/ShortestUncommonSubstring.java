package com.akshayram.stringops;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestUncommonSubstring {

    public static void main(String[] args) {
        String[] arr = new String[]{"hdrasjp", "mtkqfukubx", "zffbigargurdcg", "fgkxqwthvna", "eeiqtcvnkpjfuk", "lylydjhmqqlhhlhh", "tnlwmfwafqywkqkf", "lnbo", "fcjqnigcycelssconoum", "cqqtf", "gyygydmlljaxc", "l", "iyvtimf", "zzmoagybh", "wsgguexqcpifoujpbn", "nocglmnmgidaxmjdi", "ubaacl", "ytafnbwnxvr", "zgggvipvhdi", "aefrl", "iwqgc", "picpvgylwlogtavrz", "wdphntsgonsg", "usw", "pzwi", "gvqbzicrph", "nrbtusdjeudinzmkfocw", "dnximo", "ltouxgjlahodcyaacdrx", "lpqrodzsypnwdfeg", "fgudtvemqazdcum", "wmusjqtlywocsdq", "giqdacefjrig", "uyupew", "lkzjbmlmqerlaqzu", "dsrzxacjqsgt", "fapaceyqjnrddqvde", "qxxvxiuhwxsqqusvfgpt", "phhqoczpktrilgaajn", "nqdphsvgf", "xnzzipxplqidq", "yyzudhdugvweqs", "iwwqbijevbwtiuqpz", "srbkseuaewrjmsovuof"};
        System.out.println("Started");
        System.out.println("Result is: " + Arrays.toString(shortestSubstrings(arr)));
    }

    public static <T> Set<T> mergeSet(Set<T> a, Set<T> b) {
        // Creating a Set with 'a'
        Set<T> mergedSet = new HashSet<>(a);
        // Adding the second set to be merged
        mergedSet.addAll(b);
        // Returning the merged Set
        return mergedSet;
    }

    static List<Set<String>> allList; // N set elements
    static Set<String> allSet = new HashSet<>();

    public static boolean checkIfStrPresent1(int skipIndex, String subStrToCheck, int N) {
        Set<String> finalSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (i == skipIndex) continue;
            Set<String> currSet = allList.get(i);
            finalSet = mergeSet(finalSet, currSet);
        }
        if (finalSet.contains(subStrToCheck)) return true;
        return false;
    }

    public static boolean checkIfStrPresent(int skipIndex, String subStrToCheck, int N) {
        // Use stream operations for efficient filtering and combining
        return allList.stream().filter(set -> set != allList.get(skipIndex)).flatMap(Set::stream) // Flatten sets into a single stream
                .anyMatch(subStr -> subStr.equals(subStrToCheck));
    }

    public static boolean checkIfStrPresent2(int skipIndex, String subStrToCheck, int N) {
        int minLength = Math.max(2, subStrToCheck.length() - 1); // Consider minimum length (adjust as needed)
        // Use stream operations for efficient filtering and combining
        return allList.stream().filter(set -> set != allList.get(skipIndex)).flatMap(Set::stream).filter(subStr -> subStr.length() >= minLength) // Pre-filter based on minimum length
                .anyMatch(subStr -> subStr.equals(subStrToCheck));
    }


    public static String[] shortestSubstrings(String[] arr) {
        int N = arr.length;
        String[] answers = new String[N];
        // Set<String> allSubstrings = new HashSet<>();

        allList = new ArrayList<>(); // N set elements

        // Collect all unique substrings across all strings
        for (String str : arr) {
            Set<String> localSet = new HashSet<>();
            for (int i = 0; i < str.length(); i++) {
                for (int j = i + 1; j <= str.length(); j++) {
                    String sub = str.substring(i, j);
                    if (!allSet.contains(sub)) {
                        localSet.add(str.substring(i, j));
                        //allSet.add(str.substring(i, j));
                    }
                }
            }
            allList.add(localSet);
        }

        // Find shortest uncommon substring for each string
//         for (int i = 0; i < N; i++) {
//             String current = arr[i];
//             String shortest = "";

//             for (int j = 0; j < current.length(); j++) {
//                 for (int k = j + 1; k <= current.length(); k++) {
//                     String substring = current.substring(j, k);
//                     if (  !checkIfStrPresent(i,substring, N)  ) {
//                         // Found a unique substring or the entire string is unique
//                         if (shortest.isEmpty() || substring.length() < shortest.length() ||
//                                 (substring.length() == shortest.length() && substring.compareTo(shortest) < 0)) {
//                             shortest = substring;
//                         }
//                     }
//                 }
//             }
//             answers[i] = shortest;
//         }
//         return answers;

        for (int i = 0; i < N; i++) {
            String current = arr[i];
            String shortest = "";
            Set<String> currSet = allList.get(i);

            for (String substring : currSet) {
                if (!checkIfStrPresent(i, substring, N)) {
                    if (shortest.isEmpty() || substring.length() < shortest.length() || (substring.length() == shortest.length() && substring.compareTo(shortest) < 0)) {
                        shortest = substring;
                    }
                }
            }
            answers[i] = shortest;
        }

        return answers;
    }
}
