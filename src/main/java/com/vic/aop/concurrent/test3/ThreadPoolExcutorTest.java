package com.vic.aop.concurrent.test3;

import java.util.concurrent.*;

/**
 * @author qinquan
 * @date 2019/11/9
 */
public class ThreadPoolExcutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = createThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;
        while (true) {
            if (activeCount != threadPoolExecutor.getActiveCount() || queueSize != threadPoolExecutor.getQueue().size()) {
                System.out.println(threadPoolExecutor.getCorePoolSize());
                System.out.println(threadPoolExecutor.getActiveCount());
                System.out.println(threadPoolExecutor.getQueue().size());
                System.out.println(threadPoolExecutor.getMaximumPoolSize());
                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
                System.out.println("----------------------------------------------");
            }
        }


    }

    public static ThreadPoolExecutor createThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), (ThreadFactory) Thread::new);
        System.out.println("the thread pool is working...");
        threadPoolExecutor.execute(() -> sleep(5));
        threadPoolExecutor.execute(() -> sleep(2));
        threadPoolExecutor.execute(() -> sleep(2));
        threadPoolExecutor.execute(() -> sleep(2));
        return threadPoolExecutor;
    }

    private static void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
