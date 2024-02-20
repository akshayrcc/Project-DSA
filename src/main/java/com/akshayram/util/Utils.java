package com.akshayram.util;

import java.util.*;

public class Utils {
    public static int log2(long n) {
        return 63 - Long.numberOfLeadingZeros(n);
    }

    // nums = "aaabaaaba" odd d[1] return [1, 2, 1, 4, 1, 2, 2]
    // d[0] for even, d[1] for odd
    public static int[][] manacher(String s) {
        int n = s.length();
        int[] d1 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && s.charAt(i - k) == s.charAt(i + k)) k++;
            d1[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }

        int[] d0 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(d0[l + r - i + 1], r - i + 1);
            while (0 <= i - k - 1 && i + k < n && s.charAt(i - k - 1) == s.charAt(i + k)) {
                k++;
            }
            d0[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }
        return new int[][]{d0, d1};
    }

    // generate primes that is <= n
    public static int[] genPrimes(int n) {
        if (n <= 1) return new int[0];
        boolean[] isComp = new boolean[n + 1];
        int len = 0;
        for (int i = 2; i <= n; i++) {
            if (!isComp[i]) {
                len++;
                for (int j = i + i; j <= n; j += i) {
                    isComp[j] = true;
                }
            }
        }
        int[] ans = new int[len];
        for (int i = 2, j = 0; i <= n; i++) {
            if (!isComp[i]) {
                ans[j++] = i;
            }
        }
        return ans;
    }

    // int[0] = primeFactor, int[1] = count of that primeFactor
    // NOTE: factors[0] and factors[1] are empty list.
    public static List<int[]>[] genPrimeFactors(int n) {
        List<int[]>[] factors = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }
        boolean[] isComp = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!isComp[i]) {
                factors[i].add(new int[]{i, 1});
                for (int j = i + i; j <= n; j += i) {
                    isComp[j] = true;
                    int t = j, count = 0;
                    while (t % i == 0) {
                        count++;
                        t /= i;
                    }
                    factors[j].add(new int[]{i, count});
                }
            }
        }
        return factors;
    }

    // generate factors (not prime factors), factors[0] is empty list.
    public static List<Integer>[] genFactors(int n) {
        List<Integer>[] factors = new List[n + 1];
        for (int i = 0; i <= n; i++) factors[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                factors[j].add(i);
            }
        }
        return factors;
    }

    public static int lowerBound(int[] a, int target) {
        return lowerBound(a, target, a.length);
    }

    public static int lowerBound(int[] a, int target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(int[] a, int target) {
        return upperBound(a, target, a.length);
    }

    public static int upperBound(int[] a, int target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] a, T target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int upperBound(T[] a, T target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid].compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(List<T> a, T target) {
        int low = 0;
        int high = a.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a.get(mid).compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int upperBound(List<T> a, T target) {
        int low = 0;
        int high = a.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a.get(mid).compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static long or(int x, int y) {
        return ((long) x << 32) | ((long) y << 32 >>> 32);
    }

    // k = 0...nums.length - 1
    // After calling this function, nums[k] is the k-th number.
    public static int kthNumber(int[] nums, int k) {
        return kthNumberHelper(nums, k, 0, nums.length - 1);
    }

    // end is inclusive (0 to nums.length - 1)
    private static int kthNumberHelper(int[] nums, int K, int start, int end) {
        if (start == end) return nums[start];
        int i = partition(nums, start, end);
        return K <= i ? kthNumberHelper(nums, K, start, i) : kthNumberHelper(nums, K, i + 1, end);
    }

    // end is inclusive (0 to nums.length - 1)
    public static int partition(int[] nums, int start, int end) {
        int pos = start + ((end - start) >> 1);
        int pivot = nums[pos];

        int i = start, j = end;
        nums[pos] = nums[end];
        while (i < j) {
            while (i < j && nums[i] < pivot) i++;
            if (i < j) nums[j--] = nums[i];

            while (i < j && pivot < nums[j]) j--;
            if (i < j) nums[i++] = nums[j];
        }

        // pos is the final position for pivot.
        nums[i] = pivot;
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // partition nums into 3 parts [smaller than pivot, equal to pivot, larger than pivot]
    public static void partition3(int[] nums, int pivot) {
        int n = nums.length;
        int l = 0, r = n - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] < pivot) {
                swap(nums, l++, i);
            } else if (nums[i] > pivot) {
                swap(nums, r--, i--);
            }
        }
    }

    // end is inclusive
    public static void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    // Return false if next permutation is not available. (nums is not changed for this case)
    public static boolean nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i <= 0) return false;
        for (int j = n - 1; j >= i; j--) {
            if (nums[j] > nums[i - 1]) {
                int t = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = t;

                reverse(nums, i, n - 1);
                break;
            }
        }
        return true;
    }

    // Return false if previous permutation is not available. (nums is not changed for this case)
    public static boolean prevPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] <= nums[i]) {
            i--;
        }
        if (i <= 0) return false;
        for (int j = n - 1; j >= i; j--) {
            if (nums[j] < nums[i - 1]) {
                int t = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = t;

                reverse(nums, i, n - 1);
                break;
            }
        }
        return true;
    }

    // This function is deprecated, please hash(int l, int r) in class StableStringHash or StringHash
    // rolling hash of substrings of 's' with length == k
    // long[pos] = hash(s.substring(pos, pos + k))
    public static long[] rollingHash(String s, int k) {
        long[] ans = new long[s.length() - k + 1];
        int seed1 = 31;
        int seed2 = 131;
        int h1 = 0, h2 = 0, power1 = 1, power2 = 1;
        for (int i = 0; i < k; i++) {
            h1 = h1 * seed1 + s.charAt(i);
            h2 = h2 * seed2 + s.charAt(i);

            power1 *= seed1;
            power2 *= seed2;
        }

        ans[0] = ((long) h1 << 32) | ((long) h2 << 32 >>> 32);
        for (int i = k; i < s.length(); i++) {
            h1 = h1 * seed1 + s.charAt(i) - power1 * s.charAt(i - k);
            h2 = h2 * seed2 + s.charAt(i) - power2 * s.charAt(i - k);
            ans[i - k + 1] = ((long) h1 << 32) | ((long) h2 << 32 >>> 32);
        }

        return ans;
    }

    public static int[] kmpNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = next[i - 1];
            while (k > 0 && s.charAt(i) != s.charAt(k)) k = next[k - 1];
            if (s.charAt(i) == s.charAt(k)) {
                next[i] = k + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    // s is the original String
    // p is the pattern String
    public static boolean kmpMatch(String s, String p) {
        int[] next = kmpNext(p);
        int n = s.length();
        int m = p.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (p.charAt(j) == s.charAt(i)) j++;
            if (j == m) return true;
        }
        return false;
    }

    public static int[] kmpNext(int[] s) {
        int n = s.length;
        int[] next = new int[n];
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = next[i - 1];
            while (k > 0 && s[i] != s[k]) k = next[k - 1];
            if (s[i] == s[k]) {
                next[i] = k + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    // s is the original String
    // p is the pattern String
    public static boolean kmpMatch(int[] s, int[] p) {
        int[] next = kmpNext(p);
        int n = s.length;
        int m = p.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p[j] != s[i]) {
                j = next[j - 1];
            }
            if (p[j] == s[i]) j++;
            if (j == m) return true;
        }
        return false;
    }

    // a, b could be negative
    // remainder >= 0
    // return [a / b, a % b]
    public static int[] divMod(int a, int b) {
        int r = a % b;
        int c = a / b;
        if (r < 0) {
            r += Math.abs(b);
            c++;
        }
        return new int[]{c, r};
    }

    public static List<Integer> negativeBase(int n, int base) {
        List<Integer> digits = new ArrayList<>();
        if (n == 0) {
            digits.add(0);
            return digits;
        }
        while (n != 0) {
            // t[0] = n / base
            // t[1] = n % base;
            int[] t = divMod(n, base);
            digits.add(t[1]);
            n = t[0];
        }
        Collections.reverse(digits);
        return digits;
    }

    // (a ^ b) % MOD
    public static long powMod(long a, long b, long MOD) {
        long res = 1L;
        a %= MOD; // In case a * a is overflow
        while (b > 0) {
            if ((b & 1) != 0) res = (res * a) % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public static void preprocessCombination(long[][] C, long MOD) {
        int m = C.length;
        int n = C[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (j == 0 || i == j) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }
    }

    public static void preprocessCombination(long[][] C) {
        int m = C.length;
        int n = C[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (j == 0 || i == j) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
            }
        }
    }

    public static void preprocessPermutation(long[][] P, long MOD) {
        int m = P.length;
        int n = P[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (i == 0 || j == 0) P[i][j] = 1;
                else P[i][j] = P[i][j - 1] * (i - j + 1) % MOD;
            }
        }
    }

    public static long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }

    public static int[] preprocessLog2(int n) { // from log2(0) .... log2(n) inclusive
        int[] lg = new int[n + 1];
        for (int k = 0, i = 1; i <= n; lg[i++] = k - 1) {
            while ((1 << k) <= i) {
                k++;
            }
        }
        return lg;
    }
}

