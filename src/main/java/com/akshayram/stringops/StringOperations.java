package com.akshayram.stringops;

import java.util.*;

public class StringOperations {

    public static void main(String[] args) {
        System.out.println("Hello String World");

        /*Reversing an array*/
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
        //reverseString(s);
        System.out.println(s[0]);
        System.out.println(Arrays.toString(s));

        String firstUnique = "aadadaad";
        String firstUnique2 = "loveleetcode";
//        System.out.println("First Unique: " + firstUniqChar_1(firstUnique2));

        System.out.println("Haystack test: " + strStr2("sadbutsad", "sad"));

    }

    public static void reverseString(char[] s) {
        int length = s.length;
        int ptr_1 = 0, ptr_2 = length - 1;

        while (ptr_1 < ptr_2) {
            swap(s, ptr_1, ptr_2);
            ptr_1++;
            ptr_2--;
        }
    }

    public static void swap(char[] arr, int p1, int p2) {
        char temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }

    public static int firstUniqChar_1(String s) {
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();
        Set<Character> alreadyDuplicate = new HashSet<>();
        int position = 0;
        int firstUniqueIndex = -1;
        for (Character ch : s.toCharArray()) {
            if (linkedHashMap.containsKey(ch)) {
                linkedHashMap.remove(ch);
                alreadyDuplicate.add(ch
                );
            } else {
                if (!alreadyDuplicate.contains(ch)) {
                    linkedHashMap.put(ch, position);
                }
            }
            position++;
        }
        if (linkedHashMap.size() > 0) {
            Map.Entry<Character, Integer> entry = linkedHashMap.entrySet().iterator().next();
            firstUniqueIndex = entry.getValue();
        }
        return firstUniqueIndex;
    }

    public int firstUniqChar_2(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int ind = s.indexOf(i);
            //checking given char occurrence is first and last in the string implies it's a unique char
            if (ind != -1 && ind == s.lastIndexOf(i))
                ans = Math.min(ans, ind);
        }
        if (ans == Integer.MAX_VALUE)
            return -1;
        return ans;
    }

    public int firstUniqChar_3(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        //count the occurrences of all the chars in the string and return the first unique char.
        //Sol_1
        /*int[] charCount = new int[256];
        for (char c : s.toCharArray()) {
            charCount[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (charCount[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;*/

        //Sol_2
        int a[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (a[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        int i = 0;
        while (true) {
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j - 1].length() <= i
                        || strs[j].charAt(i) != strs[j - 1].charAt(i)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                i++;
            } else {
                break;
            }
        }

        return strs[0].substring(0, i);
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return -1;

        if (needle.length() == 0)
            return -1;

        for (int i = 0; i < haystack.length(); i++) {
            if (i + needle.length() > haystack.length())
                return -1;
            int m = i;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) == haystack.charAt(m)) {
                    if (j == needle.length() - 1)
                        return i;
                    m++;
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    public static int strStr2(String haystack, String needle) {
        int retValue = -1;
        int needleLen = needle.length();
        int haystackLen = haystack.length();
        for (int i = 0; i < haystackLen; i++) {
            if (retValue != -1) {
                return i;
            }
            if (haystackLen - i > 0 && (haystackLen - i >= needle.length())) {
                System.out.println(haystack.substring(i, i + needleLen));
                if (haystack.substring(i, i + needleLen).equals(needle)) {
                    retValue = i;
                    return retValue;
                }
            }
        }
        return retValue;
    }

    public static int strStr3(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    //TC: O(n) SC:O(n)
    public boolean checkValidString(String s) {
        int N = s.length();
        Deque<Integer> openBracketsStack = new ArrayDeque<>();
        Deque<Integer> asterisksStack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openBracketsStack.push(i);
            } else if (ch == '*') {
                asterisksStack.push(i);
            } else {
                if (!openBracketsStack.isEmpty()) {
                    openBracketsStack.pop();
                } else if (!asterisksStack.isEmpty()) {
                    asterisksStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!openBracketsStack.isEmpty() && !asterisksStack.isEmpty()) {
            if (openBracketsStack.pop() > asterisksStack.pop()) {
                return false;
            }
        }
        return openBracketsStack.isEmpty();
    }

}
