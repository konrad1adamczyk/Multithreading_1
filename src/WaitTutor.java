import org.junit.Test;
import static org.junit.Assert.*;

public class WaitTutor {
    static StringBuffer buf = new StringBuffer();
    static void log(String s) {
        buf.append(s+"\n");
    }

    Thread t1, t2;
    Object monitor = new Object();
    int runningThreadNumber = 1;
    int t1Counter = 0, t2Counter = 0;
    //int maxCounter = 0;

    class TestThread implements Runnable {
        String threadName;
        int n;

        public TestThread(String threadName, int n) {
            this.threadName = threadName;
            this.n = n;
        }

        @Override
        public void run() {
            for (int i=0;i<100;i++) {
                logAndCheckCounter(threadName, i);
                Thread.yield();
            }
        }
    }

    @Test
    public void testThread() {
        t1 = new Thread(new TestThread("t1", 1));
        t2 = new Thread(new TestThread("t2", 2));
        System.out.println("Starting threads");
        t1.start();
        t2.start();

        System.out.println("Waiting for threads");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(buf);
        assertFalse(wrongCounter);
    }

    /**
     * This code to check for the correctness of next counter.
     * Counters should be ordered: 0, 0, 1, 1, 2, 2, etc.
     */
    boolean wrongCounter = false;
    int counter = 0;
    static final int threadsAmount = 2;
    int counterOccured = 0;

    private void logAndCheckCounter(String threadName, int c) {
        log(threadName+":"+c);
        if (counter != c) wrongCounter = false;
        counterOccured++;

        if (counterOccured == threadsAmount) {
            counter++;
            counterOccured = 0;
        }
    }

}
