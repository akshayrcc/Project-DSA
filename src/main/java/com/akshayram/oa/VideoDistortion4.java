package com.akshayram.oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VideoDistortion4 {
  public static int minPossibleDistortion(List<Integer> packets, int max_frame) {
    int n = packets.size();

    // Calculate the current distortion
    int currentDistortion = 0;
    for (int i = 1; i < n; i++) {
      int diff = Math.abs(packets.get(i) - packets.get(i - 1));
      currentDistortion = Math.max(currentDistortion, diff);
    }

    // Find the minimum and maximum values in the packets list
    int minPacketValue = Integer.MAX_VALUE;
    int maxPacketValue = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      minPacketValue = Math.min(minPacketValue, packets.get(i));
      maxPacketValue = Math.max(maxPacketValue, packets.get(i));
    }

    // Calculate the new distortion after replacing zeros
    int newDistortion = Integer.MAX_VALUE;
    for (int x = minPacketValue; x <= maxPacketValue; x++) {
      int tempDistortion = 0;
      for (int i = 1; i < n; i++) {
        int prev = packets.get(i - 1) == 0 ? x : packets.get(i - 1);
        int current = packets.get(i) == 0 ? x : packets.get(i);
        int diff = Math.abs(current - prev);
        tempDistortion = Math.max(tempDistortion, diff);
      }
      newDistortion = Math.min(newDistortion, tempDistortion);
    }

    // Return the minimum of the current and new distortions
    return Math.min(currentDistortion, newDistortion);
  }

  public static void main(String[] args) {
    int max_frame;
    //         List<Integer> packets = List.of(5, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    // 0, 4); max_frame = 10; //op=1
    //        List<Integer> packets = List.of(10, 0, 6, 0, 8, 0);
    List<Integer> packets = List.of(0, 7, 6, 0, 7, 6);
    max_frame = 2; // op=5
    int result = minPossibleDistortion(packets, max_frame);
    System.out.println("Minimum Possible Distortion: " + result); // Output: 2
  }
}
