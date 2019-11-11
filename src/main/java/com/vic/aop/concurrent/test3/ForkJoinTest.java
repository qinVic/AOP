package com.vic.aop.concurrent.test3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author qinquan
 * @date 2019/11/8
 */
public class ForkJoinTest {

    private static final int MAX_VALUE = 10;

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new CalculatedRecursiveTask(0, 200));
        try {
            Integer result = forkJoinTask.get();
            System.out.println("result=====>" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class CalculatedRecursiveTask extends RecursiveTask<Integer> {

        private final int start;

        private final int end;

        CalculatedRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if ((end - start) <= MAX_VALUE) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                int middle = (end + start) / 2;
                CalculatedRecursiveTask leftTask = new CalculatedRecursiveTask(start, middle);
                CalculatedRecursiveTask rightTask = new CalculatedRecursiveTask(middle + 1, end);

                leftTask.fork();
                rightTask.fork();
                System.out.println(leftTask.join());
                System.out.println(rightTask.join());
                return leftTask.join() + rightTask.join();

            }
        }
    }

}
