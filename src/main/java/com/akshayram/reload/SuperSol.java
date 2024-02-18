package com.akshayram.reload;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class SuperSol {
    public long countPrefixSuffixPairs(String[] words) {
        Trie root = new Trie();

        long ans = 0;
        for (String w : words) {
            int K = w.length();

            int[] T = KMP.buildTable(w);
            boolean[] good = new boolean[K + 1];
            int idx = K;
            while (idx > 0) {
                good[idx] = true;
                idx = T[idx];
            }

            Trie curr = root;
            for (int i = 1; i <= K; ++i) {
                int c = w.charAt(i - 1) - 'a';
                curr = curr.next[c];
                if (curr == null) {
                    break;
                }
                if (good[i]) {
                    ans += curr.count;
                }
            }
            root.insert(w);
        }
        return ans;
    }

    private static class Trie {
        public Trie[] next = new Trie[26];
        public int count = 0;

        public void insert(String s) {
            Trie t = this;
            for (char c : s.toCharArray()) {
                int i = c - 'a';
                Trie nt = t.next[i];
                if (nt == null) {
                    nt = new Trie();
                    t.next[i] = nt;
                }
                t = nt;
            }
            ++t.count;
        }
    }

    private static Tuple key(long[] hash) {
        return new Tuple(hash[0], hash[1], hash[2], hash[3]);
    }

    private static class Tuple {
        public long a, b, c, d;

        public Tuple(long a, long b, long c, long d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object obj) {
            Tuple t = (Tuple) obj;
            return t.a == a && t.b == b && t.c == c && t.d == d;
        }

        @Override
        public int hashCode() {
            return (int) (a * 11 + b * 17 + c * 31 + d * 41);
        }
    }

    /**
     * Computes multiple polynomial hashes to do fast equality checks.
     * Excluding collisions, two subarrays are considered equal if their hashes by the `sub` function are equal.
     * <p>
     * To reduce collisions, add new distinct primes to `P` (modulo) and `K` (base), but it will cause a performance penalty.
     * Note that the choices of P and K must not exceed 2147483647, e.g. it must fit within a signed 32-bit integer.
     * <p>
     * Some additional pairs to consider:
     * - P = 2122331213, K = 104717
     * - P = 2124749677, K = 104711
     * <p>
     * NOTE:  If only a single value in `P` is needed, consider using SubHash instead.
     * NOTE:  It is NOT hack-resistant!
     */
    public static class SubHashMulti {
        private static final int[] P = {2131131137, 2147483647, 2122331213, 2124749677};
        private static final int[] K = {104723, 104729, 104717, 104711};

        private static final int HASHES = P.length;

        private static final int MAX_LEN = 2_000_002;

        private static int UPTO = 1;
        private static long[][] POW = new long[HASHES][MAX_LEN];
        private static long[][] INV = new long[HASHES][MAX_LEN];

        static {
            for (int j = 0; j < HASHES; ++j) {
                POW[j][0] = 1;
                POW[j][1] = K[j];
                INV[j][0] = 1;
                INV[j][1] = modInverse(K[j], P[j]);
            }
        }

        private static void loadPows(int upper) {
            for (int j = 0; j < HASHES; ++j) {
                for (int i = UPTO + 1; i <= upper; ++i) {
                    POW[j][i] = POW[j][i - 1] * POW[j][1] % P[j];
                    INV[j][i] = INV[j][i - 1] * INV[j][1] % P[j];
                }
            }
            UPTO = Math.max(UPTO, upper);
        }

        private final long[] S;
        private final long[][] H;

        public SubHashMulti(long[] x) {
            loadPows(x.length);

            S = x;
            H = new long[HASHES][S.length + 1];
            for (int j = 0; j < HASHES; ++j) {
                for (int i = 0; i < S.length; ++i) {
                    H[j][i + 1] = (H[j][i] + S[i] * POW[j][i]) % P[j];
                }
            }
        }

        public SubHashMulti(int[] x) {
            this(toLongArray(x));
        }

        public SubHashMulti(char[] x) {
            this(toLongArray(x));
        }

        public SubHashMulti(String x) {
            this(x.toCharArray());
        }

        public long[] sub(int loInclusive, int hiExclusive) {
            long[] hash = new long[HASHES];
            for (int j = 0; j < HASHES; ++j) {
                hash[j] = (H[j][hiExclusive] + P[j] - H[j][loInclusive]) * INV[j][loInclusive] % P[j];
            }
            return hash;
        }

        public int length() {
            return S.length;
        }

        private static long[] toLongArray(char[] x) {
            long[] arr = new long[x.length];
            for (int i = 0; i < x.length; ++i) {
                arr[i] = x[i];
            }
            return arr;
        }

        private static long[] toLongArray(int[] x) {
            long[] arr = new long[x.length];
            for (int i = 0; i < x.length; ++i) {
                arr[i] = x[i];
            }
            return arr;
        }

        /**
         * Computes the value of (b ^ e) % mod.
         */
        private static long modPow(long b, long e, long mod) {
            long p = b;
            long ans = 1;
            while (e > 0) {
                if ((e & 1) == 1) {
                    ans = ans * p % mod;
                }
                p = p * p % mod;
                e >>= 1;
            }
            return ans;
        }

        /**
         * Computes the modular inverse, such that: ak % MOD = 1, for some k.
         * See this page for details:  http://rosettacode.org/wiki/Modular_inverse
         */
        public static long modInverse(long a, long mod) {
            return modPow(a, mod - 2, mod);
        }
    }

    /**
     * Counts the frequency of objects.
     * Change to extend TreeMap instead, if ordering of objects is required.
     * <p>
     * NOTE:  If `total` is needed, only use `increment(...)` for updates.
     */
    public static class CountMapInt<T> extends HashMap<T, Integer> implements Serializable {
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

    /**
     * Counts the frequency of objects.
     * Change to extend TreeMap instead, if ordering of objects is required.
     * <p>
     * NOTE:  If `total` is needed, only use `increment(...)` for updates.
     */
    public static class CountMapLong<T> extends HashMap<T, Long> {
        private static final long serialVersionUID = -9079906779955923767L;

        public long total;

        public long getCount(T k) {
            return getOrDefault(k, 0L);
        }

        public void increment(T k, long v) {
            total += v;
            long next = getCount(k) + v;
            if (next == 0) {
                remove(k);
            } else {
                put(k, next);
            }
        }

        public static <T> CountMapLong<T> fromArray(T[] A) {
            CountMapLong<T> cm = new CountMapLong<>();
            for (T x : A) {
                cm.increment(x, 1);
            }
            return cm;
        }
    }

    /**
     * Wrapper class for Knuth-Morris-Pratt (KMP) string search algorithm.
     * - Use `KMP.buildTable(...)` if only the failure table containing prefix matches is needed.
     * - Use `KMP.search(...)` to find all matching indices.
     */
    public static class KMP {
        public int[] W;
        public int[] T;

        public KMP(String needle) {
            this(stringToIntArray(needle));
        }

        public KMP(char[] needle) {
            this(charArrayToIntArray(needle));
        }

        public KMP(int[] needle) {
            this.W = needle;
            this.T = buildTable(this.W);
        }

        public int[] search(String S) {
            return search(stringToIntArray(S));
        }

        public int[] search(char[] S) {
            return search(charArrayToIntArray(S));
        }

        public int[] search(int[] S) {
            final int N = S.length;
            final int M = W.length;

            if (N == 0) {
                if (M == 0) {
                    return new int[1];
                } else {
                    return new int[0];
                }
            }
            if (M == 0) {
                int[] arr = new int[N];
                for (int i = 0; i < N; ++i) {
                    arr[i] = i;
                }
                return arr;
            }

            int j = 0;
            int k = 0;
            int[] found = new int[N];
            int p = 0;
            while (j < S.length) {
                if (W[k] == S[j]) {
                    ++j;
                    ++k;
                    if (k == M) {
                        found[p++] = j - k;
                        k = T[k];
                    }
                } else {
                    k = T[k];
                    if (k < 0) {
                        ++j;
                        ++k;
                    }
                }
            }
            return Arrays.copyOf(found, p);
        }

        public static int[] buildTable(String W) {
            return buildTable(stringToIntArray(W));
        }

        public static int[] buildTable(char[] W) {
            return buildTable(charArrayToIntArray(W));
        }

        public static int[] buildTable(int[] W) {
            final int N = W.length;
            int[] T = new int[N + 1];
            T[0] = -1;
            int pos = 1;
            int cnd = 0;
            while (pos < N) {
                if (W[pos] == W[cnd]) {
                    T[pos] = cnd;
                } else {
                    T[pos] = cnd;
                    while (cnd >= 0 && W[pos] != W[cnd]) {
                        cnd = T[cnd];
                    }
                }
                ++pos;
                ++cnd;
            }
            T[pos] = cnd;
            return T;
        }

        public static int[] search(String S, String W) {
            return new KMP(W).search(S);
        }

        public static int[] search(char[] S, char[] W) {
            return new KMP(W).search(S);
        }

        public static int[] search(int[] S, int[] W) {
            return new KMP(W).search(S);
        }

        private static int[] stringToIntArray(String str) {
            return charArrayToIntArray(str.toCharArray());
        }

        private static int[] charArrayToIntArray(char[] S) {
            final int N = S.length;

            int[] A = new int[N];
            for (int i = 0; i < N; ++i) {
                A[i] = S[i];
            }
            return A;
        }
    }

}
