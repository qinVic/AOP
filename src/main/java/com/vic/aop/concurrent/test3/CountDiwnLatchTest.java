package com.vic.aop.concurrent.test3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qinquan
 * @date 2019/11/6
 */
public class CountDiwnLatchTest {

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(3);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                System.out.println("11111111");
                COUNT_DOWN_LATCH.countDown();
            });
        }
        COUNT_DOWN_LATCH.await();
        System.out.println("over");
    }

}
