import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // TC: O(n*k) SC: O(n)
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<Double, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            double primeProduct = getPrimeProduct(str);
            if (!map.containsKey(primeProduct)) {
                map.put(primeProduct, new ArrayList<>());
            }
            map.get(primeProduct).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private double getPrimeProduct(String str) {
        int prime[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101, 103 };
        double result = 1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            result = result * prime[c - 'a'];
        }
        return result;
    }

    // TC: O(n) SC: O(k). where "k" is the number of unique character mappings
    // between s and t
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null)
            return false;
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> sMap = new HashMap<>();
        HashMap<Character, Character> tMap = new HashMap<>();

        for (int i = 0; 1 < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sMap.containsKey(sChar)) {
                if (sMap.get(sChar) != tChar)
                    return false;
            } else {
                sMap.put(sChar, tChar);
            }
            if (tMap.containsKey(tChar)) {
                if (tMap.get(tChar) != sChar)
                    return false;
            } else {
                tMap.put(tChar, sChar);
            }
        }
        return true;
    }

    // TC: O(n) SC: O(k). where "k" is the number of unique character mappings
    // between s and pattern
    public boolean wordPattern(String pattern, String s) {
        String[] sParts = s.split(" ");
        if (pattern.length() != sParts.length) {
            return false;
        }
        char[] charPattern = pattern.toCharArray();
        Map<Character, String> patternMap = new HashMap<>();
        Map<String, Character> stringMap = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            if (!patternMap.containsKey(charPattern[i])) {
                if (!stringMap.containsKey(sParts[i])) {
                    patternMap.put(charPattern[i], sParts[i]);
                    stringMap.put(sParts[i], charPattern[i]);
                } else {
                    return false;
                }
            } else {
                if (!stringMap.containsKey(sParts[i])) {
                    return false;
                } else {
                    if ((charPattern[i] != stringMap.get(sParts[i])) ||
                            (!sParts[i].equals(patternMap.get(charPattern[i])))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
