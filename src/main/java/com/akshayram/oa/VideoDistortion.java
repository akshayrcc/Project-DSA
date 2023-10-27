package com.akshayram.oa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VideoDistortion {
    public static int minPossibleDistortion(List<Integer> packets1, int max_frame) {

        List<Integer> packets = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();

        //first element process
        int prev = packets1.get(0);
        if(packets1.get(0) == 0){ hs.add(0);}
        packets.add(prev);
        int newIndex = 0;
        int avgVal = Integer.MIN_VALUE;

        //traversing input list
        for (int m = 1; m < packets1.size(); m++) {
            int curr = packets1.get(m);
            if(curr != prev){
                packets.add(curr);
                newIndex++;
                if(curr == 0){ //find zero positions
                    hs.add(newIndex);
                } else {
                    if(avgVal == Integer.MIN_VALUE) {
                        avgVal = Math.abs(curr);
                    } else {
                        avgVal =  Math.abs(curr) + Math.abs(avgVal - Math.abs(curr))/2;
                    }
                }
            }
            prev = curr;
        }
        int n = packets.size();

        System.out.println("hset size " + hs.size());
        for(int val: hs){
            System.out.println(" hset-val " + val );
        }

        System.out.println("packets : " + packets.size());
        for(int val: packets){
            System.out.print(val + " ");
        }


        int x = avgVal;
        System.out.println("avgVal " + avgVal);
        if(x > max_frame){
            x = max_frame;
        }

        System.out.println("x " + x);

//        int minDistVal = Integer.MAX_VALUE;

        //replace 0 with x
        for(int val: hs){
            packets.set(val, x);
        }

       // for(int x = 0; x <= max_frame; x++ ){

            int currentDistortion = 0;
            for (int i = 1; i < n; i++) {
                int currVal = packets.get(i);
                int preVal = packets.get(i-1);
                int diff = Math.abs(currVal - preVal);

                //check if its zero index
                if(currVal == 0){
                    diff = Math.min(Math.abs(max_frame - preVal) , Math.abs(preVal));
                }
                currentDistortion = Math.max(currentDistortion, diff);
            }
//            minDistVal = Math.min(minDistVal, currentDistortion);
        //}

        return currentDistortion;
    }

    public static void main(String[] args) {
        int max_frame;
//         List<Integer> packets = List.of(5, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 4); max_frame = 10; //op=1
//        List<Integer> packets = List.of(10, 0, 6, 0, 8, 0);
        List<Integer> packets = List.of( 0, 7, 6, 0, 7, 6); max_frame = 2;  // op=5
        int result = minPossibleDistortion(packets, max_frame);
        System.out.println("Minimum Possible Distortion: " + result); // Output: 2
    }
}
