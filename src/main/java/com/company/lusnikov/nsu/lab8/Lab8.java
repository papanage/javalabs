package com.company.lusnikov.nsu.lab8;

import java.util.concurrent.atomic.AtomicReference;

public class Lab8 {
    public static void main(String[] args) {
        AtomicReference<Thread> cas = new AtomicReference<>();
        Thread thread1 = new Thread(new ThreadWithSpinlock(cas));
        Thread thread2 = new Thread(new ThreadWithSpinlock(cas));
        thread1.start();
        thread2.start();
    }


}
