package com.akshayram.util;

import java.util.HashMap;

/**
 * Counts the frequency of objects.
 * Change to extend TreeMap instead, if ordering of objects is required.
 * <p>
 * NOTE:  If `total` is needed, only use `increment(...)` for updates.
 */
public class CountMapInt<T> extends HashMap<T, Integer> {
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