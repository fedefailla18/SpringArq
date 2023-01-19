package com.helper.work.courses.concurrency.threadSafeSingleton;

public class Singleton3 {

    private static volatile Singleton3 singleton1;
    private static Object key;

    private Singleton3() {
    }

    public static Singleton3 getSingleton1() {
        if (singleton1 != null) {
            return singleton1;
        }
        synchronized(key) {
            return singleton1 == null ? singleton1 = new Singleton3(): singleton1;
        }
    }
}
