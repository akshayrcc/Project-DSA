package com.akshayram.leetcode;

import java.util.*;

class ArrayOps {
  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return intersect(nums2, nums1);
    }
    Map<Integer, Integer> m = new HashMap<>();
    for (int val : nums1) {
      m.put(val, m.getOrDefault(val, 0) + 1);
    }
    int k = 0;
    for (int val : nums2) {
      if (m.getOrDefault(val, 0) > 0) {
        nums1[k++] = val;
        m.put(val, m.get(val) - 1);
      }
    }
    int[] ans = new int[k];
    ans = Arrays.copyOf(nums1, k);
    return ans;
  }

  public int[] intersect_2(int[] nums1, int[] nums2) {
    int[] freq = new int[1001];
    // Setting the freq of each number in nums1
    for (int i : nums1) {
      freq[i]++;
    }
    int[] res = new int[1001];
    int k = 0;
    for (int j : nums2) {
      if (freq[j] > 0) {
        freq[j]--;
        res[k++] = j;
      }
    }
    return Arrays.copyOfRange(res, 0, k);
  }

  public void rotate(int[] nums, int k) {
    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  public void reverse(int[] arr, int start, int end) {
    while (start < end) {
      int temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
  }

  // compare if two string arrays are same string pieces
  // TC: O(n * k) SC: O(1)
  public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
    int len1 = word1.length;
    int len2 = word2.length;
    int i = 0, j = 0, char_i = 0, char_j = 0, w1count = 0, w2count = 0;
    String w1;
    String w2;
    while (i < len1 && j < len2) {
      w1 = word1[i];
      w2 = word2[j];

      if (char_i >= w1.length()) {
        char_i = 0;
        i++;
        continue;
      }
      if (char_j >= w2.length()) {
        char_j = 0;
        j++;
        continue;
      }

      char c1 = w1.charAt(char_i);
      char c2 = w2.charAt(char_j);

      char_i++;
      char_j++;

      w1count++;
      w2count++;

      if (c1 != c2) {
        return false;
      }
    }

    if (i < len1) {
      w1 = word1[i];
      if (char_i < w1.length()) w1count++;
    }

    if (j < len2) {
      w2 = word2[j];
      if (char_j < w2.length()) w2count++;
    }

    if (w1count != w2count) return false;
    return true;
  }

  public int countCharacters(String[] words, String chars) {
    int sum = 0;
    // create a freqMap for chars string
    //    Map<Character, Integer> freqMap = new HashMap<>();
    //    for (int i = 0; i < chars.length(); i++) {
    //      if (freqMap.containsKey(chars.charAt(i))) {
    //        freqMap.put(chars.charAt(i), freqMap.get(chars.charAt(i)) + 1);
    //      } else {
    //        freqMap.put(chars.charAt(i), 1);
    //      }
    //    }
    boolean goodWord = false;
    int[] freqArray = new int[26];
    for (char ch : chars.toCharArray()) {
      freqArray[ch - 'a']++;
    }

    for (String word : words) {
      goodWord = true;
      int[] wordFreqArray = new int[26];
      for (char ch1 : word.toCharArray()) {
        wordFreqArray[ch1 - 'a']++;
        if (wordFreqArray[ch1 - 'a'] > freqArray[ch1 - 'a']) {
          goodWord = false;
          break;
        }
      }
      if (goodWord) sum += word.length();
    }
    return sum;
  }

  // TC: O(n) SC:(1)
  public static String largestGoodInteger(String num) {
    char prev = ' ';
    int sameCount = 1;
    int maxGoodInt = -1;
    for (char ch : num.toCharArray()) {
      if (ch == prev) {
        sameCount++;
      } else {
        sameCount = 1;
      }
      if (sameCount == 3) {
        int val = Character.getNumericValue(ch);
        if (val > maxGoodInt) {
          maxGoodInt = val;
        }
        sameCount = 1;
      }
      prev = ch;
    }
    if (maxGoodInt != -1) {
      return maxGoodInt + "" + maxGoodInt + "" + maxGoodInt;
    }
    return "";
  }

  // TC: O(log n) SC: O(1)
  public static int numberOfMatches(int n) {
    int totalMatches = 0;
    while (n > 1) {
      if (n % 2 == 0) { // even teams
        totalMatches += (n / 2);
        n = n / 2;
      } else { // odd teams
        totalMatches += (n - 1) / 2;
        n = ((n - 1) / 2) + 1;
      }
    }
    return totalMatches;
  }

  // TC: O(n) SC: O(1)
  public static int totalMoney(int n) {
    // first full week = 28
    // every subsequent week adds 7 more.
    // Hence, nth full week = firstFullWeek + (n-1) * 7
    int fullWeeksCount = n / 7;
    int daysRemaining = n % 7;
    int money = 0;
    for (int i = 1; i <= fullWeeksCount; i++) {
      money += (28 + (i - 1) * 7);
    }
    int weekStartVal = fullWeeksCount + 1;
    for (int j = 0; j < daysRemaining; j++) {
      money += (weekStartVal + j);
    }
    return money;
  }

  //TC: O(n) SC: O(1)
  public static String largestOddNumber(String num) {
    String ans = "";
    int n = num.length() - 1;
    // skip all even nums from the last
    while (n >= 0 && Integer.parseInt(String.valueOf(num.charAt(n))) % 2 == 0) {
      n--;
    }
    // now n is point at odd num from the rear end
    if (n >= 0) {
      ans = num.substring(0, n+1);
    }
    return ans;
  }

  public static void main(String[] args) {
    //    String num = "014455"; // "42352338";//"2300019";//"6777133339";
    //    String result = largestGoodInteger(num);
    //    System.out.println("Largest Good Integer: " + result);
    String  result = largestOddNumber("52");
    System.out.println("Result: " + result);
  }
}
