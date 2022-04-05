import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creates 1 thread pool with 5 thread to create Task
 * Each task prints 1 to 10 for the no of times it is executed i.e. 5 times
 * Output:
 * pool-1-thread-1 prints 1 to 10 once
 */
public class SingleThreadPoolExecutorExample {

    static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Print Count: " + i + " by " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Task());
        executorService.shutdown();
    }
}
