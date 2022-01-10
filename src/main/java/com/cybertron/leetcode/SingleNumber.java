package com.cybertron.leetcode;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    public int singleNumber_1(int[] nums) {
        int singleNum = 0;
        Set<Integer> hashSet = new HashSet<>();
        for (int i : nums) {
            if (hashSet.contains(i)) {
                hashSet.remove(i);
            } else {
                hashSet.add(i);
            }
        }
        singleNum = hashSet.iterator().next();
        return singleNum;
    }

    public int singleNumber_2(int[] nums) {
        int n = 0;
        for(int i : nums){
            n ^= i; //this is ex-or operation
        }
        return n;
    }
}
