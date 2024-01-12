package com.akshayram.leetcode;

import java.util.Stack;
import java.util.Arrays;

class MergeIntervals {
  public int[][] merge(int[][] intervals) {

    // Logic : start putting intervals to stack
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort based on first element
    Stack<int[]> stack = new Stack<>();
    // push first interval to stack
    stack.push(new int[] {intervals[0][0], intervals[0][1]});

    for (int i = 1; i < intervals.length; i++) {

      int[] x = stack.peek();
      // if top interval has end >= current interval start
      if (x[1] >= intervals[i][0]) {
        // pop that interval
        stack.pop();
        // merge it with current interval
        // end should be max between top and current interval
        stack.push(new int[] {x[0], Math.max(x[1], intervals[i][1])});
      } else {
        stack.push(new int[] {intervals[i][0], intervals[i][1]});
      }
    }

    return stack.toArray(new int[stack.size()][2]);
  }
}
