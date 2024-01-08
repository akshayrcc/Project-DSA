package com.akshayram.hackerrank;

import java.io.*;
import java.util.*;

public class Solution {

  // Complete the bigSorting function below.
  static String[] bigSorting(String[] unsorted) {

    Map<String, Integer> freqMap = new HashMap<>();
    Map<String, Integer> relationMap = new HashMap<>();

    for (int i = 0; i < unsorted.length; i++) {
      if (freqMap.containsKey(unsorted[i])) {
        freqMap.put(unsorted[i], freqMap.get(unsorted[i]) + 1);
      } else {
        freqMap.put(unsorted[i], 1);
      }
    }
    //shorthand for freqMap
    //freqMap.put(unsorted[i], freqMap.getOrDefault(num, 0) + 1);

    List<Map.Entry<String, Integer>> list = new LinkedList<>(freqMap.entrySet());

    Collections.sort(
        list,
        new Comparator<Map.Entry<String, Integer>>() {
          public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
            String k = m1.getKey() + " " + m2.getKey();
            if (relationMap.containsKey(k)) {
              return relationMap.get(k);
            }

            int result = 0;

            if (m1.getKey().length() < m2.getKey().length()) {
              result = -1;
            } else {
              int index1 = 0, index2 = 0;
              while (index1 < m1.getKey().length()) {
                int d1 = (int) (m1.getKey().charAt(index1) - '0');
                int d2 = (int) (m2.getKey().charAt(index2) - '0');

                if (d1 > d2) {
                  result = 1;
                  break;
                }
                if (d1 < d2) {
                  result = -1;
                  break;
                }
                index1++;
                index2++;
                result = 0;
              }
            }
            relationMap.put(k, result);
            return result;
          }
        });

    int index = 0;
    for (Map.Entry<String, Integer> entry : list) {
      if (entry.getValue() > 1) {
        for (int i = 0; i < entry.getValue(); i++) {
          unsorted[index++] = entry.getKey();
        }
      } else {
        unsorted[index++] = entry.getKey();
      }
    }
    return unsorted;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    String[] unsorted = new String[n];

    for (int i = 0; i < n; i++) {
      String unsortedItem = scanner.nextLine();
      unsorted[i] = unsortedItem;
    }

    String[] result = bigSorting(unsorted);

    for (int i = 0; i < result.length; i++) {
      bufferedWriter.write(result[i]);

      if (i != result.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
