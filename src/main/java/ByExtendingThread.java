/**
 * Creates 2 threads to create Task 5 times
 * Output:
 * Thread-0 prints 1 to 10 once
 * Thread-1 prints 1 to 10 once
 */
public class ByExtendingThread {

    static class Task extends Thread {
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName()
                    + " is running");
            for (int i = 1; i <= 10; i++) {
                System.out.println("Print Count: " + i + " by " + Thread.currentThread().getName());
            }
            System.out.println("Thread " + Thread.currentThread().getName()
                    + " is completed");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Task object = new Task();
            object.start();
        }

    }

}
