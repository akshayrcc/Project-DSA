package com.akshayram.util;

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
public class SubHashMulti {
    private static final int[] P = {2131131137, 2147483647};
    private static final int[] K = {104723, 104729};

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
