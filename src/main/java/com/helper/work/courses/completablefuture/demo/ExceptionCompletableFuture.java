package com.helper.work.courses.completablefuture.demo;

import com.helper.work.courses.completablefuture.demo.model.UserConcurrency;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.helper.work.courses.completablefuture.demo.AsyncExample.sleep;

/**
 * Dealing with Exception in the CompletionStage Pipeline
 */
public class ExceptionCompletableFuture {

    public static void main(String[] args) {
        final Supplier<List<Long>> supplyIDs = () -> {
            sleep(300);
            throw new IllegalStateException("No data");
        };

        Function<List<Long>, List<UserConcurrency>> fetchEmails = ids -> {
            sleep(300);
            return ids.stream().map(UserConcurrency::new).collect(Collectors.toList());
        };

        Consumer<List<UserConcurrency>> printUsers = (users) -> users.forEach(System.out::println);

        final CompletableFuture<List<Long>> supply =
                CompletableFuture.supplyAsync(supplyIDs);
//        final CompletableFuture<List<Long>> exception =
//                supply.exceptionally((e) -> List.of());

//        final CompletableFuture<List<Long>> exception = supply.whenComplete((ids, e) -> {
//            if (e != null) {
//                System.out.println(e.getMessage());
//            }
//        });

        final CompletableFuture<List<Long>> exception =
                supply.handle((ids, e) -> {
                    if (e != null) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        return List.of();
                    }
                    return ids;
                });

        final CompletableFuture<List<UserConcurrency>> apply =
                exception.thenApply(fetchEmails);
        final CompletableFuture<Void> display = apply.thenAccept(printUsers);

        // here we can see how the exception is thrown
        //supply.join(); // should not be called, never

        sleep(1_000);

        System.out.println("Supply  : done= " + supply.isDone() +
                " exception = " + supply.isCompletedExceptionally());
        System.out.println("Fetch   : done= " + apply.isDone() +
                " exception = " + apply.isCompletedExceptionally());
        System.out.println("Display : done= " + display.isDone() +
                " exception = " + display.isCompletedExceptionally());


    }
}
