package com.cybertron.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ShuffleAnArray {

    private int[] nums;
    private Random random;

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        if (nums == null) return null;
        int[] a = nums.clone();
        for (int j = 1; j < a.length; j++) {
            int i = random.nextInt(j + 1);
            swap(a, i, j);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        /*["Solution", "shuffle", "reset", "shuffle"]
            [[[1, 2, 3]], [], [], []]*/

        ShuffleAnArray obj = new ShuffleAnArray(nums);
        //int[] param_1 = obj.reset();
        int[] param_2 = obj.shuffle();
        int[] param_3 = obj.reset();

        int[] param_4 = obj.shuffle();
        int[] param_5 = obj.reset();

        int[] param_6 = obj.shuffle();
        int[] param_7 = obj.reset();

//        System.out.println(Arrays.toString(param_1));
        System.out.println(Arrays.toString(param_2));
        System.out.println(Arrays.toString(param_3));

        System.out.println(Arrays.toString(param_4));
        System.out.println(Arrays.toString(param_5));
        System.out.println(Arrays.toString(param_6));
        System.out.println(Arrays.toString(param_7));

        //Stream.of(param_2).forEach(System.out::println);
        //Arrays.asList(param_2).stream().forEach(s -> System.out.println(s));
        //Arrays.stream(param_3).forEach(System.out::println);
    }

    /*

    int[] original = null;
    int[] shuffle = null;
    Random rand = null;

    public Solution(int[] nums) {
        original = nums;
        shuffle = Arrays.copyOf(nums, nums.length);
        rand = new Random();
    }

    *//** Resets the array to its original configuration and return it. *//*
    public int[] reset() {
        shuffle = Arrays.copyOf(original, original.length);
        return shuffle;
    }

    *//** Returns a random shuffling of the array. *//*
    public int[] shuffle() {
        for(int i=0; i<shuffle.length; i++){
            int x = rand.nextInt(shuffle.length-i);
            int idx = x+i;
            int tmp = shuffle[idx];
            shuffle[idx] = shuffle[i];
            shuffle[i] = tmp;
        }
        return shuffle;
    }*/


}
