package test.concurrency.semaphore;

/**
 * Created by Administrator on 2020/4/27 0027.
 */
public class Main {
    public static void main(String[] args) {
        Thread thread[] = new Thread[10];
        PrintQueue printQueue = new PrintQueue();
        for(int i = 0; i < 10; i++){
            thread[i] = new Thread(new Job(printQueue), "thread"+i);
            thread[i].start();
        }
    }
}
