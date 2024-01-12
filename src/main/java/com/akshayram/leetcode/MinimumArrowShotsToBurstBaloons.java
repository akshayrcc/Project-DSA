package com.akshayram.leetcode;

import java.util.Arrays;   

class MinimumArrowShotsToBurstBaloons{
    public int findMinArrowShots(int[][] points) {
        
        //example
        //after sorting
        //[2,4] [1,4] [1,5],[3,6],[7,10]
        //if we shot at 4, first 4 boloons will burst
        //if we shot at 7, last 1 baloon will burst
        //min 2 shots needed 

        //Program
        //sort array based on ending positions
        Arrays.sort(points,(a,b)->a[1]-b[1]);
        int arrowPosition=points[0][1];
         int arrowShotsCount=1;
        for(int i=1;i<points.length;i++)
        {
            //chk if we can burst more ballons using current arrow position
            //if current arrow position is covering start position of baloon, then we can burst that baloon          
            if(arrowPosition>=points[i][0])
            {
                continue;
            }
            //if not
            arrowShotsCount++;
            //assign new position to burst baloon
            arrowPosition=points[i][1];
        }
          return arrowShotsCount;
    }
}