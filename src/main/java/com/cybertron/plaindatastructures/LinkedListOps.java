package com.cybertron.plaindatastructures;

public class LinkedListOps {

    /**
     * Delete a node in the middle when given ptr to the same node.
     * */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