// Tested by leetcode 1163
class SuffixArray {
    int[] s;
    int[] SA; // SA[i] => index of the i-th ranked suffix
    int[] rank; // rank[i] => the rank of i-th suffix, SA[rank[i]] == i
    int[] height; // height[i] = LCP(s[SA[i]], s[SA[i - 1]]).

    private int[][] d;
    private int n;
    private int[] lg;

    private int log2(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    private static int[] stringToIntArray(String str) {
        int[] nums = new int[str.length()];
        for (int i = 0; i < str.length(); i++) nums[i] = str.charAt(i);
        return nums;
    }

    public SuffixArray(String str) {
        this(str, true);
    }

    public SuffixArray(String str, boolean toBuildRMQ) {
        this(stringToIntArray(str), toBuildRMQ);
    }

    public SuffixArray(int[] nums) {
        this(nums, true);
    }

    public SuffixArray(int[] nums, boolean toBuildRMQ) {
        this.s = nums;
        this.n = nums.length;
        this.SA = new int[n];
        this.rank = new int[n];
        suffixSort(s);
        if (toBuildRMQ) {
            this.height = new int[n];
            this.d = new int[log2(n) + 1][n];
            this.lg = new int[n + 1];
            buildHeight();
            buildRMQ();
        }
    }

    // height[i] = LCP(s[SA[i]], s[SA[i - 1]]).
    // ?? s[n] = 0 or s[n] = -INF.
    private void buildHeight() {
        int i, j, h;
        height[0] = 0;
        for (i = 0; i < n; i++)
            rank[SA[i]] = i;
        for (h = i = 0; i < n; i++)
            if (rank[i] > 0) {
                j = SA[rank[i] - 1];
                while (i + h < n && j + h < n && s[i + h] == s[j + h]) ++h;
                height[rank[i]] = h;
                if (h > 0) --h;
            }
    }

    // LCP(i, j) = LCP(s[SA[i]], s[SA[j]]) = min{height[k] | i + 1 <= k <= j}.
    private void buildRMQ() {
        int i, j, k;
        for (i = 0; i < n; i++)
            d[0][i] = height[i];
        for (j = 1; (1 << j) <= n; j++)
            for (i = 0; i + (1 << j) <= n; i++)
                d[j][i] = Math.min(d[j - 1][i], d[j - 1][i + (1 << (j - 1))]);
        for (lg[0] = k = 0, i = 1; i <= n; lg[i++] = k - 1)
            while ((1 << k) <= i) k++;
    }

    // LCP(i, j) = LCP(s[SA[i]], s[SA[j]]) = min{height[k] | i + 1 <= k <= j}.
    public int LCP(int i, int j) {
        if (i == j) return n - SA[i];
        if (i > j) {
            int t = i;
            i = j;
            j = t;
        }
        int k = lg[j - (++i) + 1];
        return Math.min(d[k][i], d[k][j - (1 << k) + 1]);
    }

    // LCPIndex(i, j) = LCP(s[i], s[j])
    public int LCPIndex(int i, int j) {
        return LCP(rank[i], rank[j]);
    }

    private void sortIndex(int[] nums, int start, int end) {
        if (start >= end) return;
        int pos = start + ((end - start) >> 1);
        int pivot = nums[pos];

        int i = start, j = end;
        nums[pos] = nums[end];
        while (i < j) {
            while (i < j && s[nums[i]] < s[pivot]) i++;
            if (i < j) nums[j--] = nums[i];

            while (i < j && s[pivot] < s[nums[j]]) j--;
            if (i < j) nums[i++] = nums[j];
        }
        nums[i] = pivot; // index i is the final position for pivot.
        sortIndex(nums, start, i - 1);
        sortIndex(nums, i + 1, end);
    }

    private void suffixSort(int[] s) {
        int[] count = new int[n], t;
        int[] nSA = new int[n];
        int[] nRank = new int[n];
//        Integer[] tempArray = new Integer[n];
//        for (int x = 0; x < n; x++) tempArray[x] = x;
//        Arrays.sort(tempArray, (a, b) -> Integer.compare(s[a], s[b]));
//        for (int x = 0; x < n; x++) SA[x] = tempArray[x];
        int i, k;
        int minV = s[0], maxV = s[0];
        for (i = 1; i < n; i++) {
            minV = Math.min(minV, s[i]);
            maxV = Math.max(maxV, s[i]);
        }
        if ((long) maxV - minV + 1 <= (long) n) { // radix sort if alphabet size is small
            for (i = 0; i < n; i++) count[s[i] - minV]++;
            for (i = minV + 1; i <= maxV; i++) count[i - minV] += count[i - minV - 1];
            for (i = n - 1; i >= 0; i--) SA[--count[s[i] - minV]] = i;
        } else {
            for (i = 0; i < n; i++) SA[i] = i;
            sortIndex(SA, 0, n - 1);
        }
        for (rank[SA[0]] = 0, i = 1; i < n; i++) {
            rank[SA[i]] = (s[SA[i]] != s[SA[i - 1]]) ? rank[SA[i - 1]] + 1 : rank[SA[i - 1]];
        }
        for (k = 1; k < n && rank[SA[n - 1]] < n - 1; k <<= 1) {
            for (i = 0; i < n; i++) count[rank[SA[i]]] = i + 1;
            for (i = n - 1; i >= 0; i--) if (SA[i] >= k) nSA[--count[rank[SA[i] - k]]] = SA[i] - k;
            for (i = n - k; i < n; i++) nSA[--count[rank[i]]] = i;
            t = SA;
            SA = nSA;
            nSA = t;
            for (nRank[SA[0]] = 0, i = 1; i < n; i++) {
                nRank[SA[i]] = (SA[i] + k >= n || SA[i - 1] + k >= n || rank[SA[i]] != rank[SA[i - 1]] || rank[SA[i] + k] != rank[SA[i - 1] + k]) ? nRank[SA[i - 1]] + 1 : nRank[SA[i - 1]];
            }
            t = rank;
            rank = nRank;
            nRank = t;
        }
    }
}

class Combination {
    long[] factorial;
    long[] inverseFactorial;
    long MOD;

    // O(maxSize) for preprocessing
    // MOD must be prime
    public Combination(int maxSize, long MOD) {
        factorial = new long[maxSize + 1];
        inverseFactorial = new long[maxSize + 1];
        factorial[0] = 1;
        inverseFactorial[0] = 1;
        this.MOD = MOD;
        for (int i = 1; i <= maxSize; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
            inverseFactorial[i] = inverse(factorial[i]);
        }
    }

    // x, y are long[1]
    // ax + by = gcd(a, b)
    private long extGcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            long r = extGcd(b, a % b, y, x);
            y[0] -= x[0] * (a / b);
            return r;
        }
    }

    // (a * x) % MOD = 1
    // x is the inverse element
    public long inverse(long a) {
        long[] x = new long[1], y = new long[1];
        extGcd(a, MOD, x, y);
        return (x[0] % MOD + MOD) % MOD;
    }

    // m >= n
    // O(1)
    public long P(int m, int n) {
        if (m == 0 || n == 0) return 1;
        else if (m == n) return factorial[m];
            //return factorial[m] * inverse(factorial[m - n]) % MOD;
        else return factorial[m] * inverseFactorial[m - n] % MOD;
    }

    // m >= n
    // O(1)
    public long C(int m, int n) {
        if (m == 0 || n == 0 || m == n) return 1;
            //return factorial[m] * inverse(factorial[m - n]) % MOD * inverse(factorial[n]) % MOD;
        else return factorial[m] * inverseFactorial[m - n] % MOD * inverseFactorial[n] % MOD;
    }
}

