package com.vic.aop.concurrent.test2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinquan
 * @date 2019/10/31
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public void setState(int state) {
        
        if (this.state == state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    public int getState() {
        return this.state;
    }

    public void attch(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObserver() {
        observers.forEach(Observer::update);
    }

}
