import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creates 1 thread pool with 2 thread to create Task 5 times
 * Output:
 * pool-1-thread-1 prints 1 to 10 for n times where n < 5
 * pool-1-thread-2 prints 1 to 10 for 5 - n times
 */
public class FixedThreadPoolExecutor {

    static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Print Count: " + i + " by " + Thread.currentThread().getName());
            }

        }
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 5; i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }
}
