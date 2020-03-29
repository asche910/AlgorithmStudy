package design.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ByBlockingQueue {
    volatile static int size;
    static int capacity = 10;

    static final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(capacity);

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
        new Producer().start();
        new Consumer().start();
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    blockingQueue.put(1);
                    size++;
                    System.out.println("produce, cur: " + size);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    blockingQueue.take();
                    size--;
                    System.out.println("consume, cur: " + size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
