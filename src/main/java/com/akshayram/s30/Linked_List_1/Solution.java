// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution {

    // Time Complexity : O(n)
    // Space Complexity : O(1)
    public ListNode reverseList_1(ListNode head) {
        //null
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode temp = curr.next;
        while (temp != null) {
            curr.next = prev;
            prev = curr;
            curr = temp;
            temp = temp.next;
        }
        curr.next = prev;
        return curr;
    }

    // Time Complexity : O(n)
    // Space Complexity : O(1)
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    // Time Complexity : O(n)
    // Space Complexity : O(1)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean isCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle = true;
                break;
            }
        }
        return isCycle;
    }

    // Time Complexity : O(n)
    // Space Complexity : O(1)
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean isCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle = true;
                break;
            }
        }
        if (!isCycle) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // Time Complexity : O(n)
    // Space Complexity : O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // if(head == null){
        //     return head;
        // }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode ptr1 = dummy;
        ListNode ptr2 = dummy;
        int count = 0;
        while (count <= n) {
            ptr2 = ptr2.next;
            count++;
        }
        if (ptr2 == null) {
            return head.next;
        }
        while (ptr2 != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        //delete node at ptr1.next
        ListNode temp = ptr1.next;
        ptr1.next = ptr1.next.next;
        temp.next = null;
        return head;
    }
}