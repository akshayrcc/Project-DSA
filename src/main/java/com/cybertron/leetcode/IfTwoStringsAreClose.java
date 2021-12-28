package com.cybertron.leetcode;

import java.util.Arrays;

public class IfTwoStringsAreClose {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean closeStrings1(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < c1.length; i++) {
            cnt1[c1[i]-97]++;
            cnt2[c2[i]-97]++;
        }
        for (int i = 0; i < 26; i++) {
            if ((cnt1[i] == 0) != (cnt2[i] == 0)) return false;
        }
        Arrays.sort(cnt1);
        Arrays.sort(cnt2);
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) return false;
        }
        return true;
    }
public boolean closeStrings2(String word1, String word2) {
        
        int[] count1=new int[27], count2=new int[27];
        for(char x : word1.toCharArray())
            count1[x-'a']++;
        
        for(char x: word2.toCharArray())
            count2[x-'a']++;
        
        int res=0;
        
        for(int i=0;i<27;i++){
            if((count1[i]==0 && count2[i]!=0) || (count2[i]==0 && count1[i]!=0))
                return false;
            res^=count1[i];
            res^=count2[i];
        }
              
        return res==0;
            
    }
}
