package com.vic.aop.concurrent.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author qinquan
 * @date 2019/10/30
 */
public class MyLock implements Lock {

    private Thread currentThread;

    private boolean isLock = false;

    private Collection<Thread> points = new ArrayList<>();


    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLock) {
            points.add(Thread.currentThread());
            this.wait();
        }
        points.remove(Thread.currentThread());
        isLock = true;
        System.out.println(Thread.currentThread().getName() + "is running...");
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unLock() {
        if (currentThread == Thread.currentThread()) {
            this.isLock = false;
            System.out.println(Thread.currentThread().getName() + "release the lock");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getThreadPoint() {
        return Collections.unmodifiableCollection(points);
    }
}
