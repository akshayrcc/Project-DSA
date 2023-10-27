package com.akshayram.leetcode;

public class MergeKSortedListsDivideAndConquer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* Takes two lists sorted in increasing order, and merge 
    their nodes together to make one big sorted list. Below 
    function takes O(Log n) extra space for recursive calls, 
    but it can be easily modified to work with same time and 
    O(1) extra space  */
    public static Node SortedMerge(Node a, Node b)
    {
        Node result = null;
        /* Base cases */
        if (a == null)
            return b;
        else if (b == null)
            return a;
 
        /* Pick either a or b, and recur */
        if (a.data <= b.data) {
            result = a;
            result.next = SortedMerge(a.next, b);
        }
        else {
            result = b;
            result.next = SortedMerge(a, b.next);
        }
 
        return result;
    }
 
    /* Function to print nodes in a given linked list */
    public static void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
    
    class Node {
        int data;
        Node next;
        Node(int data)
        {
            this.data = data;
        }
    }
    
}
