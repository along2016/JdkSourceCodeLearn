package test;

import java.util.concurrent.*;

public class StringAppendThread {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                }
                countDownLatch.countDown();
            });
        }
        // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        // 在此处调用await()方法可以避免线程操作未完成执行下面的操作，提前打印结果
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("size:" + stringBuffer.length());
    }

    private static void update() {
        stringBuffer.append("1");
    }
}
