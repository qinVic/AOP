package com.vic.aop.concurrent.test3;

import java.util.concurrent.Exchanger;

/**
 * @author qinquan
 * @date 2019/11/7
 */
public class ExchangerTest {

    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object o = new Object();
            System.out.println("A ------>" + o);
            try {
                Object result = exchanger.exchange(o);
                System.out.println("A ---RESULT---->" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread() {
            @Override
            public void run() {
                Object o = new Object();
                System.out.println("b ------>" + o);
                try {
                    Object result = exchanger.exchange(o);
                    System.out.println("b ---RESULT---->" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

}
