/*
    The thread which is entering into  synchronized block
    will get that lock access on the block only
 */
public class SynchronizedBlock {
        void syncBlock7Power() {
            synchronized (this) {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " :- " + 7 + " power " + i + " value: " + Math.pow(7, i));
                }
                System.out.println("\n");
            }
        }



    static class Thread3 extends Thread {
        SynchronizedBlock synchronizedBlock;

        Thread3(SynchronizedBlock synchronizedBlock) {
            this.synchronizedBlock = synchronizedBlock;
        }


        public void run() {
            synchronizedBlock.syncBlock7Power();
        }
    }

    static class Thread4 extends Thread {
        SynchronizedBlock synchronizedBlock;

        Thread4(SynchronizedBlock synchronizedBlock) {
            this.synchronizedBlock = synchronizedBlock;
        }


        public void run() {
            synchronizedBlock.syncBlock7Power();
        }
    }



    public static void main(String[] args) {
        SynchronizedBlock objSyncBlock = new SynchronizedBlock();
        Thread3 t1 = new Thread3(objSyncBlock);
        t1.setName("T1");
        Thread4 t2 = new Thread4(objSyncBlock);
        t2.setName("T2");
        t1.start();
        t2.start();
    }
}
