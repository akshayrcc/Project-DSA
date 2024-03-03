package com.akshayram.contests;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

// Ramp 2023 Coding Assessment
public class ParsingData {
    public static void main(String[] args) {
        String jsonData = """
                [
                  {
                    "id": "1",
                    "agent": "Radulf Katlego",
                    "unit": "#3",
                    "description": "This luxurious studio apartment is in the heart of downtown.",
                    "num_bedrooms": 1
                  },
                  {
                    "id": "2",
                    "agent": "Kelemen Konrad",
                    "unit": "#36",
                    "description": "We have a 1-bedroom available on the third floor.",
                    "num_bedrooms": 1
                  },
                  {
                    "id": "3",
                    "agent": "Ton Jett",
                    "unit": "#12",
                    "description": "Beautiful 1-bedroom apartment with nearby yoga studio.",
                    "num_bedrooms": 1
                  },
                  {
                    "id": "4",
                    "agent": "Fishel Salman",
                    "unit": "#13",
                    "description": "Beautiful studio with a nearby art studio.",
                    "num_bedrooms": 1
                  }
                ]
                """;

        int[] ansArr = solution_2(jsonData);
        System.out.println("OP: ");
//        Arrays.stream(solution_1(jsonData)).forEach(System.out::print);

        for (int i = 0; i < ansArr.length; i++) {
            System.out.print(" " + ansArr[i]);
        }
    }

    static int[] solution_1(String jsonData) {
        //parsing the jsonData into Array of Json objects
        JSONArray listings = new JSONArray(jsonData);
        int n = listings.length();
        int[] bedrooms = new int[n];
        for (int i = 0; i < n; i++) {
            //getting each JSON obj
            JSONObject obj = listings.getJSONObject(i);
            String descrr = obj.getString("description").toLowerCase();
            int numOfBeds = obj.getInt("num_bedrooms");

            if ((descrr.contains("studio") && !isPreceededBy(descrr, "studio"))
                    || descrr.contains("1-bedroom")) {
                numOfBeds = descrr.contains("studio") ? 0 : 1;
            }
            bedrooms[i] = numOfBeds;
        }
        return bedrooms;
    }

    static int[] solution_2(String jsonData) {
        List<String> listings = new ArrayList<>();
        int ind_1 = jsonData.indexOf("\"description\"");
        int ind_2 = jsonData.indexOf("\"num_bedrooms\"");
        while (jsonData.length() > 18) {
            ind_1 = jsonData.indexOf("\"description\"");
            ind_2 = jsonData.indexOf("\"num_bedrooms\"");
            String desc = jsonData.substring(ind_1 + 17, ind_2);
            listings.add(desc);
            System.out.println(ind_1 + " " + ind_2 + " " + desc);

            //mark current item done by skipping over
            jsonData = jsonData.substring(ind_2);

            // //get next indexes
            // ind_1 = jsonData.indexOf("\"description\"");
            // ind_2 = jsonData.indexOf("\"num_bedrooms\"");
        }


        List<Integer> bedrooms = new ArrayList<>();
        for (int i = 0; i < listings.size(); i++) {
            //getting each JSON obj
            int numOfBeds = 0;
            String descrr = listings.get(0).toLowerCase();

            if ((descrr.contains("studio") && !isPreceededBy(descrr, "studio"))
                    || descrr.contains("1-bedroom")) {
                numOfBeds = descrr.contains("studio") ? 0 : 1;
            }
            bedrooms.add(numOfBeds);
        }
        return bedrooms.stream().mapToInt(Integer::intValue).toArray();
    }

    //method to check if preceeded by exclude word
    static boolean isPreceededBy(String desc, String keyword) {
        String[] exclude = {"yoga", "dance", "art"};
        int keyIndex = desc.indexOf(keyword);
        if (keyIndex == -1) return false;
        for (String ex : exclude) {
            int exIndex = desc.lastIndexOf(ex, keyIndex - 1);
            if (exIndex != -1 && exIndex + ex.length() == keyIndex) return true;
        }
        return false;
    }
}
