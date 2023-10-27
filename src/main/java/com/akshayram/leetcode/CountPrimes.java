package com.akshayram.leetcode;

import java.util.Scanner;

public class CountPrimes {
    public static void main(String[] args) {
        System.out.println("Enter Num");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int num = countPrimes(Integer.parseInt(str));
        System.out.println("Prime Count is:" + num);
    }

    public static int countPrimes_1(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime_1(i)) count++;
        }
        return count;
    }

    /* TLE */
    public static boolean isPrime_1(int num) {
        if (num <= 1) return false;
        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /*Sieve of Eratosthenes*/
    public static int countPrimes(int num) {
        if (num <= 2) return 1;

        // init an array to track prime numbers
        boolean[] primes = new boolean[num];
        for (int i = 2; i < num; i++)
            primes[i] = true;

        for (int i = 2; i <= Math.sqrt(num - 1); i++) {
            // or for (int i = 2; i <= n-1; i++) {
            if (primes[i]) {
                for (int j = i + i; j < num; j += i)
                    primes[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < num; i++) {
            if (primes[i])
                count++;
        }
        return count;
    }
}