class Fraction {
    long n, d;

    public Fraction(long n, long d) {
        if (d == 0) {
            this.n = Long.MAX_VALUE;
            this.d = 1;
            return;
        }
        long g = gcd(Math.abs(n), Math.abs(d));
        this.n = n / g;
        this.d = d / g;
        if (this.d < 0) {
            this.d = -this.d;
            this.n = -this.n;
        }
    }

    public void add(Fraction other) {
        long numerator = this.n * other.d + this.d * other.n;
        long denominator = this.d * other.d;
        long g = gcd(Math.abs(numerator), Math.abs(denominator));
        this.n = numerator / g;
        this.d = denominator / g;
        if (this.d < 0) {
            this.d = -this.d;
            this.n = -this.n;
        }
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Fraction) {
            Fraction anotherObject = (Fraction) anObject;
            return n == anotherObject.n && d == anotherObject.d;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) (n * 31 + d);
    }

    private long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }
}

class UnionFind {
    int[] parent;
    int[] sz;

    // from 0 ... n - 1
    public UnionFind(int n) {
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public void clear() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        //return parent[x] == x ? x : (parent[x] = find(parent[x]));
        if (parent[x] == x) return x;
        int px = x;
        while (px != parent[px]) px = parent[px];
        while (x != px) {
            int next = parent[x];
            parent[x] = px;
            x = next;
        }
        return px;
    }

    // px is the final parent
    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        parent[py] = px;
        sz[px] += sz[py];
        return true;
    }

    public int size(int x) {
        return sz[find(x)];
    }

    public UnionFind clone() {
        UnionFind cloned = new UnionFind(parent.length);
        for (int i = 0; i < parent.length; i++) {
            cloned.parent[i] = parent[i];
            cloned.sz[i] = sz[i];
        }
        return cloned;
    }
}

class Trie {
    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord = false;
        long count = 0L;
    }

    TrieNode root = new TrieNode();

    public void add(String word) {
        add(word, 1L);
    }

    public void remove(String word) {
        add(word, -1L);
    }

    private void add(String word, long count) {
        TrieNode cur = root;
        cur.count += count;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.next[c] == null) cur.next[c] = new TrieNode();
            cur = cur.next[c];
            cur.count += count;
        }
        cur.isWord = true;
    }

    public boolean contains(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.next[c] == null || cur.next[c].count <= 0L) return false;
            cur = cur.next[c];
            //if (cur.isWord) return true;
        }
        return cur.isWord;
    }
}

// Tested by Leetcode 1707 and Leetcode 1803
class TrieBinary {
    static class TrieNode {
        TrieNode[] next = new TrieNode[2];
        long count;
    }

    private TrieNode root;
    private int numOfBits;

    public TrieBinary(int numOfBits) { // normally 31 for int and 63 for long
        this.numOfBits = numOfBits;
        this.root = new TrieNode();
    }

    public void add(long v) {
        add(v, 1L);
    }

    public void remove(long v) { // Tested by Leetcode 1938
        add(v, -1L);
    }

    public void add(long v, long count) {
        TrieNode cur = root;
        root.count += count;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int b = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (cur.next[b] == null) cur.next[b] = new TrieNode();
            cur = cur.next[b];
            cur.count += count;
        }
    }

    // Tested by Leetcode 1707
    public long xorMax(long v) {
        TrieNode cur = root;
        long key = 0;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int b = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (cur.next[1 - b] != null && cur.next[1 - b].count > 0) {
                cur = cur.next[1 - b];
                key |= (1L - b) << i;
            } else {
                cur = cur.next[b];
                key |= (long) b << i;
            }
        }
        return key ^ v;
    }

    public boolean contains(long v) {
        TrieNode cur = root;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int b = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (cur.next[b] == null || cur.next[b].count <= 0) return false;
            cur = cur.next[b];
        }
        return true;
    }

    // Tested by Leetcode 1803
    // count(x ^ v <= limit)
    public long xorLessOrEqualCount(long v, long limit) {
        if (limit < 0) return 0;
        TrieNode cur = root;
        long ans = 0;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int bitLimit = ((limit >>> i) & 1) == 0 ? 0 : 1;
            int bitV = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (bitLimit == 1) {
                ans += (cur.next[bitV] != null ? cur.next[bitV].count : 0);
            }
            cur = cur.next[bitV ^ bitLimit];
            if (cur == null) break;
            if (i == 0) ans += cur.count;
        }
        return ans;
    }
}

// SegmentTree for Range Query Sum
// Tested by https://leetcode.com/problems/range-sum-query-mutable/
class SegmentTree {
    private int size;
    private int[] nodes;

    // 0 ... n - 1
    public SegmentTree(int[] input) {
        this.size = input.length;
        this.nodes = new int[this.size * 4];
        build(0, 0, size - 1, input);
    }

    private void build(int x, int l, int r, int[] input) {
        if (l == r) {
            nodes[x] = input[l]; //// TODO
            return;
        }

        int m = (l + r) >> 1;
        build(x * 2 + 1, l, m, input);
        build(x * 2 + 2, m + 1, r, input);
        nodes[x] = nodes[x * 2 + 1] + nodes[x * 2 + 2]; //// TODO
    }

    public void update(int pos, int value) {
        update(0, 0, size - 1, pos, value);
    }

    private void update(int x, int l, int r, int pos, int value) {
        if (l == r) {
            nodes[x] = value; //// TODO
            return;
        }
        int m = (l + r) >> 1;
        if (pos <= m) update(x * 2 + 1, l, m, pos, value);
        else update(x * 2 + 2, m + 1, r, pos, value);
        nodes[x] = nodes[x * 2 + 1] + nodes[x * 2 + 2]; //// TODO
    }

    public int query(int queryL, int queryR) {
        return query(0, 0, size - 1, queryL, queryR);
    }

    private int query(int x, int l, int r, int queryL, int queryR) {
        if (queryL <= l && r <= queryR) {
            return nodes[x];
        }
        int m = (l + r) >> 1;
        if (queryR <= m) return query(x * 2 + 1, l, m, queryL, queryR);
        else if (m + 1 <= queryL) return query(x * 2 + 2, m + 1, r, queryL, queryR);
        else return query(x * 2 + 1, l, m, queryL, queryR) + query(x * 2 + 2, m + 1, r, queryL, queryR); //// TODO
    }
}

// Tested by Leetcode 732
// Lazy execution example is in https://leetcode.com/submissions/detail/1034182539/
// Refer to AlgorithmCompilation.java


// Tested by Leetcode 307
class SparseSegmentTreeRangeSum {
    class TreeNode {
        TreeNode left, right;
        long sum;
    }

    private TreeNode root;
    private final long L, R;

    public SparseSegmentTreeRangeSum(long l, long r) {
        L = l;
        R = r;
        root = new TreeNode();
    }

    public void update(long position, long value) {
        update(position, value, L, R, root);
    }

    private void update(long position, long value, long L, long R, TreeNode root) {
        if (position < L || R < position) return;
        if (L == R) {
            root.sum = value;
            return;
        }
        long M = L + ((R - L) >> 1);
        if (root.left == null) root.left = new TreeNode();
        if (root.right == null) root.right = new TreeNode();
        update(position, value, L, M, root.left);
        update(position, value, M + 1, R, root.right);
        root.sum = root.left.sum + root.right.sum;
    }

