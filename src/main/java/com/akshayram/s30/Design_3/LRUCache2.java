package com.akshayram.s30.Design_3;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {
    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache2(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-11, -1);

        head.next = tail;
        map.put(head.key, null);
        map.put(tail.key, head);
        //
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node prevNode = map.get(key);
        Node curr = prevNode.next;
        Node currNext = curr.next;
        // make the next node of my curr node prev has to be updated in map also;
        map.put(currNext.key, prevNode);
        prevNode.next = prevNode.next.next;
        //put curr towards head of ll
        Node headNext = head.next;
        curr.next = head.next;
        map.put(headNext.key, curr);
        head.next = curr;
        map.put(curr.key, head);
        return curr.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node prevNode = map.get(key);
            Node curr = prevNode.next;
            Node currNext = curr.next;
            // make the next node of my curr node prev has to be updated in map also;
            map.put(currNext.key, prevNode);
            prevNode.next = prevNode.next.next;
            //put curr towards head of ll
            Node headNext = head.next;
            curr.next = head.next;
            map.put(headNext.key, curr);
            head.next = curr;
            map.put(curr.key, head);
        } else {
            //fresh
            if (capacity == map.size()) {
                //remove a node
                Node tailPrev = map.get(tail.key);
                Node tailPrevPrev = map.get(tailPrev.key);
                tailPrevPrev.next = tail;
                map.put(tail.key, tailPrevPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            Node headNext = head.next;
            newNode.next = head.next;
            map.put(headNext.key, newNode);
            head.next = newNode;
            map.put(newNode.key, head);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

