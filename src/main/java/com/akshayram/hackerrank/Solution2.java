package com.akshayram.hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution2 {

    // Complete the maxTickets function below.
    static int maxTickets(List<Integer> tickets) {
        int result = Integer.MAX_VALUE;
        
        //Sort the tickets in asc order
        Collections.sort(tickets);
        
        //calculated the frequencies for each ticket
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i< tickets.size(); i++){
            if(freqMap.containsValue(tickets.get(i))){
                freqMap.put(tickets.get(i), freqMap.get(tickets.get(i)) + 1);
            } else {
                freqMap.put(tickets.get(i), 1);
            }
        }
        
        System.out.println("Current Hashmap is here:");
		for (Map.Entry<Integer, Integer> entry1 : freqMap.entrySet()) {
			System.out.println("=>" + entry1.getKey() + " " + entry1.getValue());
		}
		
        int tempResult =0;
        int prevKey = 0;
        int prevValue = 0;
        
        for (Map.Entry<Integer, Integer> entry1 : freqMap.entrySet()) {
            //System.out.println("=>" + entry1.getKey() + " " + entry1.getValue());
            if( Math.abs(entry1.getKey() - prevKey) <=1){
                tempResult += entry1.getValue() + prevValue;
            } else {
                tempResult = 0;
            }
            System.out.println("tempResult "+ tempResult);
            result = Math.max(result, tempResult); 
            prevKey = entry1.getKey();
            prevValue = entry1.getValue();
        }
        // for(int j=0; j<freqMap.size()-1; j++) {
        //     result = Math.max(result, freqMap.get(j) + freqMap.get(j+1) );
        // }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int ticketsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ticketsTemp = new ArrayList<>();

        IntStream.range(0, ticketsCount).forEach(i -> {
            try {
                ticketsTemp.add(bufferedReader.readLine().replaceAll("\\s+$", ""));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> tickets = ticketsTemp.stream()
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int res = maxTickets(tickets);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
