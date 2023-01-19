package com.helper.work.courses.concurrency.first.model;

public class A {

    private Object key1 = new Object();
    private Object key2 = new Object();

    public void a() {
        synchronized (key1) {
            System.out.println("A.a running in: " + Thread.currentThread().getName());
            b();
        }
    }

    public void b() {
        synchronized (key2) {
            System.out.println("A.b running in: " + Thread.currentThread().getName());
            c();
        }
    }

    public void c() {
        synchronized (key1) {
            System.out.println("A.c running in: " + Thread.currentThread().getName());
        }
    }

}