    // Sum[queryL...queryR] inclusive
    public long getSum(long queryL, long queryR) {
        return getSum(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long getSum(TreeNode root, long L, long R, long queryL, long queryR) {
        if (queryL > queryR || root == null) return 0L;
        if (queryL <= L && R <= queryR) {
            return root.sum;
        }
        long M = L + ((R - L) >> 1);
        if (queryR <= M) {
            return getSum(root.left, L, M, queryL, queryR);
        } else if (M + 1 <= queryL) {
            return getSum(root.right, M + 1, R, queryL, queryR);
        } else {
            return getSum(root.left, L, M, queryL, queryR) + getSum(root.right, M + 1, R, queryL, queryR);
        }
    }
}

// Tested by Leetcode 239, 2926
class SparseSegmentTreeRangeMax {
    class TreeNode {
        TreeNode left, right;
        long max = Long.MIN_VALUE;
    }

    private TreeNode root;
    private final long L, R;

    public SparseSegmentTreeRangeMax(long l, long r) {  // [l, r] inclusive
        L = l;
        R = r;
        root = new TreeNode();
    }

    public void update(long position, long value) {
        update(root, L, R, position, value);
    }

    private void update(TreeNode root, long L, long R, long position, long value) {
        if (position < L || R < position) return;
        if (L == R) {
            root.max = value;
            return;
        }

        long M = L + ((R - L) >> 1);
        if (root.left == null) root.left = new TreeNode();
        if (root.right == null) root.right = new TreeNode();
        update(root.left, L, M, position, value);
        update(root.right, M + 1, R, position, value);
        root.max = Math.max(root.left.max, root.right.max);
    }

    public long get(long position) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = (l + r) >> 1;
            if (position <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return cur == null ? Long.MIN_VALUE : cur.max;
    }

    // Max[queryL...queryR] inclusive
    public long getMax(long queryL, long queryR) {
        return getMax(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long getMax(TreeNode root, long L, long R, long queryL, long queryR) {
        if (queryL > queryR || root == null) return Long.MIN_VALUE;
        if (queryL <= L && R <= queryR) {
            return root.max;
        }
        long M = L + ((R - L) >> 1);
        if (queryR <= M) {
            return getMax(root.left, L, M, queryL, queryR);
        } else if (M + 1 <= queryL) {
            return getMax(root.right, M + 1, R, queryL, queryR);
        } else {
            return Math.max(getMax(root.left, L, M, queryL, queryR), getMax(root.right, M + 1, R, queryL, queryR));
        }
    }
}

class BinaryIndexTree {
    private long[] c;
    private int n;

    // NOTE: index is from 1 to n. (NOT 0 .. n - 1)
    public BinaryIndexTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    // Add value to position k, k is from 1 to n.
    public void add(int k, long value) {
        while (k <= n) {
            c[k] += value;
            k += (k & (k ^ (k - 1)));
        }
    }

    // k is from 1 to n.
    public long getSum(int k) {
        long sum = 0;
        while (k > 0) {
            sum += c[k];
            k -= (k & (k ^ (k - 1)));
        }
        return sum;
    }
}

class RangeSum {
    BinaryIndexTree tree;
    int start;

    // [low, high] inclusive
    public RangeSum(int low, int high) {
        start = low;
        tree = new BinaryIndexTree(high - low + 1);
    }

    public void add(int index, long value) {
        tree.add(index - start + 1, value);
    }

    public long get(int index) {
        return tree.getSum(index - start + 1) - tree.getSum(index - start);
    }

    public void set(int index, long value) {
        tree.add(index - start + 1, value - get(index));
    }

    // [l, r] inclusive
    public long getSum(int l, int r) {
        return tree.getSum(r - start + 1) - tree.getSum(l - start);
    }
}

class RangeMax {
    int start;
    int n;
    long[] t;

    // [low, high] inclusive
    public RangeMax(int low, int high) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, Long.MIN_VALUE);
    }

    public RangeMax(int low, int high, long defaultValue) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, defaultValue);
    }

    long get(int index) {
        return t[index - start + n];
    }

    void set(int index, long value) {
        index -= start;
        for (t[index += n] = value; (index >>= 1) > 0; ) {
            t[index] = Math.max(t[index << 1], t[index << 1 | 1]);
        }
    }

    // [l, r] inclusive
    long getMax(int l, int r) {
        l -= start;
        r = (r + 1 - start); // make it as [l, r)
        long resl = Long.MIN_VALUE, resr = Long.MIN_VALUE;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) resl = Math.max(resl, t[l++]);
            if ((r & 1) != 0) resr = Math.max(t[--r], resr);
        }
        return Math.max(resl, resr);
    }
}

class RangeMin {
    int start;
    int n;
    long[] t;

    // [low, high] inclusive
    public RangeMin(int low, int high) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, Long.MAX_VALUE);
    }

    public RangeMin(int low, int high, long defaultValue) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, defaultValue);
    }

    long get(int index) {
        return t[index - start + n];
    }

    void set(int index, long value) {
        index -= start;
        for (t[index += n] = value; (index >>= 1) > 0; ) {
            t[index] = Math.min(t[index << 1], t[index << 1 | 1]);
        }
    }

    // [l, r] inclusive
    long getMin(int l, int r) {
        l -= start;
        r = (r + 1 - start); // make it as [l, r)
        long resl = Long.MAX_VALUE, resr = Long.MAX_VALUE;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) resl = Math.min(resl, t[l++]);
            if ((r & 1) != 0) resr = Math.min(t[--r], resr);
        }
        return Math.min(resl, resr);
    }
}

// Used for
class SegmentTreeSet {
    int start;
    int n;
    int[] c;

    static int[] sharedBuffer;

    // [low, high] inclusive
    public SegmentTreeSet(int low, int high) {
        start = low;
        int len = high - low + 1;
        n = 1;
        while (n < len) n <<= 1;
        c = new int[n * 2];
    }

    public SegmentTreeSet(int low, int high, int maxSharedSize) {
        if (sharedBuffer == null) {
            int tn = 1;
            while (tn < maxSharedSize) tn <<= 1;
            sharedBuffer = new int[tn * 2];
        }
        start = low;
        int len = high - low + 1;
        n = 1;
        while (n < len) n <<= 1;
        c = sharedBuffer;
        for (int i = 0; i < n * 2; i++) c[i] = 0;
    }

    public void clear() {
        for (int i = 0; i < n * 2; i++) c[i] = 0;
    }

    private void set(int index, int value) {
        index -= start;
        for (c[index += n] = value; (index >>= 1) > 0; ) {
            c[index] = c[index << 1] + c[index << 1 | 1];
        }
    }

    public int count(int key) {
        return c[key - start + n];
    }

    public void add(int key) {
        set(key, count(key) + 1);
    }

    public boolean remove(int key) {
        if (!contains(key)) return false;
        set(key, count(key) - 1);
        return true;
    }

    public int size() {
        return c[1];
    }

    // index = 0 ... n - 1
    public int get(int index) {
        int cur = 1;
        while (cur < n) {
            if (c[cur << 1] >= index + 1) {
                cur = cur << 1;
            } else {
                index -= c[cur << 1];
                cur = cur << 1 | 1;
            }
        }
        return cur - n + start;
    }

    public int first() {
        int cur = 1;
        while (cur < n) {
            cur = (c[cur << 1] > 0) ? (cur << 1) : (cur << 1 | 1);
        }
        return cur - n + start;
    }

    public int last() {
        int cur = 1;
        while (cur < n) {
            cur = (c[cur << 1 | 1] > 0) ? (cur << 1 | 1) : (cur << 1);
        }
        return cur - n + start;
    }

    public int lowerCount(int key) {
        int l = 0 + n, r = key - start + n, sum = 0; // [l, r)
        for (; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) sum += c[l++];
            if ((r & 1) != 0) sum += c[--r];
        }
        return sum;
    }

    public int higherCount(int key) {
        return size() - lowerCount(key) - count(key);
    }

    public boolean contains(int key) {
        return count(key) > 0;
    }

    public Integer ceiling(int key) {
        int id = lowerCount(key);
        return id >= size() ? null : get(id);
    }

    public Integer floor(int key) {
        int id = size() - higherCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public Integer lower(int key) {
        int id = lowerCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public Integer higher(int key) {
        int id = size() - higherCount(key);
        return id >= size() ? null : get(id);
    }

    // return the first index whose value >= target
    // if this value doesn't exist, return index = size()
    public int lowerBound(int target) {
        return lowerCount(target);
    }

    // return the first index whose value > target
    // if this value doesn't exist, return index = size()
    public int upperBound(int target) {
        return size() - higherCount(target);
    }


    public int index(int key) {
        return lowerCount(key);
    }
}

abstract class Calculator<T> {
    abstract protected T parseNumber(String s);

    abstract protected T add(T a, T b);

    abstract protected T minus(T a, T b);

    abstract protected T multiply(T a, T b);

    abstract protected T divide(T a, T b);


    public T calculate(String s, T defaultValue) {
        if (s == null) {
            return defaultValue;
        }
        s = s.replaceAll("\\s+", "");
        if (s.length() == 0) {
            return defaultValue;
        }
        pos = 0;
        return parse(s, defaultValue);
    }

    private int pos;

    private T parse(String s, T defaultValue) {
        Stack<T> st = new Stack<>();
        T cur = defaultValue;
        char sign = '+';

        while (pos < s.length()) {
            if (s.charAt(pos) == '(') {
                // skip the '('
                pos++;
                cur = parse(s, defaultValue);
                // skip the ')'
                pos++;
            } else {
                StringBuilder number = new StringBuilder();
                while (pos < s.length()) {
                    char ch = s.charAt(pos);
                    if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
                        break;
                    }
                    number.append(ch);
                    pos++;
                }
                cur = parseNumber(number.toString());
            }
            if (sign == '+') st.push(cur);
            if (sign == '-') st.push(minus(defaultValue, cur));
            if (sign == '*') st.push(multiply(st.pop(), cur));
            if (sign == '/') st.push(divide(st.pop(), cur));

            if (pos >= s.length() || s.charAt(pos) == ')') break;
            sign = s.charAt(pos++);
        }
        T ans = defaultValue;
        while (!st.empty()) {
            ans = add(ans, st.pop());
        }
        return ans;
    }
}

