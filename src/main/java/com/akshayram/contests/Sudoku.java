package com.akshayram.contests;

import java.util.*;

public class Sudoku {
    static boolean sudokuSolve(char[][] board) {
        int r = -1, c = -1;
        List<Character> candidates = null;
        List<Character> newCandidates = new LinkedList<>();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    newCandidates = possibleCandidates(board, row, col);
                    if (candidates == null || newCandidates.size() < candidates.size()) {
                        candidates = newCandidates;
                        r = row;
                        c = col;
                    }
                }
            }
        }
        if (candidates == null) return true;
        for (char val : candidates) {
            board[r][c] = val;
            if (sudokuSolve(board)) return true;
            board[r][c] = '.';
        }
        return false;
    }

    static List<Character> possibleCandidates(char[][] board, int row, int col) {
        List<Character> candidates = new LinkedList<>();
        boolean collision = false;
        for (char c = '1'; c <= '9'; c++) {
            collision = false;
            for (int i = 0; i < 9; i++) {
                if (board[i][col] == c || board[row][i] == c || board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == c) {
                    collision = true;
                    break;
                }
            }
            if (!collision) candidates.add(c);
        }
        return candidates;
    }


    static boolean sudokuSolve2(char[][] board) {
        return helper(board, 0);
    }

    static boolean helper(char[][] board, int s) {
        if (s == 81) return true;
        int i = s / 9;
        int j = s % 9;
        if (board[i][j] != '.') return helper(board, s + 1);
        char ch = '1';
        while (ch <= '9') {
            if (isValid(board, i, j, ch)) {
                board[i][j] = ch;
                if (helper(board, s + 1)) return true;
            }
            board[i][j] = '.';
            ch++;
        }
        return false;
    }


    //method to check if placing c at i,j is valid or not.
    static boolean isValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[k][j] == c || board[i][k] == c || board[3 * (i / 3) + k / 3][3 * (j / 3) + k % 3] == c) {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println(); // Add a newline after each row
            // Add grid lines after every 3 rows
            if (row % 3 == 2) {
                System.out.println("- - -+- - -+- - -");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("...start...");
        char[][] board = {
                {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
        };
        boolean solved = sudokuSolve2(board);
        System.out.println("solved: " + solved + " ");
        printBoard(board);
    }

}
