package com.akshayram.stringops;

import java.util.*;

public class GroupAnagrams {


    //TC: O(nklogk) SC:O(n)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hmap = new HashMap<>();
        for (String st : strs) {
            char[] ch_array = st.toCharArray();
            Arrays.sort(ch_array);
            String sorted_st = String.valueOf(ch_array);
            if (!hmap.containsKey(sorted_st)) {
                hmap.put(sorted_st, new ArrayList<>());
            }
            hmap.get(sorted_st).add(st);
        }
        return new ArrayList<>(hmap.values());
    }


    //TC: O(nk) SC:O(n)
    private static final int[] primeArray = new int[27];

    public List<List<String>> groupAnagrams2(String[] strs) {
        int k = 0;
        for (int j = 2; j < 200 && k < 27; j++) {
            if (isPrime(j)) {
                primeArray[k] = j;
                k++;
            }
        }
        Map<Double, List<String>> hmap = new HashMap<>();
        for (String st : strs) {
            double primeProd = getPrimeProductOfString(st);
            if (!hmap.containsKey(primeProd)) {
                hmap.put(primeProd, new ArrayList<>());
            }
            hmap.get(primeProd).add(st);
        }
        return new ArrayList<>(hmap.values());
    }

    private static double getPrimeProductOfString(String str) {
        double result = 1d;
        for (int i = 0; i < str.length(); i++) {
            result *= primeArray[str.charAt(i) - 'a'];
        }
        return result;
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


}
