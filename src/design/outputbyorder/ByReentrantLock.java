package design.outputbyorder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ByReentrantLock {
    public static Lock lock = new ReentrantLock();
    public static Condition conditionA = lock.newCondition();
    public static Condition conditionB = lock.newCondition();
    public static Condition conditionC = lock.newCondition();
    public static int state = 0;

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        threadA.start();
        threadB.start();
        threadC.start();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (state % 3 != 0) {
                        conditionA.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " A");
                    state++;
                    conditionB.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (state % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " B");
                    state++;
                    conditionC.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
            lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (state % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " C");
                    state++;
                    conditionA.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}