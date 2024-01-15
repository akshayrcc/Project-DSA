package com.akshayram.tree;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //swap or invert action to invert BT nodes
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //recurse
        invertTree(root.left);
        invertTree(root.right);
        return root;

    }
}
