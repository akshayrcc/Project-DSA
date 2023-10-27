package com.akshayram.leetcode;

import com.akshayram.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTzigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);

        int level = 0;

        while (!qu.isEmpty()) {
            int len = qu.size();
            List<Integer> levelResult = new ArrayList<>();

            for (int i = 0; i < len; i++) {

                TreeNode temp = qu.poll();

                if (temp.left != null) {
                    qu.add(temp.left);
                }
                if (temp.right != null) {
                    qu.add(temp.right);
                }
                if (level % 2 == 0) {
                    levelResult.add(temp.val);
                } else {
                    levelResult.add(0, temp.val);
                }
            }

            level++;
            result.add(levelResult);
        }
        return result;
    }
}