// Including topological sort.
// Tested by Leetcode 1203 and 1857
class Graph<E> {
    Map<E, List<E>> adj = new HashMap<>();
    List<E> emptyList = new ArrayList<>();

    // NOTE: remember to call addNode() to add single nodes
    public void addNode(E node) {
        adj.computeIfAbsent(node, k -> new ArrayList<>());
    }

    // directed edge x -> y
    public void link(E x, E y) {
        adj.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
    }

    public List<E> getChildren(E x) {
        return adj.getOrDefault(x, emptyList);
    }

    public Set<E> getAllNodes() {
        return adj.keySet();
    }

    private static final int TEMPORARY = 1;
    private static final int PERMANENT = 2;

    // x -> y means x should be processed before y
    // The input is a directed graph.
    // 1. return null, means there is a cycle inside the graph, or the input is
    // invalid.
    // 2. return List<Node> as the result.
    public List<E> topologicalSort() {
        Map<E, Integer> vst = new HashMap<>();
        List<E> result = new ArrayList<>();
        for (E x : getAllNodes()) {
            if (!topologicalSort(x, vst, result)) {
                return null;
            }
        }
        Collections.reverse(result);
        return result;
    }

    private boolean topologicalSort(E root, Map<E, Integer> vst, List<E> result) {
        if (vst.getOrDefault(root, 0) == PERMANENT) {
            return true;
        }
        if (vst.getOrDefault(root, 0) == TEMPORARY) { // found cycle
            return false;
        }

        vst.put(root, TEMPORARY);
        for (E child : getChildren(root)) {
            if (!topologicalSort(child, vst, result)) {
                return false;
            }
        }
        result.add(root);
        vst.put(root, PERMANENT);
        return true;
    }
}

class GraphIntegerFaster {
    public List<Integer>[] adj;

    public GraphIntegerFaster(int n) {
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    }

    // directed edge x -> y
    public void link(int x, int y) {
        adj[x].add(y);
    }

    public List<Integer> getChildren(int x) {
        return adj[x];
    }

    private static final int TEMPORARY = 1;
    private static final int PERMANENT = 2;

    // x -> y means x should be processed before y
    // The input is a directed graph.
    // 1. return null, means there is a cycle inside the graph, or the input is
    // invalid.
    // 2. return List<Node> as the result.
    public List<Integer> topologicalSort() {
        //Map<E, Integer> vst = new HashMap<>();
        int n = adj.length;
        int[] vst = new int[n];
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            if (!topologicalSort(x, vst, result)) {
                return null;
            }
        }
        Collections.reverse(result);
        return result;
    }

    private boolean topologicalSort(int root, int[] vst, List<Integer> result) {
        if (vst[root] == PERMANENT) {
            return true;
        }
        if (vst[root] == TEMPORARY) { // found cycle
            return false;
        }

        vst[root] = TEMPORARY;
        for (int child : getChildren(root)) {
            if (!topologicalSort(child, vst, result)) {
                return false;
            }
        }
        result.add(root);
        vst[root] = PERMANENT;
        return true;
    }
}

class Dijkstra {
    public static class Edge {
        long to;
        long w;

        public Edge(long to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    public Map<Long, List<Edge>> adj = new HashMap<>();

    public void link(long x, long y, long w) {
        adj.computeIfAbsent(x, k -> new ArrayList<>()).add(new Edge(y, w));
    }

    public Map<Long, Long> shortestPath(long src) {
        Map<Long, Long> dist = new HashMap<>();
        Set<Long> vst = new HashSet<>();

        PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        q.add(new Edge(src, 0L));
        dist.put(src, 0L);

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            long u = cur.to;
            if (vst.contains(u)) continue;
            long w = cur.w;
            vst.add(u);
            if (adj.containsKey(u)) {
                for (Edge e : adj.get(u)) {
                    if (dist.getOrDefault(e.to, Long.MAX_VALUE) > w + e.w) {
                        dist.put(e.to, w + e.w);
                        q.add(new Edge(e.to, w + e.w));
                    }
                }
            }
        }
        return dist;
    }
}

class DijkstraLongFaster {
    public static class Edge {
        int to;
        long w;

        public Edge(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    public static final long INF = Long.MAX_VALUE / 4;
    public List<Edge>[] adj;

    public DijkstraLongFaster(int n) {
        this.adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    }

    public void link(int x, int y, long w) {
        adj[x].add(new Edge(y, w));
    }

    public long[] shortestPath(int src) {
        int n = adj.length;
        long[] dist = new long[n];
        boolean[] vst = new boolean[n];
        Arrays.fill(dist, INF);

        PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        q.add(new Edge(src, 0));
        dist[src] = 0;

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            if (vst[cur.to]) continue;
            int u = cur.to;
            long w = cur.w;
            vst[u] = true;

            for (Edge e : adj[u]) {
                if (!vst[e.to] && dist[e.to] > w + e.w) {
                    dist[e.to] = w + e.w;
                    q.add(new Edge(e.to, w + e.w));
                }
            }
        }
        return dist;
    }

    // NOTE: w of each edge must be the same.
    public long[] bfs(int src) {
        int n = adj.length;
        long[] dist = new long[n];
        boolean[] vst = new boolean[n];
        Arrays.fill(dist, INF);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        dist[src] = 0;
        vst[src] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            long w = dist[u];
            for (Edge e : adj[u]) {
                if (!vst[e.to]) {
                    dist[e.to] = w + e.w;
                    vst[e.to] = true;
                    q.add(e.to);
                }
            }
        }
        return dist;
    }
}

// T: type of node id
// E: type of edge id
// Tested by LC 2097
class EulerPath<T, E> {
    Map<T, List<Object[]>> adj = new HashMap<T, List<Object[]>>();
    private Map<T, Integer> curr;
    private Set<E> vst;
    private List<E> result;

    // 1. For directed graph, each edge has unique edgeId
    // 2. For undirected graph, you need to call link() twice with the same
    // edgeId, as below:
    // addEdge(x, y, 1);
    // addEdge(y, x, 1);
    public void addEdge(T x, T y, E edgeId) {
        adj.computeIfAbsent(x, k -> new ArrayList<>()).add(new Object[]{y, edgeId});
    }

    private void dfs(T x) {
        List<Object[]> children = adj.get(x);
        if (children != null) {
            while (curr.get(x) < children.size()) {
                int index = curr.get(x);
                curr.put(x, index + 1);
                Object[] edge = children.get(index);
                T y = (T) edge[0];
                E edgeId = (E) edge[1];
                if (vst.contains(edgeId)) {
                    continue;
                }
                vst.add(edgeId);
                dfs(y);
                result.add(edgeId);
            }
        }
    }

    public List<E> getEulerPath() {
        T start = adj.keySet().iterator().next();
        return getEulerPath(start);
    }

    // undirected graph: all degree (number of edges for each node) are EVEN or only one node has ODD degree (start node)
    // directed graph: all nodes have out-degree equal to in-degree, or only one node has out-degree = in-degree + 1 (start node)
    public List<E> getEulerPath(T start) {
        vst = new HashSet<E>();
        result = new ArrayList<E>();
        curr = new HashMap<T, Integer>();
        for (T x : adj.keySet()) {
            curr.put(x, 0);
        }
        dfs(start);
        Collections.reverse(result);
        return result;
    }
}

class TreapSet<E> {
    private class Node {
        E key;
        int priority, count, total;
        Node left, right, pnt;

