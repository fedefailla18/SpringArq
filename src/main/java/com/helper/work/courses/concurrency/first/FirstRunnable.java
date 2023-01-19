package com.helper.work.courses.concurrency.first;

public class FirstRunnable {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("I am running in " + Thread.currentThread().getName());

        Thread thread = new Thread(runnable);
        thread.setName("My Thread");

//        thread.start();
        thread.run();
    }
}
