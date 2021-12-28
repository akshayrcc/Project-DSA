package com.cybertron.java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Ans" + solution(new int[] {-1,1,2,3,4}));
		System.out.println("Ans" + solution(new int[] {1,2,3}));
	}

	public static int solution(int[] A) {
        // write your code in Java SE 8
        int n = A.length;
        boolean[] present = new boolean[n + 1];
         for (int i = 0; i < n; i++) {
             if (A[i] > 0 && A[i] <= n)
                present[A[i]] = true;
         }

         for (int i = 1; i <= n; i++){
            if (!present[i]){
                return i;
            }
         }
           
        return n + 1;    

    }
	
	public static int solution1(int[] A) {
		// write your code in Java SE 8
		// Arrays.sort(A);
		Set<Integer> hset = new HashSet<Integer>();
		for (Integer integer : A) {
			hset.add(integer);
		}
		
		hset.forEach(val -> System.out.println(" "+ val));
		
		int size = A.length + 1;
		System.out.println("size" + size);
		
		IntStream.range(1, size ).filter(val -> hset.contains(val) == false).forEach(System.out::println);
		//int a = IntStream.range(1, A.length).filter(val -> hset.contains(val) == false).min().getAsInt();
		int a = (int)IntStream.range(1, A.length).filter(val -> hset.contains(val) == false).count();
		System.out.println("a " + a);
		//return IntStream.range(1, A.length).filter(val -> hset.contains(val) == false).findFirst().getAsInt();
		//int b = IntStream.range(1, size ).filter(val -> hset.contains(val) == false).findFirst().orElse(0);
		int b = IntStream.range(1, A.length).filter(val -> hset.contains(val) == false).min().getAsInt();
		System.out.println("b " + b);
		return 1;
		
	}

	
}
