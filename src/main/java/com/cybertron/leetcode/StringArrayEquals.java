package com.cybertron.leetcode;


public class StringArrayEquals {
	
	public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        String newString1 = "", newString2 = "";
        	for(int i=0;i<word1.length;i++){
                newString1 = newString1.concat(word1[i]);
            }
        
        	for(int j=0;j<word2.length;j++){
                newString2 = newString2.concat(word2[j]);
            }
        
        if(newString1.equals(newString2)){
            return true;
        }
        else{
            return false;
        }
    }
	
	public static void main(String[] args) {
		String[] word1  = new String[] {"ab", "c"};//{"abc", "d", "defg"};
		String[] word2 = new String[] {"a", "bc"};//{"abcddefg"}; //["abcddefg"];
		String[] word3  = new String[] {"abc", "d", "defg"};
		String[] word4 = new String[] {"abcddefg"}; //["abcddefg"];
		String[] word5 = new String[] {"a", "cb"};
		String[] word6 = new String[] {"ab", "c"};
		
		System.out.println(arrayStringsAreEqual(word5,word6));
		//System.out.println(Arrays.toString(word1).equals(Arrays.toString(word2)));
		
	}

}
