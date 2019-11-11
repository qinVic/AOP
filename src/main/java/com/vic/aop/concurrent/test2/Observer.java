package com.vic.aop.concurrent.test2;

/**
 * @author qinquan
 * @date 2019/10/31
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject =subject;
        subject.attch(this);
    }

    abstract void update();

}
