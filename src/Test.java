import sun.nio.ch.ThreadPool;
import tool.Tools;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
//        System.out.println("Test...");

        System.out.println(Thread.currentThread());

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread());
            return "Hello";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
//        thread.start();

        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());

    }

}
