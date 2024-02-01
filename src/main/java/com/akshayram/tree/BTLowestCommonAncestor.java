package com.akshayram.tree;

import java.util.HashSet;
import java.util.Set;

public class BTLowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println(" LCA is " + lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4)).val);
    }

    static Set<Integer> hset = new HashSet<>();
    static TreeNode LCA;
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p);
        dfs_search(root, q);
        return LCA;
    }

    static TreeNode dfs(TreeNode root, TreeNode oneNode) {
        if (root == null)   return null;
        if (root.val == oneNode.val) {
            hset.add(root.val);
            return root;
        }
        TreeNode tn_left = dfs(root.left, oneNode);
        TreeNode tn_right = dfs(root.right, oneNode);
        if (tn_left == null && tn_right == null) return null;
        hset.add(root.val);
        return root;
    }

    static TreeNode dfs_search(TreeNode root, TreeNode oneNode) {
        if (root == null)   return null;
        if (root.val == oneNode.val) {
            if (LCA == null && hset.contains(root.val)) {
                LCA = root;
            }
            return root;
        }
        TreeNode tn_left = dfs_search(root.left, oneNode);
        TreeNode tn_right = dfs_search(root.right, oneNode);
        if (tn_left == null && tn_right == null) {
            return null;
        }
        if (LCA == null && hset.contains(root.val)) {
            LCA = root;
            return root;
        }
        return root;
    }
}

