package com.akshayram.stringops;

import java.util.ArrayList;
import java.util.List;

public class StringRanges {
    public static void main(String[] args) {
        int[] nums = new int[]{0,2,3,4,6,8,9}; //["0","2->4","6","8->9"]
        System.out.println("TEST:  "+ summaryRanges(nums));
    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int prev = nums[0];
        int start = nums[0];
        int end = nums[0];
        boolean isDouble = false;
        for(int i=1;i<nums.length;i++){
            if(nums[i] ==  (prev + 1)){
                end = nums[i];
                prev = nums[i];
                isDouble = true;
            } else {
                String str = getRangeVal(start, end, isDouble);
                System.out.println(str);
                ans.add(str);
                prev = nums[i];
                start = nums[i];
                end = nums[i];
                isDouble = false;
            }
        }
        String str = getRangeVal(start, end, isDouble);
        System.out.println(str);
        ans.add(str);
        return ans;
    }
    public static String getRangeVal(int start, int end, boolean isDouble){
        if(isDouble){
            return Integer.toString(start) + "->" + Integer.toString(end);
        } else {
            return Integer.toString(start);
        }
    }

}
