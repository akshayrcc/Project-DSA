package com.akshayram.leetcode;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SherlockAndAnagrams {

    private static final Scanner scanner = new Scanner(System.in);

    static int sherlockAndAnagrams(String s) {

//    	char[] ch = new char[s.length()];
//    	for (int i = 0; i < s.length(); i++) {
//            ch[i] = s.charAt(i);
//        }
        int anagrammaticPairCount = 0;

        for (int size = 1; size < s.length(); size++) {
            //System.out.println("Size "+size);

            for (int j = 0; j < s.length(); j++) {
                //System.out.println("J is "+j);

                for (int k = j + 1; k < s.length(); k++) {
                    //System.out.println("K is "+k+ "  K + size is"+ (k+size));

                    if (!((j + size) > s.length() || (k + size) > s.length())) {
                        //System.out.println("==>Checking anagrams for "+s.substring(j,j+size) + " "+ s.substring(k,k+size)  );
                        if (isAnagram(s.substring(j, j + size), s.substring(k, k + size))) {
                            //if(isAnagram()) {
                            //System.out.println("~~~Ana Found~~~");
                            anagrammaticPairCount++;
                        }
                    }
                }
            }
        }
        //System.out.println("anagrammaticPairCount "+anagrammaticPairCount);
        return anagrammaticPairCount;
    }

    static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] ch1 = new char[s1.length()];
        char[] ch2 = new char[s2.length()];

        for (int i = 0; i < s1.length(); i++) {
            ch1[i] = s1.charAt(i);
            ch2[i] = s2.charAt(i);
        }

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        for (int i = 0; i < s1.length(); i++) {
            if (ch1[i] != ch2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\testout.txt"))) {
            bufferedWriter.write("Welcome to Java World.");
            bufferedWriter.newLine();

            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();

                //boolean result = isAnagram("RAM","MAA");
                int result = sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }

        }

        scanner.close();
    }
}

