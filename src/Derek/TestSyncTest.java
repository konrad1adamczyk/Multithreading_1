package Derek;

/**
 * Created by KAdamczyk on 2016-01-08.
 */
class TestSync implements Runnable {

    private int balance;

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            increment();
            System.out.println("balance is " + balance);
        }
    }

    private void increment() {
        int i = balance;
        balance = i + 1;
    }
}
public class TestSyncTest {
        public static void main (String[] args) {
            TestSync job = new TestSync();
//            Thread a = a.start();
//            Thread b = b.start();
        }
    }

