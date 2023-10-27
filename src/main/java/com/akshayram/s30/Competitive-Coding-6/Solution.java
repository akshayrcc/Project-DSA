class Solution {

//    Solution 1: beautiful-arrangement
//    TC: O(k) where k is the number of valid combinations
//    SC: O(n)

    int beutifulArrangements = 0;

    public int countArrangement(int n) {
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            nums[i] = i;
        }
        dfs(nums, 1);
        return beutifulArrangements;
    }

    private void dfs(int[] nums, int index) {
        //base
        if (nums.length == index) {
            beutifulArrangements++;
            return;
        }
        for (int j = index; j < nums.length; j++) {
            //condition
            if (nums[j] % index == 0 || index % nums[j] == 0) {
                swap(nums, index, j);
                //recurse
                dfs(nums, index + 1);
                //backtrack
                swap(nums, j, index);
            }
        }
    }

    private void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

//    Solution 2: logger-rate-limiter
//    TC: O(1)
//    SC: O(n)

    Map<String, Integer> logMap = new HashMap<>(); // msg : lst print timestamp
    int limiter = 10;

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!logMap.containsKey(message)) {
            logMap.put(message, timestamp);
            return true;
        } else {
            if (timestamp - logMap.get(message) >= limiter) {
                logMap.put(message, timestamp);
                return true;
            }
        }
        return false;
    }
}