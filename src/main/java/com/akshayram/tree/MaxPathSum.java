package com.akshayram.tree;

public class MaxPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        //find the left and right subtree max sum
        //avoid negative sums by comparing it with 0
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);
        //max sum at root node with left and right subtree
        max = Math.max(max, root.val + left + right);
        //contribution of left/right subtree
        return root.val + Math.max(left, right);
    }
}
