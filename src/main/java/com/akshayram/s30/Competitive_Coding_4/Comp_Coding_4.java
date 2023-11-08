package com.akshayram.s30.Competitive_Coding_4;

import com.akshayram.plaindatastructures.ListNode;
import com.akshayram.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Comp_Coding_4 {
//    110. Balanced Binary Tree
//    TC: O(n) SC: O(1)
    public boolean isBalanced_1(TreeNode root) {
        int height = dfs(root);
        return height != -1;
    }
    int dfs(TreeNode root){
        //null checks
        if(root == null){
            return 0;
        }
        int left = dfs(root.left);
        if(left == -1){
            return -1;
        }
        int right = dfs(root.right);
        if(right == -1){
            return -1;
        }
        if(Math.abs(right - left) > 1){
            return -1;
        }
        return 1 + Math.max(left,right);
    }

//    TC: O(nlogn) SC: O(1)
    public boolean isBalanced(TreeNode root) {
        if(root == null)    return true;
        if(Math.abs(height(root.left) - height(root.right)) > 1)    return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root){
        //base
        if(root == null)    return 0;
        //logic
        return Math.max(height(root.left), height(root.right)) + 1;
    }



//    234. Palindrome Linked List
//    TC: O(n)      SC: O(1)

    public boolean isPalindrome(ListNode head) {

        //finding the mid
        ListNode slow = head, fast = head;
        while(fast.next!= null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        //reversing the 2nd half of the SLL
        head2 = reverseList(head2);

        //comparing elements from both the ends
        while(head != null  && head2 != null){
            if(head.val != head2.val)   return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }
}