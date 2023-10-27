package com.akshayram.oa;

import java.util.List;

public class VideoDistortion2 {
    public static int minPossibleDistortion(List<Integer> packets, int max_frame) {
        int n = packets.size();

        // Initialize an array to store the minimum distortions
        int[] dp = new int[n];

        // Initialize the dp array with maximum values
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // Base case: If there is only one packet, the distortion is 0
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int maxDiff = 0;

                // Find the maximum difference between consecutive packets
                for (int k = j + 1; k <= i; k++) {
                    maxDiff = Math.max(maxDiff, Math.abs(packets.get(k) - packets.get(k - 1)));
                }

                // Check if the maximum difference is within the allowed limit
                if (maxDiff <= max_frame) {
                    // Update the minimum distortion for packet i
                    dp[i] = Math.min(dp[i], dp[j] + maxDiff);
                }
            }
        }

        // Find the minimum distortion for the last packet
        int minDistortion = dp[n - 1];

        if (minDistortion == Integer.MAX_VALUE) {
            return -1; // Indicates that no valid configuration is possible
        }

        return minDistortion;
    }

    public static void main(String[] args) {
        List<Integer> packets = List.of(5, 0, 0, 0, 4);
        int max_frame = 10;
        int result = minPossibleDistortion(packets, max_frame);
        if (result == -1) {
            System.out.println("No valid configuration is possible.");
        } else {
            System.out.println("Minimum Possible Distortion: " + result); // Output: 1
        }
    }
}

/*
* Working: 1
*
* public static int minPossibleDistortion(List<Integer> packets1, int max_frame) {
    List<Integer> packets = new ArrayList<>(packets1);
    int n = packets.size();
    HashSet<Integer> hs = new HashSet<>();
    if(packets.get(0) == 0){ hs.add(0);}
    for (int j = 1; j < n; j++) {
        if(packets.get(j) == 0 && packets.get(j-1) != 0){
            hs.add(j);
        }
    }

    int minDistVal = Integer.MAX_VALUE;
    for(int x = 0; x <= max_frame; x++ ){
        //replace 0 with x
        for(int val: hs){
            packets.set(val, x);
        }

        int currentDistortion = 0;
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(packets.get(i) - packets.get(i - 1));
            currentDistortion = Math.max(currentDistortion, diff);
        }
        minDistVal = Math.min(minDistVal, currentDistortion);
    }

    return minDistVal;
}
*
* */