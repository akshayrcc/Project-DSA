package com.akshayram.s30.Competitive_Coding_5;

import java.util.HashMap;

class Comp_Coding_5 {
    int count = 0;

    // TC: O(valid permutations) // SC: O(visited nodes)
    public int countArrangement(int n) {
        boolean[] visited = new boolean[n + 1];
        backtrack(n, 1, visited);
        return count;
    }

    private void backtrack(int n, int pos, boolean[] visited) {
        if (pos > n)
            count++;
        for (int i = 1; i <= n; i++) {
            // action
            if (!visited[i] && (i % pos == 0 || pos % i == 0)) {
                visited[i] = true;
                // recure
                backtrack(n, pos + 1, visited);
                // backtracking
                visited[i] = false;
            }
        }
    }
}

class Logger {
    // TC: O(1) time for the lookup. // SC: O(M) size of incoming messages
    HashMap<String, Integer> hmap = new HashMap<String, Integer>();

    Logger() {
        this.hmap = new HashMap<String, Integer>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!hmap.containsKey(message)) {
            hmap.put(message, timestamp);
            return true;
        }
        Integer older = hmap.get(message);
        if (timestamp - older >= 10) {
            hmap.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }
}