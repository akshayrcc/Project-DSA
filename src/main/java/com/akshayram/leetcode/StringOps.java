package com.akshayram.leetcode;

import java.util.HashSet;
import javafx.util.Pair;
import java.util.Set;

public class StringOps {

  // TC: O(n). SC: O(n)
  public static boolean isPathCrossing(String path) {
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    int x = 0;
    int y = 0;
    visited.add(new Pair<>(x, y));
    for (char c : path.toCharArray()) {
      if (c == 'N') {
        y++;
      } else if (c == 'S') {
        y--;
      } else if (c == 'E') {
        x++;
      } else if (c == 'W') {
        x--;
      }
      Pair<Integer, Integer> coordinates = new Pair<>(x, y);
      if (visited.contains(coordinates)) {
        return true;
      } else {
        visited.add(new Pair<>(x, y));
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println("MAIN EXEC...");
    String input = "NESWW"; // "NES";
    System.out.println("For input " + input + " " + isPathCrossing(input));
  }
}
