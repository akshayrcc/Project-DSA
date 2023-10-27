package com.akshayram.leetcode;


import java.util.*;

public class RansomNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        ArrayList<String> magazineList=new ArrayList<String>(Arrays.asList(magazine));
        ArrayList<String> noteList=new ArrayList<String>(Arrays.asList(note));
        
         Iterator noteListIterator = noteList.iterator();
         
         while(noteListIterator.hasNext()){
            String currentNoteWord =  String.valueOf(noteListIterator.next());
            if(! magazineList.contains(currentNoteWord)){
                System.out.println("No");
                return;
            }
            else{
                magazineList.remove(currentNoteWord);
            }
        }
        System.out.println("Yes");
        return;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        System.out.println("=>"+m);
        System.out.println("=>"+n);
        
        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
            System.out.println("=>"+magazineItem);
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
            System.out.println("=>"+noteItem);
            
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}