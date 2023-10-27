package com.akshayram.oa;

import java.util.ArrayList;

//citadel

class TreeNode {
    int value;
    ArrayList<TreeNode> children;

    public TreeNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

public class BestSumAnyTreePathSolution {
    static int maxSum;

    public static int bestSumAnyTreePath(int[] parent, int[] values) {
        int n = parent.length;
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode(values[i]);
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                nodes[parent[i]].children.add(nodes[i]);
            }
        }

        maxSum = Integer.MIN_VALUE;
        calculateMaxSum(nodes[0]);
        return maxSum;
    }

    private static int calculateMaxSum(TreeNode node) {
        int maxSumIncludingNode = node.value;

        for (TreeNode child : node.children) {
            int childSum = calculateMaxSum(child);
            maxSumIncludingNode = Math.max(maxSumIncludingNode, childSum + node.value);
        }

        maxSum = Math.max(maxSum, maxSumIncludingNode);

        return Math.max(node.value, node.value + maxSumIncludingNode);
    }

    public static void main(String[] args) {
        int[] parent = {-1, 0, 1, 2, 0};
        int[] values = {5, -2, 10, 10, -3};
        int result = bestSumAnyTreePath(parent, values);
        System.out.println(result);  // Output should be 28
    }
}
