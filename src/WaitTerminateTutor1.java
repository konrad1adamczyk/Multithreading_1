import org.junit.Test;

public class WaitTerminateTutor1 {
    static StringBuffer buf = new StringBuffer();
    static void log(String s) {
        buf.append(s+"\n");
    }

    Thread t1, t2;
    Object monitor = new Object();
    int runningThreadNumber = 1;

    class TestThread implements Runnable {
        String threadName;
        public boolean shouldTerminate;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i=0;;i++) {
                //log(threadName+":"+i);
                synchronized(monitor) {
                    try {
                        while (!threadName.equals("t"+runningThreadNumber)) {
                            //log("wait for thread "+"t"+runningThreadNumber);
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        log("INTERRUPTED EXCEPTION OCCURED!..");
                        return;
                    }
                    runningThreadNumber++;
                    if (runningThreadNumber>2) runningThreadNumber=1;
                    monitor.notifyAll();
                    if (shouldTerminate) {
                        log("TERMINATED "+threadName+"!");
                        return;
                    }
                }
            }
        }
    }

    @Test
    public void testThread() {
        final TestThread testThread1 = new TestThread("t1");
        t1 = new Thread(testThread1);
        final TestThread testThread2 = new TestThread("t2");
        t2 = new Thread(testThread2);
        log("Starting threads...");
        t1.start();
        t2.start();

        Thread terminator = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testThread2.shouldTerminate=true;
            }
        });
        terminator.start();

        /**
         * In 2ms will be executed interruptor, which will stop t1.
         */
        Thread interruptor = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                    log("Interrupting thread t1...");
                    t1.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        interruptor.start();

        log("Waiting threads to join...");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(buf);
    }

}
