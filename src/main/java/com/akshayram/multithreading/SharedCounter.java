package com.akshayram.multithreading;

public class SharedCounter {
    private int count = 0;

    public synchronized void increment() {  // Synchronized method for thread safety
        count++;
    }

    public synchronized int getCount() {  // Synchronized method for thread safety
        return count;
    }
}