        public Node(E key, int priority, Node pnt) {
            this.key = key;
            this.priority = priority;
            this.pnt = pnt;
            this.count = 1;
            this.total = 1;
        }
    }

    private static final Random RANDOM = new Random();
    private final Comparator<? super E> comparator;
    private Node root;

    public TreapSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return root == null ? 0 : root.total;
    }

    private int leftRank(Node p) {
        return p.left != null ? p.left.total : 0;
    }

    private int rightRank(Node p) {
        return p.right != null ? p.right.total : 0;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        if ((x.right = y.left) != null) y.left.pnt = x;
        y.pnt = x.pnt;
        if (x == root) root = y;
        else if (x == x.pnt.left) x.pnt.left = y;
        else x.pnt.right = y;
        y.left = x;
        x.pnt = y;
        x.total = leftRank(x) + rightRank(x) + x.count;
        y.total = leftRank(y) + rightRank(y) + y.count;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        if ((x.left = y.right) != null) y.right.pnt = x;
        y.pnt = x.pnt;
        if (x == root) root = y;
        else if (x == x.pnt.right) x.pnt.right = y;
        else x.pnt.left = y;
        y.right = x;
        x.pnt = y;
        x.total = leftRank(x) + rightRank(x) + x.count;
        y.total = leftRank(y) + rightRank(y) + y.count;
    }

    public void add(E key) {
        if (root == null) {
            root = new Node(key, RANDOM.nextInt(), null);
            return;
        }
        Node x = root, p = null;
        while (x != null) {
            ++((p = x).total);
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                ++(x.count);
                return;
            }
        }

