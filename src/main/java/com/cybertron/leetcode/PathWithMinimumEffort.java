package com.cybertron.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {

	public static void main(String[] args) {
		/* Let us create the example graph discussed above */
		int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		int heights[][] = new int[][] { { 1, 2, 2 }, { 3, 8, 2 }, { 5, 3, 5 } };
		System.out.println("ans: " + minimumEffortPath(heights));
	}

	static int[] dir = new int[] { 0, 1, 0, -1, 0 };

	public static int minimumEffortPath(int[][] heights) {
		int m = heights.length;
		int n = heights[0].length;

		int[][] efforts = new int[m][n];
		for (int[] effort : efforts) {
			Arrays.fill(effort, Integer.MAX_VALUE);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		efforts[0][0] = 0;

		pq.offer(new int[] { 0, 0, 0 });

		while (!pq.isEmpty()) {
			int[] head = pq.poll();
			int x = head[1];
			int y = head[2];
			int currentEfforts = head[0];
			if (x == (m - 1) && y == (n - 1)) {
				return currentEfforts;
			}
			for (int k = 0; k < 4; k++) {
				int newX = x + dir[k];
				int newY = y + dir[k + 1];

				if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
					int newEffort = Math.max(currentEfforts, Math.abs(heights[newX][newY] - heights[x][y]));
					if (newEffort < efforts[newX][newY]) {
						efforts[newX][newY] = newEffort;
						pq.offer(new int[] { newEffort, newX, newY });
					}
				}
			}
		}
		return 0;
	}
}
