package com.akshayram.linkedlist;

import com.akshayram.datastructures.ListNode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BasicLLOps {

    //TC: O(n) SC: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        if (fast == null) return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        if (head.next == head) return true;
        ListNode nextNode = head.next;
        head.next = head;
        return hasCycle(nextNode);
    }

    //TC: O(n) SC: O(n)
    //recursive
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    //TC: O(n) SC: O(1)
    //iterative
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    //TC: O(n) SC:O(n)
    Stack<ListNode> st = new Stack<>();

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //finding mid
        while (fast != null && fast.next != null) {
            st.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid;
        if (fast != null) {
            st.push(slow);
            mid = slow.next;
        } else {
            mid = slow;
        }

        while (!st.isEmpty()) {
            ListNode curr = st.pop();
            if (curr.val != slow.val) return false;
            slow = slow.next;
        }
        return true;
    }

    //TC: O(n) SC:O(1)
    public boolean isPalindrome2(ListNode head) {

        //finding the mid
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        //reversing the 2nd half of the SLL
        head2 = reverseList(head2);

        //comparing elements from both the ends
        while (head != null && head2 != null) {
            if (head.val != head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> hset = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hset.add(nums[i]);
        }
        ListNode dummy = new ListNode(-1);

    }

}
