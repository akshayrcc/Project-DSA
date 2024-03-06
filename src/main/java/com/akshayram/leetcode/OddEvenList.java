package com.akshayram.leetcode;

import com.akshayram.datastructures.ListNode;

public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddIdxNode = head;
        ListNode evenIdxNodeStart = head.next;
        ListNode evenIdxNode = evenIdxNodeStart;
        while (evenIdxNode != null && evenIdxNode.next != null) {
            oddIdxNode.next = evenIdxNode.next;
            oddIdxNode = oddIdxNode.next;
            evenIdxNode.next = oddIdxNode.next;
            evenIdxNode = evenIdxNode.next;
        }
        oddIdxNode.next = evenIdxNodeStart;
        return head;
    }
}
