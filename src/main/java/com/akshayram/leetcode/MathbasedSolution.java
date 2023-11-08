package com.akshayram.leetcode;

public class MathbasedSolution {

  public static boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
    int width = Math.abs(sx - fx);
    int height = Math.abs(sy - fy);
    if (width == 0 && height == 0 && t == 1) {
      return false;
    }
    return t >= Math.max(width, height);
  }

  public static void main(String[] args) {
    int sx = 1;
    int sy = 1;
    int fx = 3;
    int fy = 4;
    int t = 1;
    boolean isReachable = isReachableAtTime(sx, sy, fx, fy, t);
    System.out.println("Is the destination reachable at time " + t + " ? " + isReachable);
  }
}
