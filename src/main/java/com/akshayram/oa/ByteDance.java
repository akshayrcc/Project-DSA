package com.akshayram.oa;/*
ByteDance is planning to buy some GPUs for training their new computer vision models using them.
They have 2 clusters on which all the models are saved.

There are n GPUs available, where the cost of the ith GPU is represented by array element cost[i]. Also, there are two arrays compatible1 and compatible2 each containing n integers, where each integer is either 0 or 1, representing the following:

If compatible1[i]= 1, then the ith GPU is compatible with cluster 1, else it is not compatible with cluster 1.
If compatible2[i]= 1, then the ith GPU is compatible with cluster 2, else it is not compatible with cluster 2.
The company wants to buy the GPUs such that there are at least a min_compatible number of GPUs compatible with each of cluster 1 and cluster 2.

Given n GPUs, an integer min_compatible, and three arrays cost, compatible1 and compatible2, find the minimum possible cost of GPUs such that there are at least a min_compatible number of GPUs compatible with each of cluster 1 and cluster 2.

Return -1 if it is not possible to buy the GPUs satisfying the above condition.

Example
Given, cost = [2, 4, 6, 5], compatible1 = [1, 1, 1, 0], compatible2 = [0, 0, 1, 1], and min_compatible = 2. Some of the ways of buying the GPUs are explained below:

https://leetcode.com/company/tiktok/discuss/3990858/Tiktok-OA
*/

import java.util.*;

public class ByteDance {
    public static void main(String[] args) {
        int[] cost = new int[]{2, 4, 6, 5};
        int[] c1 = new int[]{1, 1, 1, 0};
        int[] c2 = new int[]{0, 0, 1, 1};
        int minCompatible = 2;
        System.out.println(minimumGPUsRequired(cost, c1, c2, minCompatible));
    }

    private static int minimumGPUsRequired(int[] cost, int[] compatibility_1, int[] compatibility_2, int min_compatible) {

        List<Integer> first_list = new ArrayList<>();
        List<Integer> second_list = new ArrayList<>();
        List<Integer> common_list = new ArrayList<>();

        for (int i = 0; i < compatibility_1.length; i++) {
            if (compatibility_1[i] == 1 && compatibility_2[i] == 1) {
                common_list.add(cost[i]);
            } else if (compatibility_1[i] == 1) {
                first_list.add(cost[i]);
            } else {
                second_list.add(cost[i]);
            }
        }

        int i1 = 0, i2 = 0, i3 = 0;
        int result = 0;

        while (min_compatible != 0 && (i3 < common_list.size() || i1 < first_list.size() || i2 < second_list.size())) {
            if (i3 < common_list.size() && i1 < first_list.size() && i2 < second_list.size()) {
                if (common_list.get(i3) <= first_list.get(i1) + second_list.get(i2)) {
                    result += common_list.get(i3++);
                } else {
                    result += first_list.get(i1++) + second_list.get(i2++);
                }
            } else {
                if (i1 < first_list.size()) {
                    result += first_list.get(i1++);
                }
                if (i2 < second_list.size()) {
                    result += second_list.get(i2++);
                }
            }
            min_compatible--;
        }
        return result;
    }
}