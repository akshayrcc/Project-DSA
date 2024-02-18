package com.akshayram.reload;

import java.util.*;

public class SuperSol2 {
    private static final int[] P = primesTo(1_000_000);
    private static final HashSet<Integer> P_SET = new HashSet<>();

    static {
        for (int p : P) {
            if (p > 10) {
                P_SET.add(p);
            }
        }
    }

    private static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};

    //Most Frequent Prime
    public int mostFrequentPrime(int[][] mat) {
        final int N = mat.length;
        final int M = mat[0].length;

        CountMapInt<Integer> freq = new CountMapInt<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                for (int d = 0; d < DR.length; ++d) {
                    load(mat, i, j, d, freq);
                }
            }
        }

        int ans = -1;
        int maxFreq = -1;
        for (Map.Entry<Integer, Integer> kvp : freq.entrySet()) {
            int p = kvp.getKey();
            int f = kvp.getValue();

            if (f > maxFreq || (f == maxFreq && p > ans)) {
                ans = p;
                maxFreq = f;
            }
        }
        return ans;
    }

    private static void load(int[][] A, int r, int c, int d, CountMapInt<Integer> freq) {
        final int N = A.length;
        final int M = A[0].length;

        int curr = 0;
        while (r >= 0 && r < N && c >= 0 && c < M) {
            curr = curr * 10 + A[r][c];
            r += DR[d];
            c += DC[d];
            if (P_SET.contains(curr)) {
                freq.increment(curr, 1);
            }
        }
    }

    /**
     * Computes all the primes up to the specified number.
     */
    public static int[] primesTo(int n) {
        ArrayList<Integer> primes = new ArrayList<Integer>();

        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, 2, n + 1, true);
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                primes.add(i);
                if ((long) i * i <= n) {
                    for (int j = i * i; j < prime.length; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }

        int[] ans = new int[primes.size()];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = primes.get(i);
        }
        return ans;
    }

    /**
     * Counts the frequency of objects.
     * Change to extend TreeMap instead, if ordering of objects is required.
     * <p>
     * NOTE:  If `total` is needed, only use `increment(...)` for updates.
     */
    public static class CountMapInt<T> extends HashMap<T, Integer> {
        private static final long serialVersionUID = -1501598139835601959L;

        public int total;

        public int getCount(T k) {
            return getOrDefault(k, 0);
        }

        public void increment(T k, int v) {
            total += v;
            int next = getCount(k) + v;
            if (next == 0) {
                remove(k);
            } else {
                put(k, next);
            }
        }

        public static <T> CountMapInt<T> fromArray(T[] A) {
            CountMapInt<T> cm = new CountMapInt<>();
            for (T x : A) {
                cm.increment(x, 1);
            }
            return cm;
        }
    }
}