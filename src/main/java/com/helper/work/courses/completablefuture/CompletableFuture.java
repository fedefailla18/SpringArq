package com.helper.work.courses.completablefuture;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 *  Introduction of CompletableFuture with https://app.pluralsight.com/course-player?clipId=ceed72dc-17ea-4c82-8054-922e63faa286
 *  course. CompletableFuture is an extension to Java's Future API which was introduced in Java.
 *  A Future is used as a reference to the result of an asynchronous computation. It provides an isDone()
 *  method to check whether the computation is done or not, and a get() method to retrieve the result of the
 *  computation when it is done
 *
 *  https://www.baeldung.com/java-completablefuture
 */
public class CompletableFuture {

    public static void main(String[] args) {
    }

    private static void completableFutureSupplyAsyncWithExecutorService() {
        Supplier<String> supplier = () -> readPage("http://google.com");
        ExecutorService service = Executors.newSingleThreadExecutor();
        java.util.concurrent.CompletableFuture.supplyAsync(supplier, service);
    }

    private static void completableFutureAssync() {
        Supplier<String> supplier = () -> readPage("http://google.com");
        java.util.concurrent.CompletableFuture.supplyAsync(supplier);

        Runnable callable = () -> readPage("http://google.com");
        java.util.concurrent.CompletableFuture.runAsync(callable);
    }

    private static String readPage(String s) {
        return s;
    }

    private static void oldRunnableWithFutureObject() {
        Runnable task = () -> System.out.println("Executing old Runnable");

        ExecutorService service = Executors.newSingleThreadExecutor();

        final Future<?> fu = service.submit(task);
        try {
            fu.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
