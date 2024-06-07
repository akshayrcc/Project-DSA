package com.akshayram.multithreading;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put("string " + i);
                System.out.println("put : " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
