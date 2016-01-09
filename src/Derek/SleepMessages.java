package Derek;

/**
 * Created by KAdamczyk on 2016-01-08.
 */
public class SleepMessages {
    public static void main(String[] args) throws InterruptedException {
        String importatntInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for ( int i =0; i<importatntInfo.length; i++){
            Thread.sleep(4000);
            System.out.println(importatntInfo[i]);
        }
    }
}
