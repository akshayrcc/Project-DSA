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
}
