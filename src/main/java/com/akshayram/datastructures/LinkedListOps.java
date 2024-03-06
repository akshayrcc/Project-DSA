package com.akshayram.datastructures;

public class LinkedListOps {

    /**
     * Delete a node in the middle when given ptr to the same node.
     */
    public void deleteNode(SinglyLinkedListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public SinglyLinkedListNode reverseList(SinglyLinkedListNode head) {
        if (head == null || head.next == null)
            return head;

        SinglyLinkedListNode p1 = head;
        SinglyLinkedListNode p2 = p1.next;

        head.next = null;
        while (p1 != null && p2 != null) {
            SinglyLinkedListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

        return p1;
    }
}
