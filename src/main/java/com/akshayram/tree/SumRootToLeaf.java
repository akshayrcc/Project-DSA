package com.akshayram.tree;

public class SumRootToLeaf {

    int result;

    public int sumNumbers(TreeNode root) {

        sumofAllPaths(root, 0);
        return result;
    }

    private void sumofAllPaths(TreeNode root, int currValue) {
        //base case
        if (root == null) {
            return;
        }
        //calculate currSum at each node and then add current node value to sum
        currValue = currValue * 10 + root.val;
        if (root.left == null && root.right == null) {
            result += currValue;
        }
        sumofAllPaths(root.left, currValue);
        sumofAllPaths(root.right, currValue);
    }
}

