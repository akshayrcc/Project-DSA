package com.akshayram.stringops;

import java.util.ArrayList;
import java.util.List;

public class StringEz {

    public static void main(String[] args) {
        StringEz stringEz = new StringEz();
//        System.out.println(stringEz.paperCuttings(15, List.of(1, 3, 5, 7, 9), List.of(2, 6, 8, 10, 15)));
        System.out.println(stringEz.paperCuttings(10, List.of(3,1,2,8,8), List.of(5,3,7,10,10)));
//        System.out.println(stringEz.paperCuttings(5, List.of(3, 4, 5, 6, 8), List.of(4, 5, 6, 7, 8)));
    }
    public long paperCuttings(int textLength, List<Integer> starting, List<Integer> ending) {
        int n = starting.size();

        // Sort starting and ending points together based on starting positions
        List<Pair<Integer, Integer>> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            positions.add(new Pair<>(starting.get(i), ending.get(i)));
        }
        positions.sort((a, b) -> a.getFirst() - b.getFirst());

        // Use a nested loop to explore combinations of non-overlapping intervals
        long nonOverlappingPairs = 0;
        for (int i = 0; i < n; i++) {
            int currentEnd = positions.get(i).getSecond();
            for (int j = i + 1; j < n; j++) {
                Pair<Integer, Integer> nextPair = positions.get(j);
                if (nextPair.getFirst() > currentEnd ||
                        (nextPair.getFirst() == currentEnd && nextPair.getSecond() <= textLength)) {  // Added condition
                    // Found a non-overlapping pair (including ending at textLength)
                    nonOverlappingPairs++;
                    currentEnd = nextPair.getSecond(); // Update currentEnd for further exploration
                }
            }
        }

        return nonOverlappingPairs;
    }


//    public static List<Pair<Integer, Integer>> findNonOverlappingCouples(List<Pair<Integer, Integer>> pairs) {
//        List<Pair<Integer, Integer>> nonOverlappingCouples = new ArrayList<>();
//
//        // Sort pairs based on starting index
//        pairs.sort((a, b) -> a.getFirst() - b.getFirst());
//
//        int currentEnd = -1;
//
//        for (Pair<Integer, Integer> pair : pairs) {
//            int start = pair.getFirst();
//            int end = pair.getSecond();
//
//            if (start > currentEnd) {  // No overlap with previous interval
//                nonOverlappingCouples.add(pair);
//                currentEnd = end;  // Update current ending for next check
//            }
//        }
//
//        return nonOverlappingCouples;
//    }

