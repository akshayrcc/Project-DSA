package com.akshayram.oa;

public class VideoDistortion1 {
    public static int minPossibleDistortion(int[] packets, int max_frame) {
        int n = packets.length;

        // Calculate the current distortion
        int currentDistortion = 0;
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(packets[i] - packets[i - 1]);
            currentDistortion = Math.max(currentDistortion, diff);
        }

        // Find the minimum and maximum values in the packets array
        int minPacketValue = Integer.MAX_VALUE;
        int maxPacketValue = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            minPacketValue = Math.min(minPacketValue, packets[i]);
            maxPacketValue = Math.max(maxPacketValue, packets[i]);
        }

        // Calculate the new distortion after replacing zeros
        int newDistortion = Integer.MAX_VALUE;
        for (int x = minPacketValue; x <= maxPacketValue; x++) {
            int tempDistortion = 0;
            for (int i = 1; i < n; i++) {
                int prev = packets[i - 1] == 0 ? x : packets[i - 1];
                int current = packets[i] == 0 ? x : packets[i];
                int diff = Math.abs(current - prev);
                tempDistortion = Math.max(tempDistortion, diff);
            }
            newDistortion = Math.min(newDistortion, tempDistortion);
        }

        // Return the minimum of the current and new distortions
        return Math.min(currentDistortion, newDistortion);
    }

    public static void main(String[] args) {
        //int[] packets = {5, 0, 0, 0, 4};
        int[] packets = {10, 0, 6, 0, 8, 0};
        int max_frame = 8;
        int result = minPossibleDistortion(packets, max_frame);
        System.out.println("Minimum Possible Distortion: " + result); // Output: 1
    }
}
