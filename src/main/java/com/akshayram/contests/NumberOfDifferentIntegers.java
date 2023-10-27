package com.akshayram.contests;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumberOfDifferentIntegers {

	public static void main(String[] args) {

		System.out.print("Start");

//		int ret = numDifferentIntegers("a123bc34d8ef34");
//		int ret = numDifferentIntegers("167278959591294");
//		System.out.print("Ans: " + ret + "\n");

		System.out.print("End");
	}

	public String evaluate(String s, List<List<String>> knowledge) {
        char[] inputArr = s.toCharArray();
        Map<String, String> map = new HashMap<>();
        for(List<String> know:knowledge)  {
            map.put(know.get(0),know.get(1));
        }
        
         System.out.println("MAP:");
         map.forEach((K, V) -> {
             System.out.println(K+" "+V);
         });
        
        
        StringBuilder sb=new StringBuilder("");
        StringBuilder ans=new StringBuilder("");
        String val;
         for(int i=0;i<inputArr.length;i++){
             if(inputArr[i] != '(' && inputArr[i] != ')'){
                 ans.append(inputArr[i]);
             }
             if (inputArr[i] == '('){
            	 i++;
                 while(inputArr[i] != ')'){
                     sb.append(Character.toString(inputArr[i]));
                     i++;
                 }
                 if(map.containsKey(sb.toString())){
                	 val = map.get(sb.toString());
                 } else {
                	 val = "?";
                 }
                 ans.append(val);
                 sb=new StringBuilder("");
             }
         }
        return ans.toString();
    }

	public static int numDifferentIntegers(String word) {
		char[] wordArray = word.toCharArray();
		Set<BigInteger> chSet = new HashSet<>();

		int count = 0;
		for (int i = 0; i < wordArray.length; i++) {
			if ((int) wordArray[i] >= 48 && (int) wordArray[i] <= 57) {
				count++;
			} else {
				if (count > 0) {
					String temp = word.substring(i - count, i);
					chSet.add(new BigInteger(temp));
					count = 0;
				}
			}
		}

		if (count > 0) {
			String temp = word.substring(wordArray.length - count, wordArray.length);
			chSet.add(new BigInteger(temp));
			count = 0;
		}

		System.out.println("Set");
		Iterator<BigInteger> i = chSet.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

		return chSet.size();
	}
}
