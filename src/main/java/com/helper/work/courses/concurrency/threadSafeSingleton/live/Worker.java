package com.helper.work.courses.concurrency.threadSafeSingleton.live;

import java.util.Timer;
import java.util.TimerTask;

public class Worker extends Thread {

    private Object lock = new Object();
    private volatile boolean quittingTime = false;

    @Override
    public void run() {
        while (!quittingTime) {
            working();
            System.out.println("Still working...");
        }
        System.out.println("Coffee is good!");
    }

    private void working() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void quit() throws InterruptedException {
        synchronized (lock) {
            quittingTime = true;
            join();
        }
    }

    synchronized void keepWork() {
        synchronized (lock) {
            quittingTime = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Worker worker = new Worker();
        worker.start();

        Timer t = new Timer(true); //daemon thread
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                worker.keepWork();
            }
        };
        t.schedule(timerTask, 500);

        Thread.sleep(400);
        worker.quit();
    }
}
