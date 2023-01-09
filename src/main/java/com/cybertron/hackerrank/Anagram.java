package com.cybertron.hackerrank;

import java.io.*;
import java.util.stream.IntStream;

class Result {

    /*
     * Complete the 'anagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int anagram(String s) {
        int length = s.length();
        int[] char_counts = new int[26];
        int sum;

        //Return -1 if length is Odd.
        if (length % 2 != 0) return -1;

        for (int i = 0; i < length / 2; i++) {
            char c1 = s.charAt(i);
            char_counts[c1 - 'a']++;
            char c2 = s.charAt(length / 2 + i);
            char_counts[c2 - 'a']--;
        }

        sum = 0;
        for (int i : char_counts) {
//            if ( i != 0 ) System.out.print(" " + i);
            if (i > 0) sum += i;
        }
        return sum;
    }

}

public class Anagram {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/cybertron/hackerrank/InputFile.txt"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("src/main/java/com/cybertron/hackerrank/OutputFile.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/com/cybertron/hackerrank/OutputFile.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();
                int result = Result.anagram(s);
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}