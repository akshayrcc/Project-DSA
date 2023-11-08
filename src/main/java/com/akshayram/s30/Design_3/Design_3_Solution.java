package com.akshayram.s30.Design_3;

import java.util.*;

// TC: O(1) SC: O(C).. where C is LRUCache capacity...
class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        Node() {
            this(0, 0);
        }
    }

    Node head, tail;
    Map<Integer, Node> map;
    int capacity, count;

    // Initialization of the LRU cache
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    // Returns an element if present in the cache
    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        } else {
            update(n);// update the cache such that the most recently used element is at the top
            return n.value;
        }
    }

    // adds an element to the cache. If absent, adds. If present, updates the cache
    // to hold the most recently used at the top. If the capacity is full, removes
    // the least recently used element from the cache.
    public void put(int key, int value) {
        Node n = map.get(key);
        if (n == null) {
            n = new Node(key, value);
            map.put(key, n);
            add(n);
            ++count;
        } else {
            n.value = value;
            update(n);
        }
        if (count > capacity) {
            Node del = tail.prev;
            remove(del);
            map.remove(del.key);
            --count;
        }
    }

    // function that updates the MRU element of the cache.
    public void update(Node n) {
        remove(n);
        add(n);
    }

    // adds an element to the top of the cache(MRU element).
    public void add(Node node) {
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    // removes the LRU element from the cache.
    public void remove(Node node) {
        Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev = before;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// --------------------//--------------------//--------------------//--------------------
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */

class NestedInteger implements Iterator<Integer> {

    // In Java, the Stack class is considered deprecated. Best practice is to use
    // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
    private Deque<NestedInteger> stack;

    public NestedInteger(List<NestedInteger> nestedList) {
        // The constructor puts them on in the order we require. No need to reverse.
        stack = new ArrayDeque<>(nestedList);
    }

    @Override
    public Integer next() {
        // As per java specs, throw an exception if there's no elements left.
        if (!hasNext())
            throw new NoSuchElementException();
        // hasNext ensures the stack top is now an integer. Pop and return
        // this integer.
        return stack.removeFirst().next();
    }

    @Override
    public boolean hasNext() {
        // Check if there are integers left by getting one onto the top of stack.
        makeStackTopAnInteger();
        // If there are any integers remaining, one will be on the top of the stack,
        // and therefore the stack can't possibly be empty.
        return !stack.isEmpty();
    }

    private void makeStackTopAnInteger() {
        // While there are items remaining on the stack and the front of
        // stack is a list (i.e. not integer), keep unpacking.
        while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            // Put the NestedIntegers onto the stack in reverse order.
            List<NestedInteger> nestedList = stack.removeFirst().getList();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.addFirst(nestedList.get(i));
            }
        }
    }
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */