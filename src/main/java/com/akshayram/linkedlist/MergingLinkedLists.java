package com.akshayram.linkedlist;

import com.akshayram.datastructures.ListNode;

public class MergingLinkedLists {

    //TC: O(n) SC:O(1)
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int nodeCount = 1;
        ListNode tail2 = list2;
        while (tail2 != null && tail2.next != null) {
            tail2 = tail2.next;
        }
        ListNode ptrA = list1;
        while (ptrA.next != null && nodeCount != a) {
            nodeCount++;
            ptrA = ptrA.next;
        }
        System.out.println("A val " + ptrA.val);
        System.out.println("nodeCount val " + nodeCount);
        ListNode ptrB = ptrA.next;
        while (ptrB != null && ptrB.next != null && nodeCount != b + 1) {
            nodeCount++;
            ptrB = ptrB.next;
        }
        System.out.println("B val " + ptrB.val);
        System.out.println("nodeCount val " + nodeCount);
        if (list2 != null) {
            ptrA.next = list2;
            tail2.next = ptrB;
        } else {
            ptrA.next = ptrB;
        }
        return list1;
    }
}
