package com.akshayram.leetcode;

import java.util.*;


public class PrefixSuffixStrings {

    public static void main(String[] args) {

    }

    class Node {
        private HashMap<List<Character>, Node> map = new HashMap<>();
        private int count;
    }

    public int countPrefixSuffixPairs(String[] words) {
        Node root = new Node();
        int count = 0;
        for (String word : words) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                count += (node = node.map.computeIfAbsent(List.of(word.charAt(i), word.charAt(word.length() - i - 1)),
                        t -> new Node())).count;
            }
            node.count++;
        }
        return count;
    }


    public int countPrefixSuffixPairs2(String[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPrefixAndSuffix(words[i], words[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPrefixAndSuffix(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int index1 = str2.indexOf(str1);
        int lastIndex = str2.lastIndexOf(str1);
        if (index1 == -1) return false;
        if (lastIndex == -1) return false;
        if (index1 == 0 && lastIndex == (n2 - n1)) return true;
        return false;
    }

    //---------------------
    public int longestCommonPrefix2(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr1) {
            for (; i > 0; i /= 10) {
                set.add(i);
            }
        }
        int max = 0;
        for (int i : arr2) {
            for (; i > 0; i /= 10) {
                if (set.contains(i)) {
                    max = Math.max(max, ("" + i).length());
                }
            }
        }
        return max;
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        if (arr1.length > 1) Arrays.sort(arr1);
        if (arr2.length > 1) Arrays.sort(arr2);

        int n1 = arr1.length;
        int n2 = arr2.length;

        // System.out.println("n: " + n1 + " "+ n2);
        int maxLen = 0;
        int prev_num1 = 0;
        int prev_num2 = 0;

        Map<Integer, Integer> hmap = new HashMap<>();

        for (int i = n1 - 1; i >= 0; i--) {

            // System.out.println("prev n1:" + prev_num1);
            String num1 = String.valueOf(arr1[i]);
            // System.out.println("num1:" + num1.length() + " maxLen " + maxLen);

            //deduplicate
            if (arr1[i] == prev_num1) continue;

            prev_num2 = 0;

            // String num1 = String.valueOf(arr1[i]);
            // System.out.println("num1:" + num1.length() + " maxLen " + maxLen);

            if (num1.length() <= maxLen) continue;

            // System.out.println("here:" + arr2.length);

            for (int j = n2 - 1; j >= 0; j--) {

                // System.out.println("check1:" + arr1[i] + " " + arr2[j]);

                //deduplicate
                if (arr2[j] == prev_num2) continue;

                // if(String.valueOf(arr2[j]).length() <= maxLen)    continue;

                // System.out.println("check:" + arr1[i] + " " + arr2[j]);

                if (hmap.containsKey(arr1[i])) {
                    if (hmap.get(arr1[i]) == arr2[j]) continue;
                }

                int cnt = getPrefixCount(arr1[i], arr2[j]);
                maxLen = Math.max(maxLen, cnt);
                hmap.put(arr1[i], arr2[j]);

                prev_num2 = arr2[j];
            }

            prev_num1 = arr1[i];

        }
        return maxLen;
    }


    private int getPrefixCount(int num1, int num2) {
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);
        int n1 = str1.length();
        int n2 = str2.length();
        int cnt = 0;
        for (int i = 0; i < n1 && i < n2; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                // System.out.println("pre:" + str1.charAt(i) + " " + str2.charAt(i));
                cnt++;
            } else {
                break;
                //return cnt;
            }
        }
        // prefixSetstr1.substring(2,cnt)
        return cnt;
    }
}
