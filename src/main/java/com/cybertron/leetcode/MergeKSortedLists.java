package com.cybertron.leetcode;

//import com.cybertron.plaindatastructures.ListNode;

public class MergeKSortedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

//	public static ListNode mergeKSortedLists(ListNode arr[], int k) {
//		ListNode head = null, last = null;
//
//		// priority_queue 'pq' implemeted
//		// as min heap with the
//		// help of 'compare' function
//		PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
//			public int compare(ListNode a, ListNode b) {
//				return a.val - b.val;
//			}
//		});
//
//		// push the head nodes of all
//		// the k lists in 'pq'
//		for (int i = 0; i < k; i++)
//			if (arr[i] != null)
//				pq.add(arr[i]);
//
//		// loop till 'pq' is not empty
//		while (!pq.isEmpty()) {
//			// get the top element of 'pq'
//			ListNode top = pq.peek();
//			pq.remove();
//
//			// check if there is a node
//			// next to the 'top' node
//			// in the list of which 'top'
//			// node is a member
//			if (top.next != null)
//				// push the next node in 'pq'
//				pq.add(top.next);
//
//			// if final merged list is empty
//			if (head == null) {
//				head = top;
//				// points to the last node so far of
//				// the final merged list
//				last = top;
//			} else {
//				// insert 'top' at the end
//				// of the merged list so far
//				last.next = top;
//
//				// update the 'last' pointer
//				last = top;
//			}
//		}
//		// head node of the required merged list
//		return head;
//	}

	// function to print the singly linked list
	public static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

	// Utility function to create a new node
	public ListNode push(int data) {
		ListNode newNode = new ListNode(data);
		newNode.next = null;
		return newNode;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
