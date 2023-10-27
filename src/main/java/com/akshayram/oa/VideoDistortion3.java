package com.akshayram.oa;

import java.util.ArrayList;
import java.util.List;

public class VideoDistortion3 {
    public static int minPossibleDistortion(List<Integer> packets, int max_frame) {
        int n = packets.size();
        int[] countZero = new int[n];
        int zeroCount = 0;

        // Calculate the number of zeros in the prefix for each index
        for (int i = 0; i < n; i++) {
            if (packets.get(i) == 0) {
                zeroCount++;
            }
            countZero[i] = zeroCount;
        }

        int left = 0;
        int right = 0;
        int minDistVal = Integer.MAX_VALUE;

        // Use a sliding window to find the minimum distortion
        while (right < n) {
            int zerosInRange = countZero[right] - (left > 0 ? countZero[left - 1] : 0);
            if (zerosInRange <= max_frame) {
                int currentDistortion = Math.max(max_frame - zerosInRange, 1);
                minDistVal = Math.min(minDistVal, currentDistortion);
                right++;
            } else {
                left++;
            }
        }

        return minDistVal;
    }

    public static void main(String[] args) {
//        List<Integer> packets = List.of(5, 0, 0, 0, 4);
//        List<Integer> packets = List.of(10, 0, 6, 0, 8, 0);
        List<Integer> packets = List.of( 0, 7, 6, 0, 7, 6);
        int max_frame = 2;
        int result = minPossibleDistortion(packets, max_frame);
        System.out.println("Minimum Possible Distortion: " + result);
    }
}
