package com.helper.work.courses.completablefuture.demo;

import com.helper.work.courses.completablefuture.demo.model.EmailConcurrency;
import com.helper.work.courses.completablefuture.demo.model.UserConcurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AsyncExample {

    public static void main(String[] args) {
        printThread(null);
        final ExecutorService service = Executors.newSingleThreadExecutor();
        final ExecutorService service2 = Executors.newSingleThreadExecutor();
        Supplier<List<Long>> supplyIds = () -> {
            printThread("Supplying Ids in: ");
            sleep(500);
            return Arrays.asList(1L, 2L, 3L);
        };

        Function<List<Long>, CompletableFuture<List<UserConcurrency>>> fetchUsers = ids -> {
            printThread("fetching users in: ");
            sleep(230);
            final Supplier<List<UserConcurrency>> userSupplier =
                    () -> {
                        printThread("Suplying users with ");
                        return ids.stream().map(UserConcurrency::new).collect(Collectors.toList());
                    };
            return CompletableFuture.supplyAsync(userSupplier, service2);
        };

        Function<List<Long>, CompletableFuture<List<EmailConcurrency>>> fetchEmails =
                ids -> {
                    printThread("fetching emails in: ");
                    sleep(400);

                    final Supplier<List<EmailConcurrency>> suplyingEmails = () -> {
                        printThread("Suplying users with ");
                        return ids.stream().map(EmailConcurrency::new).collect(Collectors.toList());
                    };
                    return CompletableFuture.supplyAsync(suplyingEmails, service2);
                };

        Consumer<List<UserConcurrency>> displayUsers = users -> {
            printThread(null);
            users.forEach(System.out::println);
        };

        //
        CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplyIds, service);

        final CompletableFuture<List<UserConcurrency>> futureUsers = completableFuture.thenCompose(fetchUsers);
        final CompletableFuture<List<EmailConcurrency>> futureEmails = completableFuture.thenCompose(fetchEmails);

        futureUsers.thenAcceptBoth(futureEmails, (users, emails) -> {
            System.out.println(users.size() + " - " + emails.size());
        });

        completableFuture.thenComposeAsync(fetchUsers, service2)
                .thenAcceptAsync(displayUsers, service);

        sleep(1_000);
        service.shutdown();
    }

    private static void printThread(String msg) {
        if (msg == null) msg = "Running in: ";
        System.out.println(msg + Thread.currentThread().getName());
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
