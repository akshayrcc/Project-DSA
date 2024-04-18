package com.akshayram.contests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SomeCoins {
    public static void main(String[] args) {
        SomeCoins sc = new SomeCoins();
        int[] coins = {4, 8};
        int k = 55789;
        System.out.println(sc.findKthSmallest(coins, k));
    }

    //TLE
    public long findKthSmallest1(int[] coins, int k) {
        Arrays.sort(coins);
        //get small distinct coins
        List<Long> coinsSmall = new ArrayList<>();
        boolean shouldAdd = false;
        for (int cn : coins) {
            shouldAdd = true;
            //check if any existing coin denomination can devide this new coin
            for (int i = 0; i < coinsSmall.size(); i++) {
                if (coinsSmall.contains((long) cn)) {
                    shouldAdd = false;
                    break;
                }
                if (cn % coinsSmall.get(i) == 0) {
                    shouldAdd = false;
                }
            }
            if (shouldAdd) {
                coinsSmall.add((long) cn);
            }
        }

        // List<Integer> primes = new ArrayList<>(List.of(2, 3, 5, 7, 11, 13, 17, 19, 23));
        // for(int cn: coins){
        //     if(primes.contains(cn)){
        //         coinsSmall.add((long)cn);
        //     }
        // }
        // coinsSmall.forEach(num -> System.out.print(num + " "));

        int count = 0;
        int N = coinsSmall.size();
        long amount = coinsSmall.getFirst();
        while (count != k) {
            N = coinsSmall.size();
            for (int i = 0; i < N; i++) {
                if (amount % coinsSmall.get(i) == 0) {
                    count++;
                    coinsSmall.add(amount);
                    break;
                }
            }
            amount++;
        }
        return --amount;
    }

//    public long findKthSmallest(int[] coins, int k) {
//        long left = k, right = 50000000000L;
//        while (left < right) {
//            long mid = (left + right) / 2;
//            if (findKthSmallest(coins, mid, 1, 0, 0) < k) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return left;
//    }
//
//    private long findKthSmallest(int[] coins, long k, long v, int index, int flag) {
//        return index < coins.length ? findKthSmallest(coins, k, v, index + 1, flag) + findKthSmallest(coins, k, v * coins[index] / BigInteger.valueOf(v).gcd(BigInteger.valueOf(coins[index])).longValue(), index + 1, flag + 1) : flag > 0 ? flag % 2 > 0 ? k / v : -k / v : 0;
//    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int t = a;
            a = b;
            b = t % a;
        }
        return a;
    }

    private int lcm(int a, int b) {
        int g = gcd(a, b);
        return a / g * b;
    }

    private long getRank(int[] coins, long val) {
        long ans = 0;
        int i, n = coins.length;
        for (i = 1; i < (1 << n); i++) {
            int count = 0, base = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    count++;
                    base = lcm(base, coins[j]);
                }
            }
            if (count % 2 == 1) {
                ans += val / base;
            } else {
                ans -= val / base;
            }
        }
        return ans;
    }

    public long findKthSmallest(int[] coins, int k) {
        long low = 1, high = 1000_000_000_000_000_000L;
        while (low < high) {
            long mid = (low + high) / 2;
            long rank = getRank(coins, mid);
            if (rank < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


}