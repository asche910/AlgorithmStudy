package design.producerconsumer;

public class ByWaitAndNotify {
    static final Object lock = new Object();
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
                    synchronized (lock) {
                        while (size == capacity) {
                            lock.wait();
                        }
                        size++;
                        System.out.println("produce, cur: " + size);
                        lock.notifyAll();
                    }
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
                    synchronized (lock) {
                        while (size == 0) {
                            lock.wait();
                        }
                        size--;
                        System.out.println("consume, cur: " + size);
                        lock.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
