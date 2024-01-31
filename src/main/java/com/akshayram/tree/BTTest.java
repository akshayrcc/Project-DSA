package com.akshayram.tree;

import java.util.*;

public class BTTest {
    public static void main(String[] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");
    }

    int level = 0;
    Map<Integer, Integer> hmap = new HashMap<>();

    int findLCA(TreeNode root, int p, int q) {
        return 0;
    }

    int dfs(TreeNode root, int val) {
        level++;
        if (root.val == val) {
            //node found
            hmap.put(level, val);
            level--;
            return val;
        } else {
            dfs(root.left, val);
            dfs(root.right, val);
        }
        return 0;
    }

    Queue<TreeNode> queue = new LinkedList<>();

    void bfs(TreeNode root, int val) {
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            TreeNode firstNode = queue.poll();
            level++;
            if (firstNode.val == val) {
                hmap.put(val, level);
                level--;
                return;
            } else {
                queue.offer(firstNode.left);
                queue.offer(firstNode.right);
            }
        }
    }

    void bfsSearch(TreeNode root, int val_q) {
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            TreeNode firstNode = queue.poll();
            level++;
            if (firstNode.val == val_q) {
                if (hmap.containsKey(val_q)) {
                    return;
                }
                level--;
                return;
            } else {
                queue.offer(firstNode.left);
                queue.offer(firstNode.right);
            }
        }
    }

}
//parent = i-1/2
//first child = 2i+1
//sec child = 2i+ 2
