package com.akshayram.basicprograms;

import java.util.*;

public class TwoSum3Sums {

    //1. Two Sum
    //TC: O(n) SC: O(n)
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        int N = nums.length;
        if (N <= 1) return ans;

        Map<Integer, Integer> hmap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (hmap.containsKey(target - nums[i])) {
                ans[0] = i;
                ans[1] = hmap.get(target - nums[i]);
                return ans;
            } else {
                hmap.put(nums[i], i);
            }
        }
        return ans;
    }

    //15. 3Sum
    //TC: O(n^2) SC: O(n)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            int p = i + 1, q = N - 1; //pointer at 2nd and last loc
            while (p < q) {
                if (nums[p] + nums[q] == -nums[i]) {
                    //found exact triplet
                    List<Integer> tri = Arrays.asList(nums[i], nums[p], nums[q]);
                    ans.add(tri);

                    //skip same values
                    while (p + 1 < q && nums[p + 1] == nums[p]) p++;
                    while (q - 1 > p && nums[q - 1] == nums[q]) q--;

                    p++;
                    q--;
                } else if (nums[p] + nums[q] < -nums[i]) {
                    p++;
                } else {
                    q--;
                }
                //skip duplicate values in the sorted input array
                while (i + 1 < N && nums[i + 1] == nums[i]) i++;
            }
        }
        return ans;
    }
}
