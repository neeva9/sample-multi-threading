/*
    The thread which is entering into synchronized method or synchronized block
    will get that lock, either on the object or the method.

    Here we have two objects objSyncStatic1 (locked access by t1, t2) and objSyncStatic2 (locked access by t3, t4)
    Both t1, t3 will acquire lock on the same object i.e. objSyncStatic1 and objSyncStatic2 respectively, so will
    t2. t4 acquire lock on the same object i.e. objSyncStatic1 and objSyncStatic2 respectively at the same time.

    To avoid this, we will use static synchronization. see WithSynchronizedStatic.java
*/

public class WithoutSynchronizedStatic {

    synchronized void syncStaticPowerOf7() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " :- " + i + " pow " + 7 + " value: " + Math.pow(i, 7));
        }
        System.out.println("\n");
    }


    static class Thread1 extends Thread {
        WithoutSynchronizedStatic withoutSynchronizedStatic;

        Thread1(WithoutSynchronizedStatic withoutSynchronizedStatic) {
            this.withoutSynchronizedStatic = withoutSynchronizedStatic;
        }


        public void run() {
            withoutSynchronizedStatic.syncStaticPowerOf7();
        }
    }

    static class Thread2 extends Thread {
        WithoutSynchronizedStatic withoutSynchronizedStatic;

        Thread2(WithoutSynchronizedStatic withoutSynchronizedStatic) {
            this.withoutSynchronizedStatic = withoutSynchronizedStatic;
        }


        public void run() {
            withoutSynchronizedStatic.syncStaticPowerOf7();
        }
    }

    static class Thread3 extends Thread {
        WithoutSynchronizedStatic withoutSynchronizedStatic;

        Thread3(WithoutSynchronizedStatic withoutSynchronizedStatic) {
            this.withoutSynchronizedStatic = withoutSynchronizedStatic;
        }


        public void run() {
            withoutSynchronizedStatic.syncStaticPowerOf7();
        }
    }

    static class Thread4 extends Thread {
        WithoutSynchronizedStatic withoutSynchronizedStatic;

        Thread4(WithoutSynchronizedStatic withoutSynchronizedStatic) {
            this.withoutSynchronizedStatic = withoutSynchronizedStatic;
        }


        public void run() {
            withoutSynchronizedStatic.syncStaticPowerOf7();
        }
    }

    public static void main(String[] args) {

        WithoutSynchronizedStatic objSyncStatic1 = new WithoutSynchronizedStatic();
        Thread1 t1 = new Thread1(objSyncStatic1);
        t1.setName("T1");
        Thread2 t2 = new Thread2(objSyncStatic1);
        t2.setName("T2");
        t1.start();
        t2.start();


        WithoutSynchronizedStatic objSyncStatic2 = new WithoutSynchronizedStatic();
        Thread3 t3 = new Thread3(objSyncStatic2);
        t3.setName("T3");
        Thread4 t4 = new Thread4(objSyncStatic2);
        t4.setName("T4");
        t3.start();
        t4.start();

    }
}
