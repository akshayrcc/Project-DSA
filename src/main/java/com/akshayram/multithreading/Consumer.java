package com.akshayram.multithreading;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("take : " + queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
