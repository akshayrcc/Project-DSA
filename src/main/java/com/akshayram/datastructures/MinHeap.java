package com.akshayram.datastructures;

public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap(int capacity) {
        this.heap = new int[capacity];
        this.size = 0;
    }

    // Utility methods

    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private boolean hasLeftChild(int parentIndex) {
        return leftChildIndex(parentIndex) < size;
    }

    private boolean hasRightChild(int parentIndex) {
        return rightChildIndex(parentIndex) < size;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Key methods

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getMin() {
        if (isEmpty()) {
            throw new RuntimeException("MinHeap is empty!");
        }
        return heap[0];
    }

    public void insert(int element) {
        if (size == heap.length) {
            // Handle resize if needed
        }
        heap[size++] = element;
        heapifyUp(size - 1);
    }

    public int removeMin() {
        if (isEmpty()) {
            //throw new EmptyHeapException();
            throw new RuntimeException();
        }
        int min = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return min;
    }

    // Heapify methods

    private void heapifyUp(int index) {
        while (index > 0 && heap[index] < heap[(index - 1) / 2]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int minChildIndex = leftChildIndex(index);
            if (hasRightChild(index) && heap[minChildIndex] > heap[rightChildIndex(index)]) {
                minChildIndex = rightChildIndex(index);
            }
            if (heap[index] <= heap[minChildIndex]) {
                break;
            }
            swap(index, minChildIndex);
            index = minChildIndex;
        }
    }
}

//  EmptyHeapException class for better error handling
//public class EmptyHeapException extends RuntimeException {
//    public EmptyHeapException() {
//        super("MinHeap is empty!");
//    }
//}
