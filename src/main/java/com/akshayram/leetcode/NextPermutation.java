package com.akshayram.leetcode;

public class NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while(i>=0 && nums[i]>=nums[i+1]){
            i--;
        }
        if(i>=0){
            int j = nums.length -1;
            while(j>=0 && nums[j] <= nums[i]){
                j--;
            }
            swapTwo(nums,i,j);
        }
        swapArray(nums, i+1, nums.length -1);
    }
    
    private void swapTwo(int[] nums, int l, int r){
        int temp= nums[l];
        //nums[l++] = nums[r];
        //nums[r--] = temp;
        nums[l] = nums[r];
        nums[r] = temp;
    }
    
    private void  swapArray(int[] nums, int l, int r){
        while(l<r){
            int temp= nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }
    }

}
