package com.helper.work.courses.concurrency.threadSafeSingleton;

public class Main {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getSingleton1();
        Singleton1 singleton2 = Singleton1.getSingleton1();

        System.out.println(singleton1);
        System.out.println(singleton2);
    }
}
