package com.vic.aop.concurrent.test;

import java.util.Collection;

/**
 * @author qinquan
 * @date 2019/10/30
 */
public interface Lock {

    void lock() throws InterruptedException;

    void unLock();

    Collection<Thread> getThreadPoint();
}
