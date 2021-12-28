package com.cybertron.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// A linked list node
class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}

public class MergeKSortedLists_2 {
	// Utility function to print contents of a linked list
	public static void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " -> ");
			node = node.next;
		}
		System.out.print("null");
	}

	// The main function to merge given k sorted linked lists
	// It takes an array of lists list[0..k) and generates the sorted output
	public static Node mergeKLists(Node[] list, int k) {
		// create an empty min-heap using a comparison object for ordering the min-heap
		PriorityQueue<Node> pq;
		pq = new PriorityQueue(Comparator.comparingInt(a -> ((Node) a).data));

		// push first node of each list into the min-heap
		pq.addAll(Arrays.asList(list).subList(0, k));

		// take two pointers head and tail where head points to the first node
		// of the output list and tail points to its last node
		Node head = null, last = null;

		// run till min-heap is empty
		while (!pq.isEmpty()) {
			// extract minimum node from the min-heap
			Node min = pq.poll();

			// add the minimum node to the output list
			if (head == null) {
				head = last = min;
			} else {
				last.next = min;
				last = min;
			}

			// take next node from "same" list and insert it into the min-heap
			if (min.next != null) {
				pq.add(min.next);
			}
		}

		// return head node of the merged list
		return head;
	}

	public static void main(String[] s) {
		int k = 3; // Number of linked lists

		// An array to store the head nodes of the linked lists
		Node[] list = new Node[k];

		list[0] = new Node(1);
		list[0].next = new Node(5);
		list[0].next.next = new Node(7);

		list[1] = new Node(2);
		list[1].next = new Node(3);
		list[1].next.next = new Node(6);
		list[1].next.next.next = new Node(9);

		list[2] = new Node(4);
		list[2].next = new Node(8);
		list[2].next.next = new Node(10);

		// Merge all lists into one
		Node head = mergeKLists(list, k);
		printList(head);
	}
}
