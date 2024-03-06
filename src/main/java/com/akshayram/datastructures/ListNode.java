package com.akshayram.datastructures;

/*Definition for singly-linked list.*/
public class ListNode {
    public int val;
    public int data;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
