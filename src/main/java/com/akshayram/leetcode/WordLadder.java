package com.akshayram.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.akshayram.missing.PairCustom;
//import javafx.util.Pair;
//import com.akshayram.plaindatastructures.Pair;
//import com.sun.tools.javac.util.Pair;

public class WordLadder {

	public static void main(String[] args) {
		String[] s1 = new String[] { "hot", "dot", "dog", "lot", "log" };
		List<String> L1 = new ArrayList<String>(Arrays.asList(s1));
		String start = "hit";
		String target = "cog";
		System.out.print("Length of shortest chain is: " + ladderLength(start, target, L1));
		
	}

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

		// Since all words are of same length.
		int L = beginWord.length();

		// Dictionary to hold combination of words that can be formed,
		// from any given word. By changing one letter at a time.
		Map<String, List<String>> allComboDict = new HashMap<>();

		wordList.forEach(word -> {
			for (int i = 0; i < L; i++) {
				// Key is the generic word
				// Value is a list of words which have the same intermediate generic word.
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
				List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
				transformations.add(word);
				allComboDict.put(newWord, transformations);
			}
		});

		// Queue for BFS
		Queue<PairCustom<String, Integer>> Q = new LinkedList<>();
		Q.add(new PairCustom(beginWord, 1));

		// Visited to make sure we don't repeat processing same word.
		Map<String, Boolean> visited = new HashMap<>();
		visited.put(beginWord, true);

		while (!Q.isEmpty()) {
			PairCustom<String, Integer> node = Q.remove();
			String word = node.getKey();
			int level = node.getValue();
			for (int i = 0; i < L; i++) {

				// Intermediate words for current word
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

				// Next states are all the words which share the same intermediate state.
				for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
					// If at any point if we find what we are looking for
					// i.e. the end word - we can return with the answer.
					if (adjacentWord.equals(endWord)) {
						return level + 1;
					}
					// Otherwise, add it to the BFS Queue. Also mark it visited
					if (!visited.containsKey(adjacentWord)) {
						visited.put(adjacentWord, true);
						Q.add(new PairCustom(adjacentWord, level + 1));
					}
				}
			}
		}

		return 0;
	}

//	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
//		Set<String> wordSet = new HashSet<String>(wordList);
//
//		if (!wordSet.contains(endWord)) {
//			return 0;
//		}
//
//		int level = 0, wordlength = beginWord.length();
//
//		Queue<String> Q = new LinkedList<>();
//		Q.add(beginWord);
//
//		while (!Q.isEmpty()) {
//			++level;
//			int sizeofQ = Q.size();
//			for (int i = 0; i < sizeofQ; ++i) {
//				char[] word = Q.peek().toCharArray();
//				Q.remove();
//
//				for (int pos = 0; pos < wordlength; ++pos) {
//					char orig_char = word[pos];
//					for (char c = 'a'; c <= 'z'; ++c) {
//						word[pos] = c;
//						if (String.valueOf(word).equals(endWord)) {
//							return level + 1;
//						}
//
//						if (!wordSet.contains(String.valueOf(word))) {
//							continue;
//						}
//
//						wordSet.remove(String.valueOf(word));
//						Q.add(String.valueOf(word));
//					}
//					word[pos] = orig_char;
//				}
//			}
//		}
//		return 0;
//	}

}
