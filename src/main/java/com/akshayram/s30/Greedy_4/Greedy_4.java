import java.util.*;

public class Solution {
    // TC: O(n). SC: O(1)
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int re = check(tops, bottoms, tops[1]);
        if (re != -1)
            return re;
        return check(tops, bottoms, bottoms[1]);
    }

    private int check(int[] tops, int[] bottoms, int target) {
        int tRot = 0;
        int bRot = 0;
        for (int i = 0; i < tops.length; i++) { // 0(n)
            if (tops[i] != target && bottoms[i] != target) {
                return -1;
            }
            if (tops[i] != target) {
                tRot++;
            }
            if (bottoms[i] != target) {
                bRot++;
            }
        }
        return Math.min(tRot, bRot);
    }

    // TC: O(nlogm) SC: O(m)
    public int shortestWay(String source, String target) {
        int i = 0; // source
        int j = 0; // target
        int tl = target.length();
        int sl = source.length();

        // Building Frequency List Map on Source String
        HashMap<Character, List<Integer>> sourceFreqListMap = new HashMap<>();
        for (int k = 0; k < sl; k++) {
            char c = source.charAt(k);
            if (!sourceFreqListMap.containsKey(c)) {
                sourceFreqListMap.put(c, new ArrayList<>());
            }
            sourceFreqListMap.get(c).add(k);
        }

        int count = 1;
        // loop source until target-end is reached
        while (j < tl) {
            char tChar = target.charAt(j);
            if (!sourceFreqListMap.containsKey(tChar)) {
                return -1;
            }
            List<Integer> li = sourceFreqListMap.get(tChar);
            int k = Collections.binarySearch(li, i);
            if (k < 0) {
                k = -k - 1;
                System.out.println(k + " " + j + " " + count);
            }
            if (k == li.size()) {
                count++;
                i = li.get(0);
            } else {
                i = li.get(k);
            }
            i++;
            j++;
            if (j == tl)
                return count;
        }
        return -1;
    }
}
