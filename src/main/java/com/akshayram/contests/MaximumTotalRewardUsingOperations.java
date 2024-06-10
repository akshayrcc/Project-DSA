package com.akshayram.contests;

import java.util.Arrays;
import java.util.BitSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MaximumTotalRewardUsingOperations {

    public static void main(String[] args) {
        MaximumTotalRewardUsingOperations m = new MaximumTotalRewardUsingOperations();
        System.out.println(m.maxTotalReward(new int[]{1, 2, 3, 4, 6}));
    }

    public int maxTotalReward(int[] rew) {
        // 1 2 3 4 6
        // ^     ^ ^
        TreeSet<Integer> set = new TreeSet<>();
        for (int v : rew) set.add(v);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int p = 0;
        int[] arr = new int[set.size()];
        for (int v : set) {
            map.put(v, p);
            arr[p++] = v;
        }
        int max = arr[arr.length - 1];
        int[] memo = new int[max + 1];
        Arrays.fill(memo, -1);
        return max + findMaxLt(arr, memo, max, map);
    }

    private int findMaxLt(int[] arr, int[] memo, int k, TreeMap<Integer, Integer> map) {
        if (memo[k] >= 0) {
            return memo[k];
        }
        int max = 0;
        if (arr[0] >= k) {
            return 0;
        }
        var next = map.ceilingEntry(k);
        int nextIdx = next == null ? arr.length : next.getValue();
        // System.out.printf(">>> k=%d nexIdx=%d\n", k, nextIdx);
        for (int i = 0; i < nextIdx; i++) {
            max = Math.max(max, arr[i] + findMaxLt(arr, memo, Math.min(arr[i], k - arr[i]), map));
        }
        memo[k] = max;
        return max;
    }

    /* Second Solution */

    public int maxTotalReward2(int[] rewardValues) {
        Arrays.sort(rewardValues);
        BitSet current = new BitSet();
        current.set(0);
        BitSet clone, shifted;
        int max = 0;
        for (int num : rewardValues) max = Math.max(max, num);
        for (int num : rewardValues) {
            clone = (BitSet) current.clone();
            int size = clone.size();
            if (size > num) clone.clear(num, size);
            shifted = shiftLeft(clone, num, 2 * max);
            current.or(shifted);
        }
        return current.previousSetBit(100_000);
    }

    private BitSet shiftLeft(BitSet bits, int n, int max) {
        if (n == 0) return (BitSet) bits.clone();
        max = Math.min(max, bits.size() + n);
        int length = 1 + (max / 64);
        long[] res = new long[length];
        int cIdx = n / 64;
        n %= 64;
        long[] raw = bits.toLongArray();
        long carry = 0;

        for (int uIdx = cIdx, i = 0; uIdx < res.length; ++uIdx, ++i) {
            long val = i >= raw.length ? 0 : raw[i];

            if (n != 0) {
                long newcarry = val >>> (64 - n);
                res[uIdx] = (val << n) | carry;
                carry = newcarry;
            } else {
                res[uIdx] = val;
            }
            if (carry == 0 && i >= raw.length) break;
        }
        return BitSet.valueOf(res);
    }


    /* Third Solution */
    Integer[][] memo;

    public int maxTotalReward3(int[] rewardValues) {
        Arrays.sort(rewardValues);
        memo = new Integer[rewardValues.length][2001];
        return solve(rewardValues, 0, 0);
    }

    private int solve(int[] arr, int idx, int val) {
        if (idx == arr.length || val >= 2000) return val;
        else if (memo[idx][val] != null) return memo[idx][val];

        // skip
        int result = solve(arr, idx + 1, val);

        // take
        if (val < arr[idx]) {
            result = Math.max(result, solve(arr, idx + 1, val + arr[idx]));
        }

        memo[idx][val] = result;
        return result;
    }
}