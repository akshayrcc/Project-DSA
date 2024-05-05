package com.akshayram.stringops;

public class CompareVersions {
    //TC: O(N+M+max(N,M)), SC: O(N+M) where NNN and MMM are lengths of input strings.
    public int compareVersion(String v1, String v2) {
        String[] nums1 = v1.split("\\.");
        String[] nums2 = v2.split("\\.");
        int N1 = nums1.length, N2 = nums2.length;
        //compare
        int i1, i2;
        for (int i = 0; i < Math.max(N1, N2); ++i) {
            i1 = i < N1 ? Integer.parseInt(nums1[i]) : 0;
            i2 = i < N2 ? Integer.parseInt(nums2[i]) : 0;
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersions compareVersions = new CompareVersions();
        System.out.println(compareVersions.compareVersion("1.01", "1.001"));
        System.out.println(compareVersions.compareVersion("1.0", "1.0.0"));
        System.out.println(compareVersions.compareVersion("0.1", "1.1"));
        System.out.println(compareVersions.compareVersion("1.0.1", "1"));
    }
}
