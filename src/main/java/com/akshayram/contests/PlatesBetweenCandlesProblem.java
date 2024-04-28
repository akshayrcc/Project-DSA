package com.akshayram.contests;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class PlatesBetweenCandlesProblem {

    /*
     * Complete the 'numberOfItems' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER_ARRAY startIndices
     *  3. INTEGER_ARRAY endIndices
     */

    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {

        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> hmap = new HashMap<>();
        int N = s.length();

        int[] arr = new int[N];
        //pre-comp
        int j = 0;
        while (s.charAt(j) != '|') {
            j++;
        }

        int count = 0;
        for (; j < N; j++) {
            char ch = s.charAt(j);
            if (ch == '*') {
                count++;
            } else if (ch == '|') {
                hmap.put(j, count);
            }
        }

        //arroPs
        arr[0] = 0;
        for (int k = 0; k < N; k++) {
            if (!hmap.containsKey(k)) {
                if (k != 0) arr[k] = arr[k - 1];
            } else {
                arr[k] = hmap.get(k);
            }
        }

        //test cases
        int T = startIndices.size();
        for (int i = 0; i < T; i++) {
            int start = startIndices.get(i) - 1;
            int end = endIndices.get(i) - 1;
            int val = arr[end] - arr[start];
            ans.add(val);
        }

        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int startIndicesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> startIndices = IntStream.range(0, startIndicesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).map(String::trim).map(Integer::parseInt).collect(toList());

        int endIndicesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> endIndices = IntStream.range(0, endIndicesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).map(String::trim).map(Integer::parseInt).collect(toList());

        List<Integer> result = numberOfItems(s, startIndices, endIndices);

        bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n")) + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }

}