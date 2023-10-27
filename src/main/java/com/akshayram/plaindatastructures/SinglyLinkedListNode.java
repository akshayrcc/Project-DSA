package com.akshayram.plaindatastructures;

public class SinglyLinkedListNode {
    public int val;
    public SinglyLinkedListNode next;

    SinglyLinkedListNode() {
    }

    public SinglyLinkedListNode(int val) {
        this.val = val;
    }

    SinglyLinkedListNode(int val, SinglyLinkedListNode next) {
        this.val = val;
        this.next = next;
    }
}
