package com.akshayram.linkedlist;

import com.akshayram.datastructures.ListNode;

public class LinkedListMediums {
    //TC: O(n) SC: O(n) .. recursive stack
    int carryForward = 0;

    public ListNode doubleIt(ListNode head) {
        ListNode remainList = helper(head);
        if (carryForward != 0) {
            ListNode newHead = new ListNode(carryForward);
            newHead.next = remainList;
            return newHead;
        } else {
            return remainList;
        }
    }

    public ListNode helper(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = helper(head.next);
        int newVal = 2 * head.val + carryForward;
        if (newVal > 9) {
            head.val = newVal % 10;
            carryForward = newVal / 10;
        } else {
            head.val = newVal;
            carryForward = 0;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedListMediums linkedListMediums = new LinkedListMediums();
        ListNode head = new ListNode(1);
        head.next = new ListNode(8);
        head.next.next = new ListNode(9);
        ListNode result = linkedListMediums.doubleIt(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
