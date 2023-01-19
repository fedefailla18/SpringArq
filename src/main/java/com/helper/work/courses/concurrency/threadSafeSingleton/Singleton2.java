package com.helper.work.courses.concurrency.threadSafeSingleton;

public class Singleton2 {

    private static Singleton2 singleton1;

    private Singleton2() {
    }

    public static synchronized Singleton2 getSingleton1() {
        return singleton1 == null ? singleton1 = new Singleton2(): singleton1;
    }
}
