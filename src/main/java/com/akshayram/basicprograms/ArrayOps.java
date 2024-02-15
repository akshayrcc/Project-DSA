package com.akshayram.basicprograms;

import java.util.*;

class ArrayOps {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int val : nums1) {
            m.put(val, m.getOrDefault(val, 0) + 1);
        }
        int k = 0;
        for (int val : nums2) {
            if (m.getOrDefault(val, 0) > 0) {
                nums1[k++] = val;
                m.put(val, m.get(val) - 1);
            }
        }
        int[] ans = new int[k];
        ans = Arrays.copyOf(nums1, k);
        return ans;
    }

    public int[] intersect_2(int[] nums1, int[] nums2) {
        int[] freq = new int[1001];
        // Setting the freq of each number in nums1
        for (int i : nums1) {
            freq[i]++;
        }
        int[] res = new int[1001];
        int k = 0;
        for (int j : nums2) {
            if (freq[j] > 0) {
                freq[j]--;
                res[k++] = j;
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // compare if two string arrays are same string pieces
    // TC: O(n * k) SC: O(1)
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int len1 = word1.length;
        int len2 = word2.length;
        int i = 0, j = 0, char_i = 0, char_j = 0, w1count = 0, w2count = 0;
        String w1;
        String w2;
        while (i < len1 && j < len2) {
            w1 = word1[i];
            w2 = word2[j];

            if (char_i >= w1.length()) {
                char_i = 0;
                i++;
                continue;
            }
            if (char_j >= w2.length()) {
                char_j = 0;
                j++;
                continue;
            }

            char c1 = w1.charAt(char_i);
            char c2 = w2.charAt(char_j);

            char_i++;
            char_j++;

            w1count++;
            w2count++;

            if (c1 != c2) {
                return false;
            }
        }

        if (i < len1) {
            w1 = word1[i];
            if (char_i < w1.length()) w1count++;
        }

        if (j < len2) {
            w2 = word2[j];
            if (char_j < w2.length()) w2count++;
        }

        if (w1count != w2count) return false;
        return true;
    }

    public int countCharacters(String[] words, String chars) {
        int sum = 0;
        // create a freqMap for chars string
        //    Map<Character, Integer> freqMap = new HashMap<>();
        //    for (int i = 0; i < chars.length(); i++) {
        //      if (freqMap.containsKey(chars.charAt(i))) {
        //        freqMap.put(chars.charAt(i), freqMap.get(chars.charAt(i)) + 1);
        //      } else {
        //        freqMap.put(chars.charAt(i), 1);
        //      }
        //    }
        boolean goodWord = false;
        int[] freqArray = new int[26];
        for (char ch : chars.toCharArray()) {
            freqArray[ch - 'a']++;
        }

        for (String word : words) {
            goodWord = true;
            int[] wordFreqArray = new int[26];
            for (char ch1 : word.toCharArray()) {
                wordFreqArray[ch1 - 'a']++;
                if (wordFreqArray[ch1 - 'a'] > freqArray[ch1 - 'a']) {
                    goodWord = false;
                    break;
                }
            }
            if (goodWord) sum += word.length();
        }
        return sum;
    }

    // TC: O(n) SC:(1)
    public static String largestGoodInteger(String num) {
        char prev = ' ';
        int sameCount = 1;
        int maxGoodInt = -1;
        for (char ch : num.toCharArray()) {
            if (ch == prev) {
                sameCount++;
            } else {
                sameCount = 1;
            }
            if (sameCount == 3) {
                int val = Character.getNumericValue(ch);
                if (val > maxGoodInt) {
                    maxGoodInt = val;
                }
                sameCount = 1;
            }
            prev = ch;
        }
        if (maxGoodInt != -1) {
            return maxGoodInt + "" + maxGoodInt + "" + maxGoodInt;
        }
        return "";
    }

    // TC: O(log n) SC: O(1)
    public static int numberOfMatches(int n) {
        int totalMatches = 0;
        while (n > 1) {
            if (n % 2 == 0) { // even teams
                totalMatches += (n / 2);
                n = n / 2;
            } else { // odd teams
                totalMatches += (n - 1) / 2;
                n = ((n - 1) / 2) + 1;
            }
        }
        return totalMatches;
    }

    // TC: O(n) SC: O(1)
    public static int totalMoney(int n) {
        // first full week = 28
        // every subsequent week adds 7 more.
        // Hence, nth full week = firstFullWeek + (n-1) * 7
        int fullWeeksCount = n / 7;
        int daysRemaining = n % 7;
        int money = 0;
        for (int i = 1; i <= fullWeeksCount; i++) {
            money += (28 + (i - 1) * 7);
        }
        int weekStartVal = fullWeeksCount + 1;
        for (int j = 0; j < daysRemaining; j++) {
            money += (weekStartVal + j);
        }
        return money;
    }

    // TC: O(n) SC: O(1)
    public static String largestOddNumber(String num) {
        String ans = "";
        int n = num.length() - 1;
        // skip all even nums from the last
        while (n >= 0 && Integer.parseInt(String.valueOf(num.charAt(n))) % 2 == 0) {
            n--;
        }
        // now n is point at odd num from the rear end
        if (n >= 0) {
            ans = num.substring(0, n + 1);
        }
        return ans;
    }

    // TC: O(1) SC: O(1)
    public static int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        money -= children;
        int eights = money / 7;
        int remains = money % 7;
        if (remains == 3 && eights == children - 1) return eights - 1;
        if ((eights == children && remains != 0)) return eights - 1;
        if (eights > children) return children - 1;
        return eights;
    }

    public static int findSpecialInteger(int[] arr) {
        int quarterPerCent = arr.length / 4;
        int count = 1;
        int prev = arr[0];
        int curr = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = arr[i];
            if (prev == curr) {
                count++;
            } else {
                // count reset to one for new element
                count = 1;
            }
            prev = curr;
            if (count > quarterPerCent) {
                return curr;
            }
        }
        return -1;
    }

    static int globalPos = 0;
    static int globalSpeed = 0;
    static int fleetCount = 0;

    public static int carFleet(int target, int[] position, int[] speed) {
        if (position.length != speed.length) return 0;
        if (position.length == 1) return 1;
        // insert in ascending order
        TreeMap<Integer, Integer> sorted = new TreeMap<>();
        for (int i = 0; i < position.length; i++) {
            sorted.put(position[i], speed[i]);
        }
        int mapSize = sorted.size();
        int count = 0;
        int prevPos = 0;
        int prevSpeed = 0;
        boolean firstPass = true;
        for (Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
            count++;
            int currPos = entry.getKey();
            int currSpeed = entry.getValue();
            // check if current pair is in fleet
            if (!firstPass) {
                if (isInFleet(prevPos, prevSpeed, currPos, currSpeed, target)) {
                    prevPos = globalPos;
                    prevSpeed = globalSpeed;
                    if (count == mapSize) fleetCount++;
                } else {
                    // current car is not in fleet with next one. Its the solo fleet
                    fleetCount++;
                    prevPos = currPos;
                    prevSpeed = currSpeed;
                }
            }
            if (firstPass) {
                prevPos = currPos;
                prevSpeed = currSpeed;
            }
            firstPass = false;
        }
        return fleetCount;
    }

    static boolean isInFleet(int pos1, int speed1, int pos2, int speed2, int target) {
        while (pos1 <= target && pos2 <= target) {
            if (pos1 == pos2) {
                globalPos = pos1;
                globalSpeed = Math.min(speed1, speed2);
                return true;
            }
            //      if(pos1 >= (pos2 + speed2)){
            //        pos2 += speed2;
            //      } else if(pos2 >= (pos1 + speed1)){
            //        pos1 += speed1;
            //      } else {
            //        pos2 += speed2;
            //        pos1 += speed1;
            //      }
            pos2 += speed2;
            pos1 += speed1;
        }
        return false;
    }

    // TC: O(n) SC:O(n)
    public static int minOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int minOpsReq = 0;
        for (Integer value : freqMap.values()) {
            if (value == 1) {
                return -1;
            } else if (value % 3 == 0) {
                minOpsReq += value / 3;
            } else if (value % 3 == 1 || value % 3 == 2) {
                minOpsReq += value / 3;
                minOpsReq++;
            } else if (value % 2 == 0) {
                minOpsReq += value / 2;
            } else {
                return -1;
            }
        }
        return minOpsReq;
    }

    public static int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return dimensions[0][0] * dimensions[0][1];
        }
        double maxArea = 0;
        double largestDiag = 0;
        for (int i = 0; i < n; i++) {
            int len = dimensions[i][0];
            int wid = dimensions[i][1];
            double diag = Math.sqrt((len * len) + (wid * wid));
            if (diag > largestDiag) {
                largestDiag = diag;
                double currArea = len * wid;
                maxArea = currArea;
            } else if (diag == largestDiag) {
                largestDiag = diag;
                double currArea = len * wid;
                maxArea = Math.max(maxArea, currArea);
            }
        }
        return (int) maxArea;
    }

    public static int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        int moves = 0;
        // check if rook is inline with Queen
        if (a == e || b == f) {
            // check if no obstacle in way
            if (b == d && d == f) { // all horizontal same
                if ((a < c && c < e) || (a > c && c > e)) {
                    // obstacle present
                    return 2;
                } else {
                    return 1;
                }
            }
            if (a == c && c == e) { // all vertical same
                if ((b < d && d < f) || (b > d && d > f)) {
                    // obstacle present
                    return 2;
                } else {
                    return 1;
                }
            }
            // all diff diag
            return 1;
        } else {
            // rook is not in line
            // to check if no obstacle in way
            moves = 2;
        }

        // check if Bishop is inline with Queen
        // check odd-even  black-white match first
        // boolean blackBox = (c + d) % 2 == 1;
        if ((c + d) % 2 == (e + f) % 2) { // allowed case
            // check if one move possible
            if (Math.abs(c - e) == Math.abs(d - f)) {
                // check if no obstacle
                if (((a + b) % 2 == (c + d) % 2)) { // if rook the same color
                    if (Math.abs(c - a) == Math.abs(d - b)) { // if rook on kill line
                        if (((c < a && a < e) || (d < b && b < f)) || ((c > a && a > e) || (d > b && b > f))) {
                            // obstacle present
                            // return 1;
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            } else {
                // if obstacle
                moves = 2;
            }
        }
        return moves;
    }

    static Map<Integer, Integer> counts = new HashMap<>(); // Store counts of numbers
    static Set<Integer> visited = new HashSet<>();
    static Set<Integer> ansSet = new HashSet<>();
    static int maxSetSize = 1;

    public static int maximumLength(int[] nums) {
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        visited.add(1);

        for (int num : nums) {

        }


        int oneCount = counts.getOrDefault(1, 0);
        if (oneCount >= 2) maxSetSize -= 2;
        if (maxSetSize <= 0) maxSetSize = 1;

        return maxSetSize;
    }

    static int helper(int[] nums, int num) {
        //if(numIndex >= nums.length)  return 0;
        //int num = nums[numIndex];

        if (!visited.contains(num)) {
            int squ = num * num;
            //check if squ exists.
            if (counts.containsKey(squ)) {
                int occur = counts.get(num);
                if (occur >= 2) {
                    ansSet.add(num);
                    ansSet.add(squ);
                    maxSetSize = Math.max(maxSetSize, (ansSet.size() * 2) - 1);
                    helper(nums, num);
                }
                visited.add(num);
            } else {
                visited.add(num);
            }
        }
        return 0;
    }

    public static long flowerGame(int n, int m) {
        long count = 0;
        // for (int x = 1; x <= n; x++) {
        //     for (int y = 1; y <= m; y++) {
        //         if ((x + y) % 2 == 1) {
        //             count += Math.min(x, y);
        //         }
        //     }
        // }
        // for (int x = 1; x <= n; x++) {
        //     if (((x + m) % 2 == 1) && !(x % 2 == 1 && m % 2 == 1)) {
        //         count += Math.min(m, n - x + 1);
        //     }
        // }
        long firstEve = 1;
        long firstOdd = 1;
        long secEve = 1;
        long secOdd = 1;

        if (n % 2 == 0) {
            //n is even
            firstEve = n / 2;
            firstOdd = n / 2;
        } else {
            //n is odd
            firstEve = n / 2;
            firstOdd = n / 2 + 1;
        }

        if (m % 2 == 0) {
            //m is even
            secEve = m / 2;
            secOdd = m / 2;
        } else {
            //m is odd
            secEve = m / 2;
            secOdd = m / 2 + 1;
        }

        return (firstEve * secOdd) + (secEve * firstOdd);
    }

    public static long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = Integer.MIN_VALUE;

        Map<Integer, Integer> mapIndex = new HashMap<>(); //abs val, index
        mapIndex.put(nums[0], 0);

        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];


        for (int i = 1; i < n; i++) {
            mapIndex.put(Math.abs(nums[i]), i);
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        for (int i = 0; i < n - 1; i++) {
            int index = -1;
            int target = k + Math.abs(nums[i]);
            if (mapIndex.containsKey(target)) {
                index = mapIndex.get(target);
            }
            long sum;
            if (index != -1) {
                if (i != 0) {
                    sum = prefixSum[index] - prefixSum[i - 1];
                } else {
                    sum = prefixSum[index];
                }

                maxSum = Math.max(maxSum, sum);
            }
            //2nd
//            int target2 = k + Math.abs(nums[i]);
//            if(mapIndex.containsKey(target2)){
//                index = mapIndex.get(target2);
//            }
//
//            if(i!=0){
//                sum = prefixSum[index] - prefixSum[i-1];
//            } else {
//                sum = prefixSum[index];
//            }
//            maxSum = Math.max(maxSum, sum);

        }
        if (maxSum == Integer.MIN_VALUE) maxSum = 0;
        return maxSum;
    }

    public static int majorityElement(int[] nums) {
        int n = nums.length;
        int count = 0;
        int currentWinner = 0;
        for(int i=0;i<n;i++){
            int curr = nums[i];
            if(count == 0){
                currentWinner = curr;
            }
            if(curr == currentWinner){
                count++;
            } else {
                count--;
            }
        }
        return currentWinner;
    }

    public static boolean isValid(String s) {
        Map<Character, Character> hmap = new HashMap<>();
        hmap.put('(',')');
        hmap.put('{','}');
        hmap.put('[',']');
        Stack<Character> st = new Stack<>();
        for(Character ch: s.toCharArray()){
            if(hmap.keySet().contains(ch)){ //isKey
                st.push(ch);
                System.out.println(st);
            } else {
                if(st.isEmpty()) return false;
                Character counter = hmap.get(st.pop());

                if(counter!= ch) return false;
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        //    String num = "014455"; // "42352338";//"2300019";//"6777133339";
        //    String result = largestGoodInteger(num);
        //    System.out.println("Largest Good Integer: " + result);
        //    int result = distMoney(20, 3);
        //    int [] arr = new int[]{1,2,2,6,6,6,6,7,10};
        //    int result = findSpecialInteger(arr);
        //    int target = 12;
        //    int [] pos = new int[]{10,8,0,5,3};
        //    int [] speed = new int[]{2,4,1,1,3};
        //    int target = 10;
        //    int[] pos = new int[] {0, 4, 2};
        //    int[] speed = new int[] {2, 1, 3};
//        int target = 20;
//        int[] pos = new int[]{6, 2, 17};
//        int[] speed = new int[]{3, 9, 2};
        //    int target = 10;
        //    int [] pos = new int[]{8,3,7,4,6,5};
        //    int [] speed = new int[]{4,4,4,4,4,4};

        //    int result = carFleet(target, pos, speed);

        //    int[] nums = new int[] {2,3,3,2,2,4,2,3,4}; // 4
        //    int[] nums =
        //        new int[] {14, 12, 14, 14, 12, 14, 14, 12, 12, 12, 12, 14, 14, 12, 14, 14, 14, 12,
        // 12}; //7
        //    new int[] {13,7,13,7,13,7,13,13,7};

        //    int result = minOperations(nums);

        //    int[][] nums = new int[][]{
        ////            {2,6},{5,1},{3,10},{8,4} // 30
        //            {6,5},{8,6},{2,10},{8,1}, {9,2}, {3,5}, {3,5} // 20
        //    };
//        int result = minMovesToCaptureTheQueen(2, 4, 2, 8, 8, 2);
//        int result = maximumLength(new int[]{48841, 358801, 28561, 18974736, 4356, 221, 358801, 599, 13, 4356, 66, 48841, 28561, 815730721, 13, 815730721, 18974736, 66, 169, 599, 169, 221}); // 7
//        long result = flowerGame(7, 4);
//        System.out.println("Result: " + result);
//        long result = maximumSubarraySum(new int[]{-1, -2, -3, -4}, 2); //[1,2,3,4,5,6], k = 1
//        long result = majorityElement(new int[]{1,1,1,3,3,2,2,2,1,1,1,1}); //[1,2,3,4,5,6], k = 1
        boolean result = isValid("["); //[1,2,3,4,5,6], k = 1
        System.out.println("Result: " + result);
    }
}
