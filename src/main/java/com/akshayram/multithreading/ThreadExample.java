package com.akshayram.multithreading;

public class ThreadExample implements Runnable{
    private final SharedCounter counter;

    public ThreadExample(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();
        Thread thread1 = new Thread(new ThreadExample(counter));
        Thread thread2 = new Thread(new ThreadExample(counter));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count: " + counter.getCount()); // May not always be 2000 due to race conditions
    }
}
