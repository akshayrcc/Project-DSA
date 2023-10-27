package com.akshayram.s30.Backtracking_4;

import java.util.*;

class BT4_Solution {
  List<String> result;

  // TC: O(2^N) SC: O(2^N)
  public String[] expand(String s) {
    this.result = new ArrayList<>();
    List<List<Character>> groups = new ArrayList<>();
    int idx = 0;
    while (idx < s.length()) {
      char c = s.charAt(idx);
      List<Character> block = new ArrayList<>();
      if (c == '{') {
        idx++;
        while (s.charAt(idx) != '}') {
          if (s.charAt(idx) != ',') {
            block.add(s.charAt(idx));
          }
          idx++;
        }
      } else {
        block.add(c);
      }
      idx++;
      Collections.sort(block);
      groups.add(block);
    }
    backtrack(groups, 0, new StringBuilder());
    String[] returnResult = new String[result.size()];
    for (int i = 0; i < result.size(); i++) {
      returnResult[i] = result.get(i);
    }
    return returnResult;
  }

  private void backtrack(List<List<Character>> groups, int index, StringBuilder sb) {
    // base
    if (index == groups.size()) {
      result.add(sb.toString());
      return;
    }
    // logic
    for (char c : groups.get(index)) {
      // action
      sb.append(c);
      // recurse
      backtrack(groups, index + 1, sb);
      // backtrack
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}

class OptimalBuildingParking {
  public static void main(String[] args) {
    BuildingPlacement buildingPlacement = new BuildingPlacement();
    System.out.print(buildingPlacement.findMinDistance(7, 7, 3));
  }

  public static class BuildingPlacement {
    int H;
    int W;
    int min;

    // TC: O(H * W), SC: O(H * W)
    public int findMinDistance(int h, int w, int n) {
      this.H = h;
      this.W = w;
      this.min = Integer.MAX_VALUE;
      int[][] grid = new int[H][W];
      for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
          grid[i][j] = -1;
        }
      } // HW+(HW* HWcN)
      backtrack(grid, n, 0);
      return min;
    }

    // TC: O(H * W choose n), SC: O(H * W)
    private void bfs(int[][] grid) {
      Queue<int[]> q = new LinkedList<>();
      boolean[][] visited = new boolean[H][W];
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
          if (grid[i][j] == 0) {
            q.add(new int[] {i, j});
            visited[i][j] = true;
          }
        }
      }
      int dist = 0;
      while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
          int[] curr = q.poll();
          for (int[] dir : dirs) {
            int nr = curr[0] + dir[0];
            int nc = curr[1] + dir[1];
            if (nr < H && nr >= 0 && nc < W && nc >= 0 && grid[nr][nc] == -1 && !visited[nr][nc]) {
              q.add(new int[] {nr, nc});
              visited[nr][nc] = true;
            }
          }
        }
        dist++;
      }
      min = Math.min(min, dist - 1);
    }

    // TC: O(H * W choose n), SC: O(H * W)
    private void backtrack(int[][] grid, int n, int pivot) {
      // base
      if (n == 0) {
        bfs(grid);
        return;
      }
      // logic
      for (int i = pivot; i < H * W; i++) {
        int r = i / W;
        int c = i % W;
        // action
        grid[r][c] = 0;
        // recurse
        backtrack(grid, n - 1, i + 1);
        // backtrack
        grid[r][c] = -1;
      }
    }
  }
}
