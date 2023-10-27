package com.akshayram.leetcode;

import java.util.HashSet;

public class ValidSudoku {

    public boolean isValidSudoku_1(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;
        // check each column
        for (int i = 0; i < 9; i++) {
            boolean[] m = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (m[(board[i][j] - '1')]) {
                        return false;
                    }
                    m[(board[i][j] - '1')] = true;
                }
            }
        }

        //check each row
        for (int j = 0; j < 9; j++) {
            boolean[] m = new boolean[9];
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.') {
                    if (m[(board[i][j] - '1')]) {
                        return false;
                    }
                    m[(board[i][j] - '1')] = true;
                }
            }
        }

        //check each 3*3 matrix
        for (int block = 0; block < 9; block++) {
            boolean[] m = new boolean[9];
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    if (board[i][j] != '.') {
                        if (m[(board[i][j] - '1')]) {
                            return false;
                        }
                        m[(board[i][j] - '1')] = true;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku_2(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;

        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current_val = board[i][j];
                if (current_val != '.') {
                    if (!seen.add(current_val + " found in row " + i) ||
                            !seen.add(current_val + " found in column " + j) ||
                            !seen.add(current_val + " found in sub box " + i / 3 + "-" + j / 3)
                    ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
