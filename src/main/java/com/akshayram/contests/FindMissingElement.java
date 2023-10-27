package com.akshayram.contests;

public class FindMissingElement {

    //TC: O(logN )
    //SC: O(1)

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 4, 5, 6, 7, 8};  //ans is 3
        int[] arr2 = new int[]{1, 2, 3, 4, 6, 7, 8, 9};  //ans is 5
        int[] arr3 = new int[]{3, 4, 6, 7, 8, 9};  //ans is 5
        int[] arr4 = new int[]{6, 7, 8, 10, 11, 12};  //ans is 9
        int[] arr5 = new int[]{5, 6, 7, 8, 9, 10, 12};  //ans is 9
        int missingElement = findingMissingElement(arr4);
        System.out.println("Missing Element is " + missingElement);
    }

    private static int findingMissingElement(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is the pivot
            if (mid > 0 && arr[mid] != arr[mid + 1] - 1) {
                // Elements are not consecutive
                return arr[mid] + 1; // Returning the missing element
            }

            if ((arr[mid] - arr[low]) != (mid - low)) {
                // Missing element is in the left half of the array
                high = mid - 1;
            } else {
                // Missing element is in the right half of the array
                low = mid + 1;
            }
        }

        // No missing element found
        return -1;
    }
}
