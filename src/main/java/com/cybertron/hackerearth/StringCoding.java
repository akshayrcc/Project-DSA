package com.cybertron.hackerearth;/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

import java.io.BufferedReader;
import java.io.InputStreamReader;

class StringCoding {
    public static void main(String[] args) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        StringBuilder sb = new StringBuilder();
        char chr = inputString.charAt(0);
        Long count = 1L;
        for (int i = 1; i < inputString.length(); i++) {
            char current = inputString.charAt(i);
            if (current == chr) {
                count++;
            } else {
                sb.append(chr);
                sb.append(count);
                chr = current;
                count = 1L;
            }
        }
        sb.append(chr);
        sb.append(count);
        System.out.println(sb);
    }
}
