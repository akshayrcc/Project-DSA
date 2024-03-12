package com.akshayram.stringops;

import java.util.HashMap;

public class CustomSortString {

    // TC : O(n) SC : O(1)
    public String customSortString1(String order, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder result = new StringBuilder();
        // Frequency Map
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        // check which chars are in order and add to sb
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            if (map.containsKey(c)) {
                int count = map.get(c);
                for (int j = 0; j < count; j++) {
                    result.append(c);
                }
                map.remove(c);
            }
        }
        // add all the leftover chars that were not in order string
        for (Character c : map.keySet()) {
            int count = map.get(c);
            for (int j = 0; j < count; j++) {
                result.append(c);
            }
        }
        return result.toString();
    }
}
