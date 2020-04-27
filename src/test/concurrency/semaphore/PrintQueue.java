package test.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2020/4/27 0027.
 */
public class PrintQueue {

    // 这个final很重要，要不然不能保证一次只有一个线程在执行打印操作
    private final Semaphore semaphore;

    public PrintQueue(){
        semaphore = new Semaphore(1);
    }

    public void printJob(Object document){
        try {
            semaphore.acquire();
            long duration = (long) (Math.random() * 1000);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
