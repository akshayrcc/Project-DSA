package com.akshayram.leetcode;

import com.akshayram.datastructures.SinglyLinkedListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists_2 {
	// Utility function to print contents of a linked list
	public static void printList(SinglyLinkedListNode node) {
		while (node != null) {
			System.out.print(node.val + " -> ");
			node = node.next;
		}
		System.out.print("null");
	}

	// The main function to merge given k sorted linked lists
	// It takes an array of lists list[0..k) and generates the sorted output
	public static SinglyLinkedListNode mergeKLists(SinglyLinkedListNode[] list, int k) {
		// create an empty min-heap using a comparison object for ordering the min-heap
		PriorityQueue<SinglyLinkedListNode> pq;
		pq = new PriorityQueue(Comparator.comparingInt(a -> ((SinglyLinkedListNode) a).val));

		// push first SinglyLinkedListNode of each list into the min-heap
		pq.addAll(Arrays.asList(list).subList(0, k));

		// take two pointers head and tail where head points to the first SinglyLinkedListNode
		// of the output list and tail points to its last SinglyLinkedListNode
		SinglyLinkedListNode head = null, last = null;

		// run till min-heap is empty
		while (!pq.isEmpty()) {
			// extract minimum SinglyLinkedListNode from the min-heap
			SinglyLinkedListNode min = pq.poll();

			// add the minimum SinglyLinkedListNode to the output list
			if (head == null) {
				head = last = min;
			} else {
				last.next = min;
				last = min;
			}

			// take next SinglyLinkedListNode from "same" list and insert it into the min-heap
			if (min.next != null) {
				pq.add(min.next);
			}
		}

		// return head SinglyLinkedListNode of the merged list
		return head;
	}

	public static void main(String[] s) {
		int k = 3; // Number of linked lists

		// An array to store the head SinglyLinkedListNodes of the linked lists
		SinglyLinkedListNode[] list = new SinglyLinkedListNode[k];

		list[0] = new SinglyLinkedListNode(1);
		list[0].next = new SinglyLinkedListNode(5);
		list[0].next.next = new SinglyLinkedListNode(7);

		list[1] = new SinglyLinkedListNode(2);
		list[1].next = new SinglyLinkedListNode(3);
		list[1].next.next = new SinglyLinkedListNode(6);
		list[1].next.next.next = new SinglyLinkedListNode(9);

		list[2] = new SinglyLinkedListNode(4);
		list[2].next = new SinglyLinkedListNode(8);
		list[2].next.next = new SinglyLinkedListNode(10);

		// Merge all lists into one
		SinglyLinkedListNode head = mergeKLists(list, k);
		printList(head);
	}
}
