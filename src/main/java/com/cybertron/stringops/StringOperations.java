package com.cybertron.stringops;

import java.util.Arrays;

public class StringOperations {

    public static void main(String[] args) {
        System.out.println("Hello String World");

        /*Reversing an array*/
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
        //reverseString(s);
        System.out.println(s[0]);
        System.out.println(Arrays.toString(s));
    }

    public static void reverseString(char[] s) {

        int length = s.length;
        int ptr_1 = 0, ptr_2 = length - 1;

        while (ptr_1 < ptr_2) {
            swap(s, ptr_1, ptr_2);
            ptr_1++;
            ptr_2--;
        }
    }

    public static void swap(char[] arr, int p1, int p2) {
        char temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}
