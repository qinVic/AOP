package com.vic.aop.concurrent.test2;

/**
 * @author qinquan
 * @date 2019/10/31
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    void update() {
        System.out.println("BinaryObserver......" + Integer.toBinaryString(subject.getState()));
    }




}
