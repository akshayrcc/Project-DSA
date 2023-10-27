package com.akshayram.oa;

public class SnakeToCamelCase {
    static String convertSnakeToCamelCase(String src) {
        StringBuilder sb = new StringBuilder();
        String[] words = src.split("\\s+");
        boolean flagFirstWord = true;
        for (String word : words) {
            StringBuilder sbWord = new StringBuilder();
            if(!flagFirstWord){
                sb.append(" ");
            }
//            System.out.println(" "+ word);
            int i=0;
            //bypassing prepend underscores
            while(i<word.length() && word.charAt(i) == '_'){
                sbWord.append(word.charAt(i));
                i++;
            }
            //at first char now, bypassing first word until underscore
            while(i<word.length() && word.charAt(i) != '_'){
                sbWord.append(word.charAt(i));
                i++;
            }
            while(i<word.length()){
                //passing over the next underscore
                while(i<word.length() && word.charAt(i) == '_'){
                    i++;
                }
                //if actual char exists
                if(i<word.length() && word.charAt(i)!='_'){
                    //capitalizing this first char of new found word after underscore
                    char capChar = Character.toUpperCase(word.charAt(i));
                    sbWord.append(capChar);
                    i++;
                    //skip next letters in the word
                    while(i<word.length() && word.charAt(i) != '_'){
                        sbWord.append(word.charAt(i));
                        i++;
                    }
                }
            }
            // System.out.println(startIndex +" "+ word.length());
            if (word.endsWith("_")) {
                int underscoreCount = 0;
                int x = word.length()-1;
                while(word.charAt(x)=='_'){
                    underscoreCount++;
                    x--;
                }
                while(underscoreCount > 0){
                    sbWord.append('_'); // Preserve trailing underscore
                    underscoreCount--;
                }
            }
            sb.append(sbWord);
            if(flagFirstWord){
                flagFirstWord = false;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test case 1: Basic snake case conversion
        String snakeCase1 = "___abcd__GHIJ__Klmn___x___yyyyyZ___";
        String camelCase1 = convertSnakeToCamelCase(snakeCase1);
        System.out.println("ANS:" + camelCase1); // Output: "thisIsSnakeCaseExample"

        // Test case 2: Snake case with single characters
        String snakeCase2 = "a_b_c_d";
        String camelCase2 = convertSnakeToCamelCase(snakeCase2);
        System.out.println(camelCase2); // Output: "aBCD"

        // Test case 3: Snake case with numbers
        String snakeCase3 = "test_case_123";
        String camelCase3 = convertSnakeToCamelCase(snakeCase3);
        System.out.println(camelCase3); // Output: "testCase123"

        // Test case 4: Empty string
        String snakeCase4 = "";
        String camelCase4 = convertSnakeToCamelCase(snakeCase4);
        System.out.println(camelCase4); // Output: ""

        // Test case 5: Snake case with leading and trailing underscores
        String snakeCase5 = "_start_and_end_";
        String camelCase5 = convertSnakeToCamelCase(snakeCase5);
        System.out.println(camelCase5); // Output: "_startAndEnd_"
    }

}
