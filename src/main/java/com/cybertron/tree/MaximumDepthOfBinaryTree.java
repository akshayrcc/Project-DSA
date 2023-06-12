package com.cybertron.tree;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int bigger = Math.max(leftDepth, rightDepth);
        return bigger + 1;
    }
}
