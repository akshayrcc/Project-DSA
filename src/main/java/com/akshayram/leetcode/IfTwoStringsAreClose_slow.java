package com.akshayram.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class IfTwoStringsAreClose_slow {

	public static void main(String[] args) {
		
		//IfTwoStringsAreClose I = new IfTwoStringsAreClose();
		
		System.out.println(closeStrings("cabbba","aabbss"));
	}

	public static boolean closeStrings(String word1, String word2) {

		if (word1.length() != word2.length()) {
			return false;
		}

		HashMap map1 = characterCount(word1);
		HashMap map2 = characterCount(word2);

		if (!map1.keySet().equals(map2.keySet())) {
			return false;
		}

		ArrayList<String> listA = new ArrayList<String>(map1.values());
		ArrayList<String> listB = new ArrayList<String>(map2.values());
		Collections.sort(listA);
		Collections.sort(listB);
		if (!listA.equals(listB)) {
			return false;
		}
		return true;
	}

	private static HashMap characterCount(String inputString) {
		// Creating a HashMap containing char as a key and occurrences as a value
		HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

		// Converting given string to char array
		char[] strArray = inputString.toCharArray();

		// checking each char of strArray
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {
				// If char 'c' is present in charCountMap, incrementing it's count by 1
				charCountMap.put(c, charCountMap.get(c) + 1);
			} else {
				// If char 'c' is not present in charCountMap,
				// putting 'c' into charCountMap with 1 as it's value
				charCountMap.put(c, 1);
			}
		}
		return charCountMap;
	}
	/*
	 * Word Frequency Logic:
	 * 
	  	int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for(char c: word1.toCharArray())
            freq1[c-'a'] ++ ;
        for(char c: word2.toCharArray())
            freq2[c-'a'] ++ ;
	*/
}
