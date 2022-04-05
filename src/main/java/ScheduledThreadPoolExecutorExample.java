import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Creates 1 thread pool that can schedule commands to run after a given delay, or to execute periodically.
 * Output:
 * pool-1-thread-1 prints the current seconds at the delay of every 2 seconds.
 */
public class ScheduledThreadPoolExecutorExample {

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Executing : " + Thread.currentThread().getName() + ", Current Seconds : " + new Date().getSeconds());
        }
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        executor.scheduleWithFixedDelay(new Task(), 2, 2, TimeUnit.SECONDS);
    }
}
