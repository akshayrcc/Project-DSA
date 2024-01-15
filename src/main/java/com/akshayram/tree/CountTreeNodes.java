package com.akshayram.tree;

public class CountTreeNodes {
    int count = 1;

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
