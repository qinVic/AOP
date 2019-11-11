package com.vic.aop.concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author qinquan
 * @date 2019/9/26
 */
public class ReentrantArrayList {

    private ArrayList<Object> array = new ArrayList<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public void add(Object args) {
        writeLock.lock();
        try {
            array.add(args);
        } finally {
            writeLock.unlock();
        }
    }

    public Object get(int index) {
        readLock.lock();
        try {
            return array.get(index);
        } finally {
            readLock.unlock();
        }
    }

}
