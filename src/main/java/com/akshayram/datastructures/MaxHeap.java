package com.akshayram.datastructures;

public class MaxHeap {
    private int[] heap;
    private int size;

    public MaxHeap(int capacity) {
        this.heap = new int[capacity];
        this.size = 0;
    }

    // Utility methods (mostly same as MinHeap)

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

    // Key methods (modify logic for max)

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getMax() {
        if (isEmpty()) {
            throw new RuntimeException("MaxHeap is empty!");
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

    public int removeMax() {
        if (isEmpty()) {
//            throw new EmptyHeapException();
            throw new RuntimeException("MaxHeap is empty!");
        }
        int max = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return max;
    }

    // Heapify methods (modify comparison operators)

    private void heapifyUp(int index) {
        while (index > 0 && heap[index] > heap[(index - 1) / 2]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int maxChildIndex = leftChildIndex(index);
            if (hasRightChild(index) && heap[maxChildIndex] < heap[rightChildIndex(index)]) {
                maxChildIndex = rightChildIndex(index);
            }
            if (heap[index] >= heap[maxChildIndex]) {
                break;
            }
            swap(index, maxChildIndex);
            index = maxChildIndex;
        }
    }
}
