package com.akshayram.basicprograms;

import java.util.ArrayDeque;
import java.util.Queue;

public class ArrayRearrange {

    //Rearrange Array Elements by Sign
    public int[] rearrangeArray(int[] nums) {
        final int N = nums.length;

        Queue<Integer> pos = new ArrayDeque<>();
        Queue<Integer> neg = new ArrayDeque<>();

        for (int x : nums) {
            if (x > 0) {
                pos.offer(x);
            } else {
                neg.offer(x);
            }
        }

        int[] ans = new int[N];
        for (int i = 0; i < N; i += 2) {
            ans[i] = pos.poll();
            ans[i + 1] = neg.poll();
        }
        return ans;
    }

    //TC: O(n) SC: O(1)
    public int[] rearrangeArray2(int[] nums) {
        int n = nums.length;
        if(n <= 1)  return nums;
        int[] ans = new int[n];
        int posPtr = 0;
        int negPtr = 1;
        for(int i=0; i<n; i++){
            if(nums[i] > 0){
                //positive number
                ans[posPtr] = nums[i];
                posPtr+=2;
            } else if(nums[i] < 0){
                //negative number
                ans[negPtr] = nums[i];
                negPtr+=2;
            }
        }
        return ans;
    }
}
