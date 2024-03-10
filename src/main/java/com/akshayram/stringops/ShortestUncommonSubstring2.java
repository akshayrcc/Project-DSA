package com.akshayram.stringops;

import java.util.*;

public class ShortestUncommonSubstring2 {

    public static void main(String[] args) {
        String[] arr = new String[]{"hdrasjp", "mtkqfukubx", "zffbigargurdcg", "fgkxqwthvna", "eeiqtcvnkpjfuk", "lylydjhmqqlhhlhh", "tnlwmfwafqywkqkf", "lnbo", "fcjqnigcycelssconoum", "cqqtf", "gyygydmlljaxc", "l", "iyvtimf", "zzmoagybh", "wsgguexqcpifoujpbn", "nocglmnmgidaxmjdi", "ubaacl", "ytafnbwnxvr", "zgggvipvhdi", "aefrl", "iwqgc", "picpvgylwlogtavrz", "wdphntsgonsg", "usw", "pzwi", "gvqbzicrph", "nrbtusdjeudinzmkfocw", "dnximo", "ltouxgjlahodcyaacdrx", "lpqrodzsypnwdfeg", "fgudtvemqazdcum", "wmusjqtlywocsdq", "giqdacefjrig", "uyupew", "lkzjbmlmqerlaqzu", "dsrzxacjqsgt", "fapaceyqjnrddqvde", "qxxvxiuhwxsqqusvfgpt", "phhqoczpktrilgaajn", "nqdphsvgf", "xnzzipxplqidq", "yyzudhdugvweqs", "iwwqbijevbwtiuqpz", "srbkseuaewrjmsovuof"};
        System.out.println("Started");
        System.out.println("Result is: " + Arrays.toString(shortestSubstrings(arr)));
    }


    public static String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] ret = new String[n];
        for (int i = 0; i < n; i++) {
            String best = null;
            for (int j = 0; j < arr[i].length(); j++) {
                inner:
                for (int k = j + 1; k <= arr[i].length(); k++) {
                    String t = arr[i].substring(j, k);
                    for (int l = 0; l < n; l++) {
                        if (l == i) continue;
                        if (arr[l].contains(t)) {
                            continue inner;
                        }
                    }
                    if (best == null || t.length() < best.length()
                            || (t.length() == best.length() && t.compareTo(best) < 0)
                    ) {
                        best = t;
                    }
                }
            }
            if (best == null) best = "";
            ret[i] = best;
        }
        return ret;
    }
}
