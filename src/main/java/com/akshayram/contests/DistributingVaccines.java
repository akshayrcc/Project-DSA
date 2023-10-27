package com.akshayram.contests;

import java.io.*;
import java.util.*;


public class DistributingVaccines {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for(int t_i = 0; t_i < T; t_i++)
        {
            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());
            int K = Integer.parseInt(br.readLine().trim());
            String[] arr_A = br.readLine().split(" ");
            int[] A = new int[N];
            for(int i_A = 0; i_A < arr_A.length; i_A++)
            {
            	A[i_A] = Integer.parseInt(arr_A[i_A]);
            }

            int out_ = solve(N, M, K, A);
            System.out.println(out_);
            
         }

         wr.close();
         br.close();
    }
    static int solve(int N, int M, int K, int[] A){
       // Write your code here
       
        // if(N == 0 ){
        //     return -1;
        // }

        int sumOfA = Arrays.stream(A).sum();
        System.out.println("Sum :" + sumOfA);
        int toRemoveSum = (sumOfA - K) % M;
        System.out.println("To remove Sum :" + toRemoveSum);
        
         if(toRemoveSum < 0){
             return -1;
         }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        
        subSeqOfSum(ans, A, toRemoveSum, 0, new ArrayList<Integer>());
        
        //print subsequences of needed sum
         for(int i=0; i< ans.size(); i++){
             System.out.print(" ith row: "+ i);
             for(int j=0; j< ans.get(i).size(); j++){
                 System.out.print(ans.get(i).get(j) + " ");
             }
             System.out.println();
         }

        int minVal = Integer.MAX_VALUE;
        int minIndex=0;
        //to get min size al for alList
        for(int i=0; i<ans.size();i++){
            if(ans.get(i).size() < minVal){
                minVal = ans.get(i).size();
                minIndex = i;
            }
        }
        return ans.get(minIndex).size();    
    }

    static void subSeqOfSum(ArrayList<ArrayList<Integer>> ans, int a[], int sum, int start, ArrayList<Integer> temp){
        
        if(start > a.length || sum < 0){
            return;
        }
        if(sum==0){
            ans.add(new ArrayList<Integer>(temp));
            return;
        } else {
            for(int i = start ; i < a.length; i++){
                temp.add(a[i]);
                subSeqOfSum(ans, a, sum - a[i] , i+1 , temp );
                temp.remove(temp.size()-1);
            }
        }
    }
}