        x = new Node(key, RANDOM.nextInt(), p);
        if (comparator.compare(key, p.key) < 0) p.left = x;
        else p.right = x;
        while ((p = x.pnt) != null && p.priority < x.priority) {
            if (p.left == x) rotateRight(p);
            else rotateLeft(p);
        }
    }

    public void remove(E key) {
        Node x = root, p = null;
        while (x != null) {
            --(x.total);
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                if (--(x.count) > 0) return;
                break;
            }
        }
        if (x == null) return;
        while (x.left != null || x.right != null) {
            if (x.left == null || (x.right != null && x.right.priority > x.left.priority)) {
                rotateLeft(x);
            } else {
                rotateRight(x);
            }
        }
        if ((p = x.pnt) != null) {
            if (p.left == x) p.left = null;
            else p.right = null;
        } else {
            root = null;
        }
        // delete x;
    }

    // number of elements smaller than key
    public int lowerCount(E key) {
        int sum = 0;
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                sum += leftRank(x) + x.count;
                x = x.right;
            } else {
                sum += leftRank(x);
                break;
            }
        }
        return sum;
    }

    // number of elements larger than key
    public int higherCount(E key) {
        //return size() - lowerCount(key) - count(key);
        int sum = 0;
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp > 0) x = x.right;
            else if (cmp < 0) {
                sum += rightRank(x) + x.count;
                x = x.left;
            } else {
                sum += rightRank(x);
                break;
            }
        }
        return sum;
    }

    public int index(E key) {
        return lowerCount(key);
    }

    public E get(int index) // index = 0 ... size - 1
    {
        Node p = root;
        ++index;
        while (true) {
            int t = leftRank(p);
            if (index <= t) p = p.left;
            else {
                if ((index -= t + p.count) <= 0) break;
                p = p.right;
            }
        }
        return p.key;
    }

    public int count(E key) {
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                x = x.right;
            } else {
                return x.count;
            }
        }
        return 0;
    }

    public E first() {
        if (root == null) return null;
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public E last() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public boolean contains(E key) {
        return count(key) > 0;
    }

    public E ceiling(E key) {
        int id = lowerCount(key);
        return id >= size() ? null : get(id);
    }

    public E floor(E key) {
        int id = size() - higherCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public E lower(E key) {
        int id = lowerCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public E higher(E key) {
        int id = size() - higherCount(key);
        return id >= size() ? null : get(id);
    }

    // return the first index whose value >= target
    // if this value doesn't exist, return index = size()
    public int lowerBound(E target) {
        return lowerCount(target);
    }

    // return the first index whose value > target
    // if this value doesn't exist, return index = size()
    public int upperBound(E target) {
        return size() - higherCount(target);
    }

    public List<E> keys() {
        List<E> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node x, List<E> list) {
        if (x == null) return;
        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
    }
}

class ArraySum {
    long[] sum;
    int n;

    // nums index starts from 0 to nums.length - 1;
    public ArraySum(int[] nums) {
        n = nums.length;
        sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public ArraySum(long[] nums) {
        n = nums.length;
        sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    // [l, r], index starts from 0
    public long getSum(int l, int r) {
        // error handling
        if (l > r || !(l >= 0 && l < n && r >= 0 && r < n)) return 0L;
        return sum[r + 1] - sum[l];
    }
}

class TwoDArraySum {
    int[][] sum;
    int m, n;

    // nums index starts from 0
    public TwoDArraySum(final int[][] nums) {
        m = nums.length;
        n = nums[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + nums[i - 1][j - 1];
            }
        }
    }

    // [(x1, y1), (x2, y2)], index starts from 0
    // x1 <= x2 and y1 <= y2
    public int getSum(int x1, int y1, int x2, int y2) {
        if (!(x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && x2 >= 0 && x2 < m && y2 >= 0 && y2 < n) || x1 > x2 || y1 > y2)
            return 0;
        return sum[x2 + 1][y2 + 1] - sum[x2 + 1][y1] - sum[x1][y2 + 1] + sum[x1][y1];
    }
}

class ArrayUtil {
    static void fill(int[] array, int defaultValue) {
        Arrays.fill(array, defaultValue);
    }

    static void fill(int[][] array, int defaultValue) {
        for (int[] a1 : array) {
            Arrays.fill(a1, defaultValue);
        }
    }

    static void fill(int[][][] array, int defaultValue) {
        for (int[][] a2 : array) {
            for (int[] a1 : a2) {
                Arrays.fill(a1, defaultValue);
            }
        }
    }

    static void fill(int[][][][] array, int defaultValue) {
        for (int[][][] a3 : array) {
            for (int[][] a2 : a3) {
                for (int[] a1 : a2) {
                    Arrays.fill(a1, defaultValue);
                }
            }
        }
    }

    static void fill(int[][][][][] array, int defaultValue) {
        for (int[][][][] a4 : array) {
            for (int[][][] a3 : a4) {
                for (int[][] a2 : a3) {
                    for (int[] a1 : a2) {
                        Arrays.fill(a1, defaultValue);
                    }
                }
            }
        }
    }

    static void fill(int[][][][][][] array, int defaultValue) {
        for (int[][][][][] a5 : array) {
            for (int[][][][] a4 : a5) {
                for (int[][][] a3 : a4) {
                    for (int[][] a2 : a3) {
                        for (int[] a1 : a2) {
                            Arrays.fill(a1, defaultValue);
                        }
                    }
                }
            }
        }
    }

    static void fill(long[] array, long defaultValue) {
        Arrays.fill(array, defaultValue);
    }

    static void fill(long[][] array, long defaultValue) {
        for (long[] a1 : array) {
            Arrays.fill(a1, defaultValue);
        }
    }

    static void fill(long[][][] array, long defaultValue) {
        for (long[][] a2 : array) {
            for (long[] a1 : a2) {
                Arrays.fill(a1, defaultValue);
            }
        }
    }

    static void fill(long[][][][] array, long defaultValue) {
        for (long[][][] a3 : array) {
            for (long[][] a2 : a3) {
                for (long[] a1 : a2) {
                    Arrays.fill(a1, defaultValue);
                }
            }
        }
    }

    static void fill(long[][][][][] array, long defaultValue) {
        for (long[][][][] a4 : array) {
            for (long[][][] a3 : a4) {
                for (long[][] a2 : a3) {
                    for (long[] a1 : a2) {
                        Arrays.fill(a1, defaultValue);
                    }
                }
            }
        }
    }

    static void fill(long[][][][][][] array, long defaultValue) {
        for (long[][][][][] a5 : array) {
            for (long[][][][] a4 : a5) {
                for (long[][][] a3 : a4) {
                    for (long[][] a2 : a3) {
                        for (long[] a1 : a2) {
                            Arrays.fill(a1, defaultValue);
                        }
                    }
                }
            }
        }
    }
}

// Tested by Leetcode 698
class DivideSticks {
    static boolean[] vst;
    static int[] nums;
    static int targetNumOfBlocks;
    static int targetBlockSum;

    // divide int[] sticks into K blocks, and each block has sum == blockSum
    static boolean canPartitionKBlocks(final int[] sticks, int numOfBlocks, int blockSum) {
        vst = new boolean[sticks.length];
        nums = Arrays.copyOf(sticks, sticks.length);
        Arrays.sort(nums);
        targetNumOfBlocks = numOfBlocks;
        targetBlockSum = blockSum;
        return dfs(0, nums.length - 1, 0, 0);
    }

    private static boolean dfs(int depth, int startIndex, int currentSum, int currentNumOfBlocks) {
        if (depth == nums.length) return currentNumOfBlocks == targetNumOfBlocks;
        if (currentNumOfBlocks >= targetNumOfBlocks) return false;
        if (nums[0] + currentSum > targetBlockSum) return false; // TODO

        int lastIndex = -1;
        for (int i = startIndex; i >= 0; i--) {
            if (vst[i]) continue;
            if (lastIndex >= 0 && nums[lastIndex] == nums[i]) continue; // skip duplicate
            if (nums[i] + currentSum > targetBlockSum) continue; // TODO

            vst[i] = true;
            if (nums[i] + currentSum == targetBlockSum) { // TODO
                if (dfs(depth + 1, nums.length - 1, 0, currentNumOfBlocks + 1)) {
                    return true;
                }
            } else {
                if (dfs(depth + 1, i - 1, nums[i] + currentSum, currentNumOfBlocks)) {
                    return true;
                }
            }
            vst[i] = false;

            lastIndex = i;
            if (currentSum == 0) return false;
        }
        return false;
    }
}

class DivideSticksDP {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += nums[i];
        }
        if (totalSum % k != 0) return false;
        int targetSum = totalSum / k;

        int[] sum = new int[1 << n];
        for (int s = 0; s < (1 << n); s++) {
            for (int i = 0; i < n; i++) {
                if (((s >>> i) & 1) != 0) {
                    sum[s] += nums[i];
                }
            }
        }

        int mask = (1 << n) - 1;
        boolean[][] dp = new boolean[k + 1][1 << n];
        for (int i = 0; i <= k; i++) Arrays.fill(dp[i], false);

        dp[0][0] = true;
        for (int i = 0; i < k; i++) {
            for (int s = 0; s < (1 << n); s++) {
                if (!dp[i][s]) continue;
                int remain = ~s & mask;
                for (int sub = remain; sub > 0; sub = (sub - 1) & remain) {
                    if (sum[sub] == targetSum) {
                        dp[i + 1][s | sub] = true;
                    }
                }
            }
        }
        return dp[k][(1 << n) - 1];
    }
}

class DoubleUtils {
    private static final double EPS = 1e-8;

    public static int compare(double a, double b) {
        if (a - b > EPS) return 1;
        else if (a - b < -EPS) return -1;
        else return 0;
    }

    public static boolean isInteger(double a) {
        return compare(floor(a), a) == 0;
    }

    public static double floor(double a) {
        return Math.floor(a + EPS);
    }

    public static double ceil(double a) {
        return Math.ceil(a - EPS);
    }
}

// Better to use this when length of string >= 2^11
class StableStringHash {
    static class SingleStringHash {
        long[] h;
        long[] pow;
        final long SEED;
        final long MOD;

        public SingleStringHash(String s, long SEED, long MOD) { // example: SEED = 131L;
            this.SEED = SEED;
            this.MOD = MOD;
            int n = s.length();
            h = new long[n + 1];
            pow = new long[n + 1];
            pow[0] = 1;
            for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED) % MOD;
            //h[i] = hash[s[0...i - 1]]
            h[0] = 0;
            for (int i = 1; i <= n; i++) {
                h[i] = (h[i - 1] * SEED + s.charAt(i - 1)) % MOD;
            }
        }

        // hash[s[l....r]]
        public long hash(int l, int r) {
            //return ((h[r + 1] - h[l] * pow[r - l + 1]) % MOD + MOD) % MOD;
            long ret = (h[r + 1] - h[l] * pow[r - l + 1]) % MOD;
            return ret >= 0 ? ret : ret + MOD;
        }
    }

    private SingleStringHash hash1, hash2;
    static final long SEED1 = 31L, SEED2 = 131L;
    static final long MOD = 1_000_000_007L;

    public StableStringHash(String s) {
        hash1 = new SingleStringHash(s, SEED1, MOD);
        hash2 = new SingleStringHash(s, SEED2, MOD);
    }

    public long hash(int l, int r) {
        return (hash1.hash(l, r) << 32) | (hash2.hash(l, r) << 32 >>> 32);
    }

    public static long hashCode(String s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length(); i++) {
            h1 = (h1 * SEED1 + s.charAt(i)) % MOD;
            h2 = (h2 * SEED2 + s.charAt(i)) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    public static long hashCode(char[] s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length; i++) {
            h1 = (h1 * SEED1 + s[i]) % MOD;
            h2 = (h2 * SEED2 + s[i]) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }
}

// NOTE: Faster than StableStringHash, but has only one SEED, not stable
// This class cannot pass Leetcode 1923
class StringHash {
    long[] h;
    long[] pow;
    static final long SEED = 31L;

    public StringHash(String s) {
        int n = s.length();
        h = new long[n + 1];
        pow = new long[n + 1];
        pow[0] = 1;
        for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED);
        //h[i] = hash[s[0...i - 1]]
        h[0] = 0;
        for (int i = 1; i <= n; i++) {
            h[i] = (h[i - 1] * SEED + s.charAt(i - 1));
        }
    }

    // hash[s[l....r]]
    public long hash(int l, int r) {
        return (h[r + 1] - h[l] * pow[r - l + 1]);
    }

    public static long hashCode(String s) {
        long h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = h * SEED + s.charAt(i);
        }
        return h;
    }

    public static long hashCode(char[] s) {
        long h = 0;
        for (int i = 0; i < s.length; i++) {
            h = h * SEED + s[i];
        }
        return h;
    }
}

class StableIntegerArrayHash {
    static class SingleIntegerArrayHash {
        long[] h;
        long[] pow;
        final long SEED;
        final long MOD;

        public SingleIntegerArrayHash(int[] s, long SEED, long MOD) { // example: SEED = 131L;
            this.SEED = SEED;
            this.MOD = MOD;
            int n = s.length;
            h = new long[n + 1];
            pow = new long[n + 1];
            pow[0] = 1;
            for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED) % MOD;
            //h[i] = hash[s[0...i - 1]]
            h[0] = 0;
            for (int i = 1; i <= n; i++) {
                h[i] = (h[i - 1] * SEED + s[i - 1]) % MOD;
                if (h[i] < 0) h[i] += MOD;
            }
        }

        // hash[s[l....r]]
        public long hash(int l, int r) {
//            return ((h[r + 1] - h[l] * pow[r - l + 1]) % MOD + MOD) % MOD;
            long ret = (h[r + 1] - h[l] * pow[r - l + 1]) % MOD;
            return ret >= 0 ? ret : ret + MOD;
        }
    }

    private SingleIntegerArrayHash hash1, hash2;
    static final long SEED1 = 31L, SEED2 = 131L;
    static final long MOD = 1_000_000_007L; // or 1_000_000_123L

    public StableIntegerArrayHash(int[] s) {
        hash1 = new SingleIntegerArrayHash(s, SEED1, MOD);
        hash2 = new SingleIntegerArrayHash(s, SEED2, MOD);
    }

    public long hash(int l, int r) {
        return (hash1.hash(l, r) << 32) | (hash2.hash(l, r) << 32 >>> 32);
    }

    public static long hashCode(int[] s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length; i++) {
            h1 = (h1 * SEED1 + s[i]) % MOD;
            if (h1 < 0) h1 += MOD;
            h2 = (h2 * SEED2 + s[i]) % MOD;
            if (h2 < 0) h2 += MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    public static long hashCode(List<Integer> s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.size(); i++) {
            h1 = (h1 * SEED1 + s.get(i)) % MOD;
            if (h1 < 0) h1 += MOD;
            h2 = (h2 * SEED2 + s.get(i)) % MOD;
            if (h2 < 0) h2 += MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }
}

