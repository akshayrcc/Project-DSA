package com.cybertron.basicprograms;

import java.util.Random;
import java.util.Scanner;

public class Mulcheck {
      public static void main(String[]args){
    	  System.out.println("Welcome To The Game Of Multiplier");
    	  System.out.println("Lets Begin");
    	  Scanner scanner = new Scanner(System.in);
    	  boolean status = true;
    	  System.out.println("Enter the Number to be Multiple of");
    	  int a = scanner.nextInt();
    	  int[] arr=new int[11];
    	  for(int i=0;i<=10;i++){
        	  arr[i]=0;
          }
    	  while(status){
	    	  int counter;
	          Random rnum =new Random();
	          counter = rnum.nextInt(10);
	          int x = counter+1;
	     
	          if(arr[x]==0){
		    	  System.out.println(a+"  multiplied by "+x);
		    	  int ans = scanner.nextInt();
		    	  int result =a*x;
		    	  arr[x] = 1;
		    	  if(ans==result){
		    		  System.out.println("correct");
		    		 
		    	  }  	  else    	  {
		    		  System.out.println("incorrect");
		    		  System.out.println("Game is Over");
		    		  status= false;
		    	  }
		    	  
	    	  }
	          
    	  }
    	  System.out.println("You Won The Game ");
      }
      
}