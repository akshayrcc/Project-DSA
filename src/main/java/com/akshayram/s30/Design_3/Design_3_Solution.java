package com.akshayram.s30.Design_3;

import java.util.*;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * <p>
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 * <p>
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
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
//
//class NestedInteger implements Iterator<Integer> {
//
//    // In Java, the Stack class is considered deprecated. Best practice is to use
//    // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
//    private Deque<NestedInteger> stack;
//
//    public NestedInteger(List<NestedInteger> nestedList) {
//        // The constructor puts them on in the order we require. No need to reverse.
//        stack = new ArrayDeque<>(nestedList);
//    }
//
//    @Override
//    public Integer next() {
//        // As per java specs, throw an exception if there's no elements left.
//        if (!hasNext())
//            throw new NoSuchElementException();
//        // hasNext ensures the stack top is now an integer. Pop and return
//        // this integer.
//        return stack.removeFirst().next();
//    }
//
//    @Override
//    public boolean hasNext() {
//        // Check if there are integers left by getting one onto the top of stack.
//        makeStackTopAnInteger();
//        // If there are any integers remaining, one will be on the top of the stack,
//        // and therefore the stack can't possibly be empty.
//        return !stack.isEmpty();
//    }
//
//    private void makeStackTopAnInteger() {
//        // While there are items remaining on the stack and the front of
//        // stack is a list (i.e. not integer), keep unpacking.
//        while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
//            // Put the NestedIntegers onto the stack in reverse order.
//            List<NestedInteger> nestedList = stack.removeFirst().getList();
//            for (int i = nestedList.size() - 1; i >= 0; i--) {
//                stack.addFirst(nestedList.get(i));
//            }
//        }
//    }
//}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */


public class Design_3_Solution {
}