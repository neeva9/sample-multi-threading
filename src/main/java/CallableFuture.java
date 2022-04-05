import java.util.Random;
import java.util.concurrent.*;

/**
 * Creates 1 thread pool with 5 thread to create Task
 * Each task prints the name of the current thread and returns a random integer
 * Output:
 * pool-1-thread-1 prints a random integer
 * pool-1-thread-2 prints a random integer
 * pool-1-thread-3 prints a random integer
 * pool-1-thread-4 prints a random integer
 * pool-1-thread-5 prints a random integer
 */
public class CallableFuture {

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() {
            System.out.println("Thread : " + Thread.currentThread().getName());
            return new Random().nextInt();
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        Future<Integer> future;
        System.out.println("Estimate no of active threads : " + Thread.activeCount());
        for (int i = 1; i <= 5; i++) {
            future = executor.submit(new Task());
            System.out.println("Active Threads executing tasks : " + executor.getActiveCount());
            System.out.println("Estimate no of active threads : " + Thread.activeCount());
            try {
                Integer result = future.get();
                System.out.println("Result : " + result);

            } catch (InterruptedException |
                    ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nMaximum allowed threads : " + executor.getMaximumPoolSize());
        System.out.println("Current no of threads in Pool : " + executor.getPoolSize());
        System.out.println("Task Count : " + executor.getTaskCount());
        System.out.println("Completed Task Count : " + executor.getCompletedTaskCount());
        System.out.println("Estimate no of active threads : " + Thread.activeCount());
        System.out.println("Active Threads executing tasks : " + executor.getActiveCount());
        executor.shutdown();
    }
}
