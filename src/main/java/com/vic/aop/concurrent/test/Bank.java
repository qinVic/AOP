package com.vic.aop.concurrent.test;

import java.util.Arrays;

/**
 * @author qinquan
 * @date 2019/10/29
 */
public class Bank {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow();

        /*Thread thread1 = new Thread(ticketWindow, "一号窗口");
        thread1.start();
        Thread thread2 = new Thread(ticketWindow, "二号窗口");
        thread2.start();
        Thread thread3 = new Thread(ticketWindow, "三号窗口");
        thread3.start();*/

        /*Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("#################");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        System.out.println(threadGroup.activeGroupCount());
        Thread[] threads = new Thread[threadGroup.activeGroupCount()];
        int enumerate = threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);*/


    }

}
