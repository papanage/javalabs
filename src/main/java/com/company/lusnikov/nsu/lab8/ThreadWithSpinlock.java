package com.company.lusnikov.nsu.lab8;

import java.util.concurrent.atomic.AtomicReference;

public class ThreadWithSpinlock implements Runnable {
    private Spinlock slock;

    public ThreadWithSpinlock(AtomicReference<Thread> cas) {
        this.slock = new Spinlock(cas);
    }

    @Override
    public void run() {
        slock.lock();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        slock.unlock();
    }
}
