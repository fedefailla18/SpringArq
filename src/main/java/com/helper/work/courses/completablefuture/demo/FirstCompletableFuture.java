package com.helper.work.courses.completablefuture.demo;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class FirstCompletableFuture {

    public static void main(String[] args) {
        demo3();
    }

    public static long demo3() {
        Instant now = Instant.now();

        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return Thread.currentThread().getName();
        };

        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier);

        final String join = completableFuture.join(); // Blocking code
        System.out.println("Result: " + join);

//        System.out.println(completableFuture.complete("Too long!"));
        completableFuture.obtrudeValue("Too long!");

        final String join2 = completableFuture.join(); // Blocking code
        System.out.println("Result: " + join2);

        return Instant.now().getEpochSecond() -now.getEpochSecond();
    }

    private static void demo2() {
        Supplier supplier = () -> Thread.currentThread().getName();

        final CompletableFuture completableFuture = CompletableFuture.supplyAsync(supplier);

        final Object join = completableFuture.join(); // Blocking code

        System.out.println(join);
    }

    private static void demo1() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        AtomicInteger count = new AtomicInteger();

        final Runnable executing_task = () -> System.out.println("Executing task " + count.getAndIncrement() +" in " + Thread.currentThread().getName());

        CompletableFuture.runAsync(executing_task, service);
        CompletableFuture.runAsync(executing_task);
//        Thread.sleep(100);
        System.out.println(count.getAndIncrement() + " Tasks accomplished in " + Thread.currentThread().getName());
        service.shutdown();
    }
}
