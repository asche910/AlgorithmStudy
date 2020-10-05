import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static Lock lock = new ReentrantLock();
    public static Condition conditionA = lock.newCondition();
    public static Condition conditionB = lock.newCondition();
    public static Condition conditionC = lock.newCondition();
    public static int state = 0;

    public static void main(String[] args) {


    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (state % 3 != 0){
                        conditionA.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " A");
                    state++;
                    conditionB.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (state % 3 != 1){
                        conditionB.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " B");
                    state++;
                    conditionC.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (state % 3 != 2){
                        conditionC.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " C");
                    state++;
                    conditionA.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}