package com.akshayram.s30.DFS

import java.util.Stack;

class DecodeString {
    // TC: O(n) SC: O(n)
    public String decodeString(String s) {

        StringBuilder currStr = new StringBuilder();
        int currNum = 0;
        Stack<StringBuilder> StringStack = new Stack<>();
        Stack<Integer> NumStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character is a digit
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0'; // Here we are subtracting the number with its ascii value
            }

            // If the character is an opening bracket
            else if (c == '[') {
                // We push the elements from CurrNum and CurrString to their respective stacks
                NumStack.push(currNum);
                StringStack.push(currStr);

                // update the values
                currStr = new StringBuilder();
                currNum = 0;
            }

            // If the character is a closing bracket
            else if (c == ']') {
                // we pop the num from the numstack
                int cnt = NumStack.pop();
                StringBuilder baby = new StringBuilder();

                // we need the string in currString to be printed as many times as the popped
                // number
                for (int k = 0; k < cnt; k++) {
                    baby.append(currStr);
                }

                // then we append it to the parent in the StringStack
                StringBuilder parent = StringStack.pop();
                currStr = parent.append(baby);
            }

            // If the character is an alphabet
            else {
                // We append it to the currStr
                currStr.append(c);
            }
        }
        return currStr.toString();
    }

    // TC :O(2(mxn)) // SC : O(min(m,n))
    int[][] dirs;
    int m, n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        this.dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }

        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        // base
        if (i < 0 || j < 0 || i == m || j == n || grid[i][j] == '0')
            return;
        // logic
        grid[i][j] = '0'; // We change the cell which had 1 to 0
        for (int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            dfs(grid, nr, nc);
        }
    }
}