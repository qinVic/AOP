package com.vic.aop.concurrent.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinquan
 * @date 2019/10/29
 */
public class TicketWindow {

    private final Object lock = new Object();

    private volatile List<String> list = new ArrayList<>();

    public void producer() {
        synchronized (lock) {
            if (list.size() > 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("添加list数据");
                list.add("java");
                lock.notify();
            }
        }
    }

    public void consumer() {

            if (list.size() > 0) {
                System.out.println("减少list的数据");
                list.remove(0);
                lock.notify();
            } else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow();
        new Thread(){
            @Override
            public void run() {
                while (true)
                ticketWindow.producer();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true)
                ticketWindow.consumer();
            }
        }.start();
    }

}
