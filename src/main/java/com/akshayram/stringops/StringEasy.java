package com.akshayram.stringops;

import java.util.Arrays;

public class StringEasy {

    public int minimumBoxes1(int[] apple, int[] capacity) {
        Arrays.sort(capacity);

        int boxes = 0;
        int m = capacity.length;

        int totalApple = 0;
        for (int a : apple) {
            totalApple += a;
        }

        for (int i = m - 1; i >= 0; i--) {
            int currCapacity = capacity[i];
            if (totalApple == 0) return boxes;

            if (totalApple >= currCapacity) {
                totalApple -= currCapacity;
                boxes++;
            } else if (totalApple < currCapacity) {
                boxes++;
                return boxes;
            }
        }
        if (totalApple > 0) return -1;
        return boxes;
    }

    public int minimumBoxes(int[] apple, int[] capacity) {
        int s = 0;
        for (int v : apple) {
            s += v;
        }
        Arrays.sort(capacity);
        int ans = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            if (s > 0) {
                ans++;
            }
            s -= capacity[i];
        }
        return ans;
    }

    public long maximumHappinessSum(int[] happiness, int k) {
        int N = happiness.length;
        Arrays.sort(happiness);
        long totalHappiness = 0;
        int k_count = 0;
        for (int i = N - 1; i >= 0; i--) {
            int temp = happiness[i] - k_count;
            if (temp < 0) temp = 0;
            totalHappiness += (temp);
            System.out.println(totalHappiness);
            k_count++;
            if (k_count == k) {
                break;
            }
        }
        return totalHappiness;
    }

    public long maximumHappinessSum2(int[] happiness, int k) {
        int N = happiness.length;
        Arrays.sort(happiness);
        long ans = 0;
        for (int i = N - k; i < N; i++) {
            ans += Math.max(0, happiness[i] - Math.min(k, N - 1 - i));
        }
        return ans;
    }
}
