package com.akshayram.s30.Greedy_1;

import java.util.Arrays;

class Greedy_1 {


    //TC: O(n). SC:O(n)
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)  return 0;
        int n = ratings.length;
        int[] results = new int[n];
        Arrays.fill(results, 1);
        //left pass
        for(int i=1; i<n;i++){
            if(ratings[i] > ratings[i-1]){
                results[i] = results[i-1] + 1;
            }
        }
        int sum = results[n-1];
        //right pass
        for(int i=n-2; i>=0; i--){
            if(ratings[i] > ratings[i+1]){
                results[i] = Math.max(results[i], results[i+1]+1);
            }
            sum += results[i];
        }
        return sum;
    }
}