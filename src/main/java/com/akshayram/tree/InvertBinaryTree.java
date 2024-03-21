package com.akshayram.tree;

public class InvertBinaryTree {
    //TC: O(n) SC:(h)
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        //swap the children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        //recurse
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
