package com.akshayram.tree;

import java.util.Stack;

public class LeavesManipulation {
  static Stack<Integer> st = new Stack<>();
  static boolean isLeafValueSeq = true;

  // TC: O(n) SC: O(n)
  public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
    getLeaves(root1);
    checkLeaves(root2);
    if(!st.isEmpty()){
      isLeafValueSeq = false;
    }
    return isLeafValueSeq;
  }

  private static void getLeaves(TreeNode root) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      st.push(root.val);
      return;
    }
    getLeaves(root.left);
    getLeaves(root.right);
  }

  private static void checkLeaves(TreeNode root) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      if(st.isEmpty()){
        isLeafValueSeq = false;
        return;
      }
      int popped = st.pop();
      if (root.val != popped) {
        isLeafValueSeq = false;
      }
      return;
    }
    if (isLeafValueSeq) {
      checkLeaves(root.right);
    }
    if (isLeafValueSeq) {
      checkLeaves(root.left);
    }
  }

  static TreeNode buildTree(String[] preOrder) {
    int i = 0;
    return buildTreeUtil(preOrder, i);
  }

  static TreeNode buildTreeUtil(String[] preOrder, int i) {
    if (i >= preOrder.length || preOrder[i].equals("NULL") || preOrder[i].equals("null")) {
      return null;
    }

    TreeNode node = new TreeNode(preOrder[i]);
    node.left = buildTreeUtil(preOrder, i * 2 + 1); // Left child index
    node.right = buildTreeUtil(preOrder, i * 2 + 2); // Right child index
    return node;
  }

  public static void main(String[] args) {
    System.out.println("STARTED...");

    // build
    String[] arr1 = {"4","2","6","1","3","5","7"};
    TreeNode root1 = buildTree(arr1);

    String[] arr2 = {"4","2","6","null","3","5","7"};
    TreeNode root2 = buildTree(arr2);

    // Test the leafSimilar method
    boolean areLeafSimilar = LeavesManipulation.leafSimilar(root1, root2);
    System.out.println("Trees are leaf-similar: " + areLeafSimilar);
  }
}
