package com.akshayram.basicprograms;

import java.util.*;

public class MostFreqIds {

    //TC: O(n log n) SC: O(n)
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Map<Long, Long> hm = new HashMap<>();

        //max heap of type: long[]{num, freq}
        PriorityQueue<long[]> pq = new PriorityQueue<>((long[] a, long[] b) -> {
            return Long.compare(b[1], a[1]);
        });
        int n = nums.length;
        long[] result = new long[n];

        for (int i = 0; i < n; i++) {
            long num = (long) nums[i];
            long count = (long) freq[i];

            hm.put(num, hm.getOrDefault(num, 0L) + count);
            pq.add(new long[]{num, hm.get(num)});

            while (pq.peek()[1] != hm.get(pq.peek()[0]))
                pq.poll();

            result[i] = pq.peek()[1];
        }

        return result;
    }

    public long[] mostFrequentIDs2(int[] nums, int[] freq) {
        TreeMap<Long, Integer> cnt = new TreeMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) set.add(i);
        cnt.put(0L, set.size());
        long[] c = new long[100001];
        long[] ans = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long a = c[nums[i]];
            c[nums[i]] += freq[i];
            long b = c[nums[i]];
            if (cnt.get(a) == 1) cnt.remove(a);
            else cnt.put(a, cnt.get(a) - 1);
            if (cnt.get(b) == null) cnt.put(b, 1);
            else cnt.put(b, cnt.get(b) + 1);
            ans[i] = cnt.lastKey();
        }
        return ans;
    }

    public long[] mostFrequentIDs3(int[] nums, int[] freq) {
        Map<Integer, long[]> map = new HashMap<>();
        TreeSet<long[]> set = new TreeSet<>((a, b) -> a[1] == b[1] ? Long.compare(a[0], b[0]) : Long.compare(b[1], a[1]));
        long[] result = new long[nums.length];
        long[] cur;
        for (int i = 0; i < nums.length; ++i) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new long[]{nums[i], 0});
            }
            cur = map.get(nums[i]);
            set.remove(cur);
            cur[1] += freq[i];
            if (cur[1] != 0) {
                set.add(cur);
            }
            if (set.isEmpty()) continue;
            cur = set.first();
            result[i] = cur[1];
        }
        return result;
    }


}