//    public List<Pair<Integer, Integer>> findAllNonOverlappingCombinations(List<Pair<Integer, Integer>> pairs) {
//        List<Pair<Integer, Integer>> allCombinations = new ArrayList<>();
//
//        // Sort pairs based on starting index
//        pairs.sort((a, b) -> a.getFirst() - b.getFirst());
//
//        int currentEnd = -1;
//
//        for (int i = 0; i < pairs.size(); i++) {
//            Pair<Integer, Integer> pair1 = pairs.get(i);
//            int start1 = pair1.getFirst();
//            int end1 = pair1.getSecond();
//
//            if (start1 > currentEnd) {  // No overlap with previous interval
//                currentEnd = end1;
//
//                // Combine pair1 with remaining non-overlapping pairs
//                for (int j = i + 1; j < pairs.size(); j++) {
//                    Pair<Integer, Integer> pair2 = pairs.get(j);
//                    int start2 = pair2.getFirst();
//                    int end2 = pair2.getSecond();
//
//                    if (start2 > currentEnd) {  // No overlap with current pair or previous intervals
//                        allCombinations.add(new Pair<>(start1, end1));
//                        allCombinations.add(new Pair<>(start2, end2));
//                        currentEnd = Math.max(currentEnd, end2);  // Update for next iteration
//                    }
//                }
//
//                // Add pair1 alone if no further non-overlapping pair found
//                if (!allCombinations.contains(pair1)) {
//                    allCombinations.add(pair1);
//                }
//            }
//        }
//
//        return allCombinations;
//    }
//
//    public long paperCuttings(int textLength, List<Integer> starting, List<Integer> ending) {
//        int n = starting.size();
//        long nonOverlappingPairs = 0;
//
//        // Sort starting and ending points together based on starting positions
//        List<Pair<Integer, Integer>> positions = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            positions.add(new Pair<>(starting.get(i), ending.get(i)));
//        }
//        List<Pair<Integer, Integer>> ans = findAllNonOverlappingCombinations(positions);
//        //print ans
//        for (Pair<Integer, Integer> pair : ans) {
//            System.out.println(pair.getFirst() + " " + pair.getSecond());
//        }
//        return ans.size();
//    }


    public long paperCuttings2(int textLength, List<Integer> starting, List<Integer> ending) {
        int n = starting.size();

        // Sort starting and ending points together based on starting positions
        List<Pair<Integer, Integer>> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            positions.add(new Pair<>(starting.get(i), ending.get(i)));
        }
        positions.sort((a, b) -> a.getFirst() - b.getFirst());

        // Use a nested loop to explore combinations of non-overlapping intervals
        long nonOverlappingPairs = 0;
        for (int i = 0; i < n; i++) {
            int currentEnd = positions.get(i).getSecond();
            for (int j = i + 1; j < n; j++) {
                Pair<Integer, Integer> nextPair = positions.get(j);
                if (nextPair.getFirst() > currentEnd) {
                    // Found a non-overlapping pair
                    nonOverlappingPairs++;
                    currentEnd = nextPair.getSecond(); // Update currentEnd for further exploration
                }
            }
        }

        return nonOverlappingPairs;
    }

    public long paperCuttings1(int textLength, List<Integer> starting, List<Integer> ending) {
        int n = starting.size();

        // Sort starting and ending points together based on starting positions
        List<Pair<Integer, Integer>> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            positions.add(new Pair<>(starting.get(i), ending.get(i)));
        }
        positions.sort((a, b) -> a.getFirst() - b.getFirst());

        // Variables to track current and previous non-overlapping intervals
        int currentStart = -1, currentEnd = -1;
        long nonOverlappingPairs = 0;

        for (Pair<Integer, Integer> position : positions) {
            int newStart = position.getFirst();
            int newEnd = position.getSecond();

            // Check for overlap with the previous interval, considering identical intervals as overlapping
            if (newStart > currentEnd || (newStart == currentStart && newEnd == currentEnd)) {
                // No overlap or identical interval, consider the current interval as a potential candidate
                if (currentStart != -1 && currentEnd != -1) {
                    nonOverlappingPairs++; // Add 1 for the previous interval
                }
                currentStart = newStart;
                currentEnd = newEnd;
            } else {
                // Overlap, update the current interval if the new ending extends further
                currentEnd = Math.max(currentEnd, newEnd);
            }
        }

        // Consider the last interval if it doesn't overlap
        if (currentStart != -1 && currentEnd != -1) {
            nonOverlappingPairs++;
        }

        return nonOverlappingPairs;
    }
//    public long paperCuttings(int textLength, List<Integer> starting, List<Integer> ending) {
//        int n = starting.size();
//
//        // Sort starting and ending points together based on starting positions
//        List<Pair<Integer, Integer>> positions = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            positions.add(new Pair<>(starting.get(i), ending.get(i)));
//        }
//        positions.sort((a, b) -> a.getFirst() - b.getFirst());
//
//        // Variables to track current and previous non-overlapping intervals
//        int currentStart = -1, currentEnd = -1;
//        long nonOverlappingPairs = 0;
//
//        for (Pair<Integer, Integer> position : positions) {
//            int newStart = position.getFirst();
//            int newEnd = position.getSecond();
//
//            // Check for overlap with the previous interval
//            if (newStart > currentEnd) {
//                // No overlap, consider the current interval as a potential candidate
//                if (currentStart != -1 && currentEnd != -1) {
//                    nonOverlappingPairs++; // Add 1 for the previous interval
//                }
//                currentStart = newStart;
//                currentEnd = newEnd;
//            } else {
//                // Overlap, update the current interval if the new ending extends further
//                currentEnd = Math.max(currentEnd, newEnd);
//            }
//        }
//
//        // Consider the last interval if it doesn't overlap
//        if (currentStart != -1 && currentEnd != -1) {
//            nonOverlappingPairs++;
//        }
//
//        return nonOverlappingPairs;
//    }

    // Helper class to represent a pair of starting and ending positions
    class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }
}
