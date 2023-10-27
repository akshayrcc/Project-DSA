package com.akshayram.oa;

import java.util.*;

public class FinalDataCalculator {

    public static List<Integer> getFinalData(List<Integer> data, List<List<Integer>> updates) {
        int n = data.size();
        // Initialize a list to keep track of the updates
        List<Integer> updatesTrack = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            updatesTrack.add(0);
        }
        // Process updates in reverse order
        for (List<Integer> update : updates) {
            int i = update.get(0);
            int r = update.get(1);
            updatesTrack.set(i - 1, updatesTrack.get(i - 1) + 1);  // Mark the start index of the update
            if (r < n) {
                updatesTrack.set(r, updatesTrack.get(r) - 1);  // Mark the end index of the update
            }
        }
        // Apply updates to the data
        for (int i = 1; i < n; i++) {
            updatesTrack.set(i, updatesTrack.get(i) + updatesTrack.get(i - 1));
        }
        // Update the data based on updatesTrack
        for (int i = 0; i < n; i++) {
            if (updatesTrack.get(i) % 2 == 1) {
                data.set(i, -data.get(i));
            }
        }
        return data;
    }
    public static List<Integer> getFinalData2(List<Integer> data, List<List<Integer>> updates) {
        Set<Integer> hset = new HashSet<>();
        List<Integer> finalList = new ArrayList<>(data);
        int minNum = 1;
        int maxNum = data.size();
        for (List<Integer> update : updates) {
            int startIndex = update.get(0);
            int endIndex = update.get(1);
            if(startIndex < minNum){
                startIndex = minNum;
            }
            if(endIndex > maxNum){
                endIndex = maxNum;
            }
            for(int k=startIndex;k<=endIndex;k++){
                if(hset.contains(k)){
                   hset.remove(k);
                } else {
                    hset.add(k);
                }
            }
        }
        for (Integer ele : hset) {
            System.out.println(ele);
        }
        System.out.println("-----------");
        for (int i = 0; i < finalList.size(); i++) {
            if(hset.contains(i+1)){
                int val = data.get(i);
                val  *= -1;
                finalList.set(i, val);
                System.out.println(" changed at "+ i);
            }
        }
        return finalList;
    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(3, 1, 3, 0, 7);
        List<List<Integer>> updates = Arrays.asList(
                Arrays.asList(2, 4),
                Arrays.asList(3, 5),
                Arrays.asList(2, 4),
                Arrays.asList(3, 5),
                Arrays.asList(2, 31)
        );
        List<Integer> finalData = FinalDataCalculator.getFinalData2(data, updates);
        System.out.println("Final Data: " + finalData); //[3, -1, -3, 0, -7]
    }
}
