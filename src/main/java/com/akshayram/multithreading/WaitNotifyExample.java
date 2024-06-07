package com.akshayram.multithreading;

public class WaitNotifyExample {
    private boolean flag = false;

    public synchronized void producer() throws InterruptedException {
        while (!flag) {
            wait(); // Wait until flag is set by consumer
        }
        System.out.println("Producer: Processing data...");
        flag = false;
        notify(); // Notify consumer that data is ready
    }

    public synchronized void consumer() throws InterruptedException {
        while (flag) {
            wait(); // Wait until flag is reset by producer
        }
        System.out.println("Consumer: Consuming data...");
        flag = true;
        notify(); // Notify producer that data is consumed
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyExample example = new WaitNotifyExample();

        Thread producerThread = new Thread(() -> {
            try {
                example.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                example.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
