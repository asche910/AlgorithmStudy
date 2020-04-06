import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("123456789123456789123456789");
        BigInteger b2 = new BigInteger("123456789123456789123456789");
        System.out.println(bigInteger.negate());
    }
}