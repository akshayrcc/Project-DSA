package com.akshayram.leetcode;

import java.util.Arrays;
import java.util.Comparator;
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

    private static int minMeetingRooms3(int[][] intervals) {
        if (intervals != null && intervals.length <= 0) {
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //min heap .. end time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //start time
        for (int i = 0; i < intervals.length - 1; i++) {
            if (!pq.isEmpty() && intervals[i][0] >= pq.peek()) {
                pq.poll(); //popping the min element in the heap
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }

    //MR 3
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        PriorityQueue<Integer> openRoomIDs = new PriorityQueue<>();
        PriorityQueue<MeetingEnd> meetingEnds = new PriorityQueue<>(MeetingEnd.BY_END_TIME);
        for (int i = 0; i < n; ++i) {
            openRoomIDs.add(i);
        }

        int[] counts = new int[n];
        long t = 0;
        for (int[] m : meetings) {
            final long startTime = m[0];
            final long endTime = m[1];
            final long duration = endTime - startTime;

            t = Math.max(t, startTime);
            advanceTime(t, openRoomIDs, meetingEnds);

            if (openRoomIDs.isEmpty()) {
                t = meetingEnds.peek().endTime;
                advanceTime(t, openRoomIDs, meetingEnds);
            }

            int roomID = openRoomIDs.poll();
            ++counts[roomID];
            meetingEnds.offer(new MeetingEnd(roomID, t + duration));
        }

        int maxRoomID = 0;
        for (int i = 1; i < n; ++i) {
            if (counts[i] > counts[maxRoomID]) {
                maxRoomID = i;
            }
        }
        return maxRoomID;
    }

    private static void advanceTime(long t, PriorityQueue<Integer> openRoomIDs, PriorityQueue<MeetingEnd> meetingEnds) {
        while (!meetingEnds.isEmpty() && meetingEnds.peek().endTime <= t) {
            MeetingEnd end = meetingEnds.poll();
            openRoomIDs.offer(end.roomID);
        }
    }

    private static class MeetingEnd {
        public int roomID;
        public long endTime;

        public MeetingEnd(int roomID, long endTime) {
            this.roomID = roomID;
            this.endTime = endTime;
        }

        public static final Comparator<MeetingEnd> BY_END_TIME = new Comparator<>() {
            @Override
            public int compare(MeetingEnd a, MeetingEnd b) {
                return Long.compare(a.endTime, b.endTime);
            }
        };
    }


}
