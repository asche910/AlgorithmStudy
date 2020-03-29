package design.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ByReentrantLock {

    static Lock lock = new ReentrantLock();
    static Condition notFull = lock.newCondition();
    static Condition notEmpty = lock.newCondition();

    volatile static int size;
    static int capacity = 10;

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
                    lock.lock();
                    while (size == capacity) {
                        notFull.await();
                    }
                    size++;
                    System.out.println("produce, cur: " + size);
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
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
                    lock.lock();
                    while (size == 0) {
                        notEmpty.await();
                    }
                    size--;
                    System.out.println("consume, cur: " + size);
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
