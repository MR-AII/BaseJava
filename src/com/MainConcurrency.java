package com;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    //    private static final Object LOCK = new Object();
//    private static final Lock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {

            Future<Integer> future = executorService.submit(() ->
//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
//            thread.start();
//            threads.add(thread);
        }

/*
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
*/
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(mainConcurrency.counter);
        System.out.println(mainConcurrency.atomicCounter.get());

//        final String lock1 = "lock1";
//        final String lock2 = "lock2";
//        deadLock(lock1, lock2);
//        deadLock(lock2, lock1);
        System.out.println(minValue(new int[]{9, 7, 8, 9, 10, 4, 5, 7}));

        List<Integer> list = Arrays.asList(5, 7, 9, 43, 31, 20, 10);
        System.out.println(oddOrEven(list));
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//        WRITE_LOCK.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            WRITE_LOCK.unlock();
//        }
//                wait();
//                readFile
//                ...
//        }
    }

    private static int minValue(int[] values) {
        return Arrays.stream(Arrays.stream(values).distinct().toArray()).min().getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers
                .stream()
                .filter(integers.stream().mapToInt(Integer::intValue)
                        .sum() % 2 != 0 ? n -> n % 2 == 0 : n -> n % 2 != 0)
                .collect(Collectors.toList());
    }
}
