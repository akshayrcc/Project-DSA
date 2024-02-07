package com.akshayram.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Traversals {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println("BFS traversal: ");
        bfs(root);
        System.out.println("\nDFS pre-order traversal: ");
        dfsPreOrder(root);
        System.out.println("\nDFS in-order traversal: ");
        dfsInOrder(root);
        System.out.println("\nDFS post-order traversal: ");
        dfsPostOrder(root);
    }

    static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            System.out.print(current.val + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    static void dfsPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        dfsPreOrder(root.left);
        dfsPreOrder(root.right);
    }

    static void dfsInOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        dfsInOrder(root.left);
        System.out.print(root.val + " ");
        dfsInOrder(root.right);
    }

    static void dfsPostOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        dfsPostOrder(root.left);
        dfsPostOrder(root.right);
        System.out.print(root.val + " ");
    }


}
