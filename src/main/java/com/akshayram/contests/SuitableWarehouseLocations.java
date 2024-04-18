package com.akshayram.contests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* The world is represented by a number line from -109 to 109.
* There are n delivery centers, the ith one at location center[i].
* A location x is called a suitable location for a warehouse if it is possible to bring all the products to that point by traveling a distance of no more than d.
* At any one time, products can be brought from one delivery center and placed at point x.
* Given the positions of n delivery centers, calculate the number of suitable locations in the world.
* That is, calculate the number of points x on the number line (-10^9 ≤ x ≤ 10^9)
* where the travel distance required to bring all the products to that point is less than or equal to d.
*
Note: The distance between point x and center[i] is |x - center[i]|, their absolute difference.
*
* Example 1:

Input: center = [-2, 1, 0], d = 8

Output: 3
* */
public class SuitableWarehouseLocations {

    public static void main(String[] args) {
        SuitableWarehouseLocations swl = new SuitableWarehouseLocations();
        int[] center = {-2, 1, 0};
        int d = 8;
        System.out.println(Arrays.toString(swl.findSuitableLocations(center, d)));
    }

    int[] findSuitableLocations(int[] center, int d) {
        Set<Integer> suitablePoints = new HashSet<>();
        int count = 0;
        //find the weighted center of the values in the array
        int sum = 0;
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int i : center) {
            sum += center[i];
            minVal = Math.min(minVal, center[i]);
            maxVal = Math.max(maxVal, center[i]);
        }

        int centerPoint = sum / center.length;
        System.out.println("Sum: " + sum);


        while (centerPoint > minVal ) {
            int val1 = getDistanceValue(center, centerPoint);
            if (val1 <= d) {
                count++;
                suitablePoints.add(centerPoint);
            } else {
                break;
            }
        }

        while (centerPoint < maxVal) {
            int val2 = getDistanceValue(center, centerPoint);
            if (val2 <= d) {
                count++;
                suitablePoints.add(centerPoint);
            } else {
                break;
            }
        }

        return suitablePoints.stream().mapToInt(i -> i).toArray();
    }

    int getDistanceValue(int[] center, int fromPoint) {
        int dist = 0;
        for (int i : center) {
            dist += 2 * Math.abs(fromPoint - center[i]);
        }
        return dist;
    }
}
