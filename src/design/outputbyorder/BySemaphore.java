package design.outputbyorder;

import java.util.concurrent.Semaphore;

public class BySemaphore {
    public static Semaphore semaphore1 = new Semaphore(1);
    public static Semaphore semaphore2 = new Semaphore(0);

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < 10; i++){
                try {
                    semaphore1.acquire();
                    System.out.println(Thread.currentThread().getName() + " A");

                    semaphore2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for(int i = 0; i < 10; i++){
                try {
                    semaphore2.acquire();
                    System.out.println(Thread.currentThread().getName() + " B");

                    semaphore1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
