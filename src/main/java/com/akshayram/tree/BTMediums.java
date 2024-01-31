package com.akshayram.tree;

import java.util.*;

public class BTMediums {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        convert(root, 0, map);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int minute = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                int current = queue.poll();
                for (int num : map.get(current)) {
                    if (!visited.contains(num)) {
                        visited.add(num);
                        queue.add(num);
                    }
                }
                levelSize--;
            }
            minute++;
        }
        return minute - 1;
    }

    public void convert(TreeNode current, int parent, Map<Integer, Set<Integer>> map) {
        if (current == null) {
            return;
        }
        if (!map.containsKey(current.val)) {
            map.put(current.val, new HashSet<>());
        }
        Set<Integer> adjacentList = map.get(current.val);
        if (parent != 0) {
            adjacentList.add(parent);
        }
        if (current.left != null) {
            adjacentList.add(current.left.val);
        }
        if (current.right != null) {
            adjacentList.add(current.right.val);
        }
        convert(current.left, current.val, map);
        convert(current.right, current.val, map);
    }

    //The LCA Problem

    Set<Integer> hset = new HashSet<>();
    TreeNode LCA;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p);
        // System.out.println(hset);
        dfs_search(root, q);
        return LCA;
    }

    TreeNode dfs(TreeNode root, TreeNode oneNode){
        //base condition
        if(root == null){
            return null;
        }
        if(root.val == oneNode.val){
            hset.add(root.val);
            return root;
        }
        TreeNode tn_left = dfs(root.left, oneNode);
        TreeNode tn_right = dfs(root.right, oneNode);

        if(tn_left == null &&  tn_right == null)    return null;
        hset.add(root.val);
        return root;
    }

    TreeNode dfs_search(TreeNode root, TreeNode oneNode){
        System.out.println("chek "+ oneNode.val);
        //base condition
        if(root == null){
            return null;
        }

        if(root.val == oneNode.val){
            System.out.println("found node "+ root.val);
            if(LCA == null && hset.contains(root.val)){
                LCA = root;
            }
            return root;
        }

        TreeNode tn_left = dfs(root.left, oneNode);
        TreeNode tn_right = dfs(root.right, oneNode);
        System.out.println("checking for parent: "+ root.val);
        if(LCA == null && hset.contains(root.val)){
            LCA = root;
        }
        if(tn_left == null &&  tn_right == null)    return null;
        return root;
    }
}
