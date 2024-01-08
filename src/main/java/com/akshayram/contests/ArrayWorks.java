package com.akshayram.contests;

import java.util.*;

public class ArrayWorks {


    private static final int MOD = 1000000007;
    public static int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);

        List<Integer> hFences_list = new ArrayList<>();
        for (int num : hFences) {
            hFences_list.add(num);
        }

        List<Integer> vFences_list = new ArrayList<>();
        for (int num : vFences) {
            vFences_list.add(num);
        }

        // List<Integer> hFences_list = Arrays.stream(hFences)
        //                                 .collect(Collectors.toCollection(ArrayList::new));
        // List<Integer> vFences_list = Arrays.stream(vFences)
        //                                 .collect(Collectors.toCollection(ArrayList::new));

        hFences_list.add(m);
        vFences_list.add(n);

        int side = 0;
        int i = hFences_list.size() -1;
        int j=vFences_list.size() - 1;
        while ( i > 0  && j > 0) {
            int hElement = hFences_list.get(i);
            int vElement = vFences_list.get(j);
            if(hElement < vElement){
                if(vFences_list.contains(hElement)){
                    //side found
                    side = hElement;
                    break;
                }
                i--;
            } else {
                if(hFences_list.contains(vElement)){
                    //side found
                    side = vElement;
                    break;
                }
                j--;
            }
        }
        side--;
        if (side <= 0) {
            return -1;
        }
        long maxArea = ((long) side * side) % MOD;
        return (int)maxArea;
    }

    public static void main(String[] args) {
//        int m = 4; // Number of horizontal lines
//        int n = 3; // Number of vertical lines
//        int[] hFences = {2, 3}; // Horizontal fences
//        int[] vFences = {2}; // Vertical fences

//        int m = 6; // Number of horizontal lines
//        int n = 7; // Number of vertical lines
//        int[] hFences = {2}; // Horizontal fences
//        int[] vFences = {4}; // Vertical fences
        //ans = -1;

        int m = 3; // Number of horizontal lines
        int n = 9; // Number of vertical lines
        int[] hFences = {2}; // Horizontal fences
        int[] vFences = {8,6,5,4}; // Vertical fences
        //ans = 4
        int maxArea = maximizeSquareArea(m, n, hFences, vFences);
        System.out.println("Maximum area of a square field: " + maxArea);
    }

}
