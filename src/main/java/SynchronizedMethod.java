/*
    The thread which is entering into  synchronized method
    will get that lock access on the method only
 */

public class SynchronizedMethod {

    synchronized void syncMethodTableOf7() {
        int k;
        for (int i = 1; i <= 10; i++) {
            k = 7 * i;
            System.out.println(Thread.currentThread().getName() + " :- " + 7 + "*" + i + " = " + k);
        }
        System.out.println("\n");
    }

    static class Thread1 extends Thread {
        SynchronizedMethod synchronizedMethod;

        Thread1(SynchronizedMethod synchronizedMethod) {
            this.synchronizedMethod = synchronizedMethod;
        }


        public void run() {
            synchronizedMethod.syncMethodTableOf7();
        }
    }

    static class Thread2 extends Thread {
        SynchronizedMethod synchronizedMethod;

        Thread2(SynchronizedMethod synchronizedMethod) {
            this.synchronizedMethod = synchronizedMethod;
        }


        public void run() {
            synchronizedMethod.syncMethodTableOf7();
        }
    }

    public static void main(String[] args) {
        SynchronizedMethod objSyncMethod = new SynchronizedMethod();
        Thread1 t1 = new Thread1(objSyncMethod);
        t1.setName("T1");
        Thread2 t2 = new Thread2(objSyncMethod);
        t2.setName("T2");
        t1.start();
        t2.start();
    }
}
