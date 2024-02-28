package com.akshayram.tree;

public class BTBasics {

    private int getTreeHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = getTreeHeight(node.left);
        int rightHeight = getTreeHeight(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int distanceFromRoot(TreeNode node, int target) {
        if (node == null) {
            return -1; // Node not found
        }

        if (node.val == target) {
            return 0; // Node found at the root
        }

        // Recursively search in left and right subtrees
        int leftDistance = distanceFromRoot(node.left, target);
        if (leftDistance != -1) {
            return leftDistance + 1; // Found in left subtree
        }

        int rightDistance = distanceFromRoot(node.right, target);
        if (rightDistance != -1) {
            return rightDistance + 1; // Found in right subtree
        }

        return -1; // Not found in the tree
    }

    //TC: O(n) SC: O(h)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }

    int diameter;

    //TC: O(n) SC: O(h)
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        helper(root);
        return diameter;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
}
