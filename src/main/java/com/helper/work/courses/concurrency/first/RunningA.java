package com.helper.work.courses.concurrency.first;

import com.helper.work.courses.concurrency.first.model.A;

public class RunningA {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        Runnable r1 = () -> a.a();
        Runnable r2 = () -> a.b();

        Thread thread1 = new Thread(r1);
        thread1.start();

        Thread thread2 = new Thread(r2);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
