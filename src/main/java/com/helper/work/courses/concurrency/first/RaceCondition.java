package com.helper.work.courses.concurrency.first;

import com.helper.work.courses.concurrency.first.model.LongWrapper;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        LongWrapper longWrapper = new LongWrapper(0L);

        final int i1 = 1_000;
        Runnable r = () -> {
            for (int i = 0; i < i1; i++) {
                longWrapper.inc();
            }
        };

        Thread[] threads = new Thread[i1];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println("Result: " + longWrapper.getaLong());

    }
}
