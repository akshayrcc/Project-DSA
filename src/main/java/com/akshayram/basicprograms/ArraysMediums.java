package com.akshayram.basicprograms;

public class ArraysMediums {

    //3091. Apply Operations to Make Sum of Array Greater Than or Equal to k
    public int minOperations(int k) {
        if (k == 1) return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < k; i++) {
            int steps = 0;
            int j = 2;
            int number = i;
            while (number < k) {
                number = i * j;
                j++;
                steps++;
            }
            steps += i - 1;
            ans = Math.min(ans, steps);
        }
        return ans;
    }

    public int minOperations1(int k) {
        int ans = Integer.MAX_VALUE;
        int finall = 0;
        for (int i = 1; i <= k / 2; i++) {
            // i=1--> 1+1+1+1+1+1+1+1+1+1+1 --> To get first 1 -->(1-1=0 operation) + leftover 10 more 1's --> final=10
            // i=2--> 2+2+2+2+2+2+2 --> To get first 2 -->(2-1=1 operation) + leftover 5 more 2's --> final=1+5=6
            // i=3--> 3+3+3+3 --> To get first 3 -->(3-1=2 operations) + leftover 3 more 3's --> final=2+5=5
            // i=4--> 4+4+4 --> To get first 4 -->(4-1=3 operations) + leftover 2 more 4's --> final=3+2=5
            // i=5--> 5+5+5 --> To get first 5 -->(5-1=4 operations) + leftover 2 more 5 --> final=4+2=6
            // i=6--> 6+6 --> To get first 6 -->(6-1=5 operations) + leftover 1 more 5 --> final=5+1=6
            // ....
            int sum = 0;
            sum += i - 1; // (to get first digit)
            sum += k / i - 1; // (leftover count)
            if (k % i > 0)
                sum += 1;
            ans = Math.min(sum, ans);
            if (ans == sum)
                finall = ans;
            sum = 0;
        }
        return finall;
    }

    public int minOperations2(int k) {
        int a = (int) Math.sqrt(k);
        return a + (k - 1) / a - 1;
    }

    // 41. First Missing Positive
    public int firstMissingPositive(int[] nums) {
        //TC: O(n) SC: O(1)
        int N = nums.length;
        int i = 0;
        //now placing all vals to their own index... val-1
        while (i < N) {
            int correctIndex = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= N && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        //now checking for first mismatch from begining, thats the missing first positive
        for (int j = 0; j < N; j++) {
            if (nums[j] != j + 1) return j + 1;
        }

        return N + 1;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    //713. Subarray Product Less Than K
    //TC: O(n) SC: O(1)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int left = 0, right = 0, product = 1, count = 0;
        int N = nums.length;
        while (right < N) {
            product *= nums[right]; //expanding from right
            while (product >= k) product /= nums[left++]; //shriking from left
            count += 1 + (right - left); //adding all subarrays from the active window
            right++;
        }
        return count;
    }


}