class LongCalculator extends Calculator<Long> {
    public Long parseNumber(String s) {
        return Long.parseLong(s);
    }

    public Long add(Long a, Long b) {
        return a + b;
    }

    public Long minus(Long a, Long b) {
        return a - b;
    }

    public Long multiply(Long a, Long b) {
        return a * b;
    }

    public Long divide(Long a, Long b) {
        return a / b;
    }
}

class RMQ { // NOTE!!! query return index, not value
    // tested by LC 1438,239
    final long[] a; // input data array copy
    int[][] d;

    int[] log2;
    boolean useMax;

    private static long[] intArrayToLongArray(int[] nums) {
        long[] ret = new long[nums.length];
        for (int i = 0; i < nums.length; i++) ret[i] = nums[i];
        return ret;
    }

    // useMax == true means RMQMax
    public RMQ(int[] input, boolean useMax) {
        this(intArrayToLongArray(input), useMax);
    }

    public RMQ(long[] input, boolean useMax) {
        int n = input.length;
        this.a = input;
        this.log2 = Utils.preprocessLog2(n);
        this.useMax = useMax;
        this.d = new int[log2[n] + 1][n];
        for (int i = 0; i < n; i++) d[0][i] = i;
        for (int j = 1; (1 << j) <= n; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
                if (useMax) {
                    d[j][i] = a[d[j - 1][i]] >= a[d[j - 1][i + (1 << (j - 1))]] ? d[j - 1][i] : d[j - 1][i + (1 << (j - 1))];
                } else {
                    d[j][i] = a[d[j - 1][i]] <= a[d[j - 1][i + (1 << (j - 1))]] ? d[j - 1][i] : d[j - 1][i + (1 << (j - 1))];
                }
    }

    // i <= j, and index starts from 0.
    public int query(int i, int j) { // NOTE!!! return index, not value
        int k = log2[j - i + 1];
        if (useMax) return a[d[k][i]] >= a[d[k][j - (1 << k) + 1]] ? d[k][i] : d[k][j - (1 << k) + 1];
        else return a[d[k][i]] <= a[d[k][j - (1 << k) + 1]] ? d[k][i] : d[k][j - (1 << k) + 1];
    }
}

class StronglyConnectedComponent {
    // Return sccId for each v. sccId is the root node id for the scc.
    // NOTE: if v is not in scc, then sccId[v] == v
    public static int[] findScc(List<Integer>[] g) {
        int n = g.length;
        boolean[] vst = new boolean[n];
        List<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            if (!vst[i]) {
                dfs(g, i, vst, order);
            }

        // Build reverse graph
        List<Integer>[] gt = new List[n];
        for (int i = 0; i < n; i++) gt[i] = new ArrayList<Integer>();

        for (int i = 0; i < n; i++)
            if (g[i] != null) {
                for (int y : g[i]) {
                    gt[y].add(i);
                }
            }

        int[] sccId = new int[n];
        Arrays.fill(vst, false);
        for (int i = n - 1; i >= 0; i--)
            if (!vst[order.get(i)]) {
                dfsGT(gt, order.get(i), vst, order.get(i), sccId);
            }
        return sccId;
    }

    private static void dfs(List<Integer>[] g, int x, boolean[] vst, List<Integer> order) {
        if (vst[x]) return;
        vst[x] = true;
        if (g[x] != null) {
            for (int y : g[x]) {
                dfs(g, y, vst, order);
            }
        }
        order.add(x);
    }

    private static void dfsGT(List<Integer>[] gt, int x, boolean[] vst, int sccRoot, int[] sccId) {
        if (vst[x]) return;
        sccId[x] = sccRoot;
        vst[x] = true;
        if (gt[x] != null) {
            for (int y : gt[x]) {
                dfsGT(gt, y, vst, sccRoot, sccId);
            }
        }
    }
}


// Tested by 2827
// Similar: 1012, 2376, 902, 2719, 2801, 357, 1215, 1397
class DigitDP {
    static final boolean ENABLE_DEBUG_MODE = false; // TODO: set it to true if you want to print more debug info
    static final long UNSET = -1;
    // dp[isStart][hasLimit][pos] | [odd][even][r]
    // (isStart, hasLimit, pos) is generic, (odd, even, r) is use case specific
    long[][][][][][] dp;
    int K;

    public long digitDP(long num, int k) {
        String numString = String.valueOf(num); // TODO
        this.dp = new long[2][2][numString.length()][numString.length() + 1][numString.length() + 1][k];
        this.K = k;

        if (ENABLE_DEBUG_MODE) System.out.println("num = " + numString);
        ArrayUtil.fill(dp, UNSET);
        long ans = dfs(new StringBuilder(), numString, 1, 1, 0, 0, 0, 0);
        if (ENABLE_DEBUG_MODE) System.out.println("---------------------\n");
        return ans;
    }

    long dfs(StringBuilder curResult, String s, int isStart, int hasLimit, int pos, int odd, int even, int r) {
        if (pos == s.length()) {
            if (isStart == 1) return 0; // NOTE: special check for num == 0. TODO
            if (r == 0 && odd == even) { // TODO
                if (ENABLE_DEBUG_MODE) System.out.println("result = " + curResult);
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[isStart][hasLimit][pos][odd][even][r] != UNSET) return dp[isStart][hasLimit][pos][odd][even][r];

        int start = 0;
        int end = (hasLimit == 1) ? s.charAt(pos) - '0' : 9; // TODO: check if it is binary string or not

        long ans = 0;
        for (int digit = start; digit <= end; digit++) {
            if (isStart == 1 && digit == 0) { // NOTE: handle leading zeros
                // [0, 999..9]
                // [0， 10^(len(s) - pos - 1) - 1]
                ans += dfs(new StringBuilder(), s, 1, 0, pos + 1, 0, 0, 0);
                continue;
            }
            int newHasLimit = (hasLimit == 1 && digit == s.charAt(pos) - '0') ? 1 : 0;

            int newOdd = odd + (digit % 2); // TODO
            int newEven = even + (digit % 2 == 0 ? 1 : 0); // TODO
            int newR = (isStart == 1 ? digit % this.K : (r * 10 + digit) % this.K); // TODO;

            if (ENABLE_DEBUG_MODE) curResult.append(digit);
            ans += dfs(curResult, s, 0, newHasLimit, pos + 1, newOdd, newEven, newR);
            if (ENABLE_DEBUG_MODE) curResult.deleteCharAt(curResult.length() - 1);

        }
        return dp[isStart][hasLimit][pos][odd][even][r] = ans;
    }
}

class Printer {
    static boolean ENABLE_LOCAL_PRINT = false;

    static void println(String x) {
        if (!ENABLE_LOCAL_PRINT) return; // do nothing
        System.out.println(x);
    }

    static void printf(String format, Object... args) {
        if (!ENABLE_LOCAL_PRINT) return; // do nothing
        System.out.printf(format, args);
    }
}


class Solution {
    private static final int INF = 1_000_000_001;
    private static final long MOD = 1_000_000_007L;

    private static final int UNSET = -131;
    private static final int[] DX = {-1, 0, 1, 0}; // up, right, down, left
    private static final int[] DY = {0, 1, 0, -1};
    private static final double EPS = 1e-8;
    private static final long SEED = 31L;


    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;

        Map<Long, Integer> count = new HashMap<>();
        for (String s : words) {
            StableStringHash hash = new StableStringHash(s);

            for (int i = 0; i < s.length(); i++) {
                long l = hash.hash(0, i);
                long r = hash.hash(s.length() - i - 1, s.length() - 1);
                if (l == r) {
                    ans += count.getOrDefault(l, 0);
                }
            }
            long t = hash.hash(0, s.length() - 1);
            count.put(t, count.getOrDefault(t, 0) + 1);
        }
        return ans;
    }


    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Printer.ENABLE_LOCAL_PRINT = true;
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        System.out.println((System.currentTimeMillis() - startTime) + "ms");
    }
}