package com.akshayram.linkedlist;

import com.akshayram.plaindatastructures.ListNode;

public class RemoveNthElementSLL {

    //TC: O(n) SC: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for(int i=0; i<n; i++)  fast = fast.next;
        if(fast == null)    return head.next;
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
