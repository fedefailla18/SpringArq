package com.helper.work.courses.concurrency.third;

import java.util.function.IntFunction;

public class FalseSharing {

    public static int NUM_MAX_OF_THREADS = 4;
    public final static long ITERATIONS = 50_000_000L;

    public static volatile VolatileLongPadded[] paddeds;
    public static volatile VolatileLongUnPadded[] unpaddeds;

    public static final class VolatileLongPadded {
        public long q1, q2, q3, q4, q5, q6;
        public volatile long value = 0L;
        public long q11, q12, q13, q14, q15, q16;
    }
    private static class VolatileLongUnPadded {
        public volatile long value = 0L;
    }

    static {
        paddeds = new VolatileLongPadded[NUM_MAX_OF_THREADS];
        for (int i = 0; i < paddeds.length; i++) {
            paddeds[i] = new VolatileLongPadded();
        }

        unpaddeds = new VolatileLongUnPadded[NUM_MAX_OF_THREADS];
        for (int i = 0; i < unpaddeds.length; i++) {
            unpaddeds[i] = new VolatileLongUnPadded();
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Hello world 1");
            return;
        } catch (Exception e) {
            System.out.println("Hello world 2");
        }
        finally {
            System.out.println("Hello world 3");
        }
        System.out.println("Hello world 4");
    }

    private static void runBenchMark() throws InterruptedException{
        long begin, end;

        for (int i = 1; i < NUM_MAX_OF_THREADS; i++) {
            Thread[] threads = createThreads(i, n -> new Thread(createPaddedRunnable(n)));

            begin = System.currentTimeMillis();
            startAndJoinThreads(threads);
            end = System.currentTimeMillis();

            printMessage(begin, end, i, "Padded # of Threads %d - T = %dms\n");


            threads = createThreads(i, n -> new Thread(createUnPaddedRunnable(n)));
            begin = System.currentTimeMillis();
            startAndJoinThreads(threads);
            end = System.currentTimeMillis();

            printMessage(begin, end, i, "UnPadded # of Threads %d - T = %dms\n\n--------------------------\n");

        }
    }

    private static void printMessage(long begin, long end, int i, String s) {
        System.out.printf(s, i, end - begin);
    }

    private static Thread[] createThreads(int n, IntFunction<Thread> o) {
        Thread[] threads = new Thread[n];
        for (int j = 0; j < n; j++) {
            threads[j] = o.apply(j);
        }
        return threads;
    }

    private static void startAndJoinThreads(Thread[] threads) throws InterruptedException {
        for (Thread t :
                threads) {
            t.start();
        }
        for (Thread t :
                threads) {
            t.join();
        }
    }

    private static Runnable createPaddedRunnable(final int j) {
        return () -> {
            long i = ITERATIONS + 1;
            while (0 != --i) {
                paddeds[j].value = j;
            }
        };
    }

    private static Runnable createUnPaddedRunnable(final int j) {
        return () -> {
            long i = ITERATIONS + 1;
            while (0 != --i) {
                unpaddeds[j].value = j;
            }
        };
    }
}
