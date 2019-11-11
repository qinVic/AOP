package com.vic.aop.concurrent.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author qinquan
 * @date 2019/10/30
 */
public class MyLockTest {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("T1", "T2", "T3", "T4");
        StringBuilder stringBuilder = new StringBuilder();
        strings.forEach(s -> {
            stringBuilder.append(s)
                    .append("*")
                    .append(1)
                    .append("\n");
        });
            System.out.println(stringBuilder.toString());




        /*final MyLock myLock = new MyLock();

        Stream.of("T1", "T2", "T3", "T4")
                .forEach(e -> {
                    new Thread(() -> {

                            try {

                                myLock.lock();
                                //Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            } finally {
                                myLock.unLock();
                            }
                        }
                    , e).start();
                });*/
    }

}
