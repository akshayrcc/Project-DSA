package com.akshayram.oa;

import java.util.ArrayList;
import java.util.List;

public class FuturePricesCalculator {
    public static List<Integer> calculateFuturePrices(int S, int N, List<int[]> dividends, List<String[]> queries) {
        int[] future_prices = new int[101]; // Initialize an array to store cumulative dividends up to day 100
        System.out.println("Stock val :" + S);
        for(int i=0; i<101;i++){
            future_prices[i] = S;
        }
        // Calculate cumulative dividends
        for (int[] dividend : dividends) {
            int A = dividend[0];
            int D = dividend[1];
            System.out.println(" "+ A + " " + D);
            for(int i=D; i<101;i++){
                future_prices[i] -= A;
            }
//            future_prices[D] -= A;
        }
        for(int m=0; m<future_prices.length;m++){
            System.out.print(future_prices[m] + " ");
        }

        System.out.println(" ");
        List<Integer> result = new ArrayList<>();

        for (String[] query : queries) {
            if (query[0].equals("DIVIDEND_UPDATE")) {
                int i = Integer.parseInt(query[1]);
                int A = Integer.parseInt(query[2]);
                int D = Integer.parseInt(query[3]);
                System.out.println(" "+ i + " " + A + " " + D);
                for(int j=D; j<101;j++){
                    future_prices[j] -= A;
                }

            } else if (query[0].equals("PRICE")) {
                int day = Integer.parseInt(query[1]);
                System.out.println(" " + day);
                result.add(future_prices[day]);
            }
        }

//        for(int m=0; m<future_prices.length;m++){
//            System.out.print(future_prices[m] + " ");
//        }
        return result;
    }

    public static void main(String[] args) {
        int S = 1000;
        int N = 2;
        int Q = 4;

        List<int[]> dividends = new ArrayList<>();
        dividends.add(new int[]{100, 10});
        dividends.add(new int[]{50, 100});

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"PRICE", "15"});
        queries.add(new String[]{"DIVIDEND_UPDATE", "2", "40", "20"});
        queries.add(new String[]{"PRICE", "15"});
        queries.add(new String[]{"PRICE", "25"});

        // Calculate future prices and print the results
        List<Integer> result = calculateFuturePrices(S, N, dividends, queries);
        for (int price : result) {
            System.out.println(price);
        }
    }
}
