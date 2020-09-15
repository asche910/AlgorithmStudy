package design.producerconsumer;

import java.util.concurrent.Semaphore;

public class BySemaphore {
    volatile static int size;
    static int capacity = 10;

    //mutex互斥量用于防止对size变量并发修改
    static Semaphore mutex = new Semaphore(1);
    // 刚开始为空，notFull状态有10个，表示生产者可以acquire 10次该状态
    static Semaphore notFull = new Semaphore(capacity);
    static Semaphore notEmpty = new Semaphore(0);

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
                    notFull.acquire();
                    mutex.acquire();

                    size++;
                    System.out.println("produce, cur: " + size);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    notEmpty.release();
                    mutex.release();
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
                    notEmpty.acquire();
                    mutex.acquire();

                    size--;
                    System.out.println("consume, cur: " + size);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    notFull.release();
                    mutex.release();
                }
            }
        }
    }
}
