//TC:each row n,n-1,n-2,..options to place queen so O(n!)
//SC: O(n^2) space of boolean board

import java.util.*;

class NQueens {

    List<List<String>> result;

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        boolean[][] board = new boolean[n][n];
        backtrack(board, n, 0);
        return result;
    }

    private void backtrack(boolean[][] board, int n, int r) {
        // base
        if (r == n) {
            List<String> li = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (board[i][j]) {
                        str.append('Q');
                    } else {
                        str.append('.');
                    }
                }
                li.add(str.toString());
            }
            result.add(li);
            return;
        }

        // logic
        for (int c = 0; c < n; c++) {
            if (isSafe(board, r, c, n)) {
                // action
                board[r][c] = true;

                // rcurse
                backtrack(board, n, r + 1);

                // backtrack
                board[r][c] = false;

            }
        }
    }

    private boolean isSafe(boolean[][] board, int r, int c, int n) {
        for (int i = 0; i < r; i++) {
            if (board[i][c])
                return false;
        }
        // upper right
        int i = r;
        int j = c;
        while (i >= 0 && j > n) {
            if (board[i][r]) {
                return false;
            }
            i--;
            j++;
        }

        // upper left
        i = r;
        j = c;
        while (i >= 0 && j >= 0) {
            if (board[i][j]) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }

}

// TC : O(m*n*(3^L)) //SC = L..length of word for recursion stack
public class Solution {
    int m;
    int n;
    int[][] dirs;
    boolean flag;

    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        this.dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        this.flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                backtrack(board, word, i, j, 0); // call the backtrack function on all the elements
            }
        }
        return flag;
    }

    private void backtrack(char[][] board, String word, int r, int c, int idx) {
        // base case
        // if we are able to reach at the last index, it means we found the word. So set
        // the flag true and return
        if (idx == word.length()) {
            flag = true;
            return;
        }
        // check the border before moving ahead along with if the element is already
        // visited previously
        if (r < 0 || r == m || c < 0 || c == n || board[r][c] == '#')
            return;
        // logic
        // if we find the character match, then only we will proceed further
        if (board[r][c] == word.charAt(idx)) {
            // mutate the value to maintain the visited element
            board[r][c] = '#';
            // iterate in all direction for BFS and recurse
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                // recurse
                // pass new row and column value along with next index for next character
                backtrack(board, word, nr, nc, idx + 1);
            }
            // backtrack
            // update the mutated value
            board[r][c] = word.charAt(idx);
        }
    }

}