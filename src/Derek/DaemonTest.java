package Derek;

/**
 * Created by Konrad on 2016-01-07.
 */
public class DaemonTest {
    public static void main(String[] args){
        new WorkerThread().start();
        try{
            Thread.sleep(7500);
        } catch (InterruptedException e){}
        System.out.println("Main Thread ending");
    }
}

class WorkerThread extends Thread {

//    Try to set daemon flag to true or false
//    and notice the chang of behavior.

    public WorkerThread(){
        setDaemon(true);
    }
    public void run(){
        int count=0;
        while (true){
            System.out.println("Hello from Worker " + count++);
            try {
                sleep(600);
            } catch (InterruptedException e){}
        }
    }
}
