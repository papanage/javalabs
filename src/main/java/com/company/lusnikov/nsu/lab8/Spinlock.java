package com.company.lusnikov.nsu.lab8;

import java.util.concurrent.atomic.AtomicReference;

public class Spinlock {
    private AtomicReference<Thread> cas;

    Spinlock(AtomicReference<Thread> cas){
        this.cas = cas;
    }
    public void lock() {
        Thread current = Thread.currentThread();
        while (!cas.compareAndSet(null, current)) {
            System.out.println("I am spinning");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }
}
