package com.akshayram.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {

    //TC: O(logN)
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek() <= interval[0]) {
                pq.poll();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }
    // TC: O(log n) SC: O(n)
    public static int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek() <= interval[0]) {
                pq.poll();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }

    private static int minMeetingRooms3(int[][] intervals){
        if(intervals != null && intervals.length <= 0){
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //min heap .. end time
        Arrays.sort(intervals, (a,b) -> a[0]-b[0]); //start time
        for (int i = 0; i < intervals.length - 1; i++) {
            if(!pq.isEmpty() && intervals[i][0] >= pq.peek()){
                pq.poll(); //popping the min element in the heap
            }
            pq.add(intervals[i][1]);
        }
        return  pq.size();
    }


}
