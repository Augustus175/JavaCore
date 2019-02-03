package com.zzb.javacore.chap14.currenttest;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);
        Thread thread1 = new Thread(new myTread(latch));
        Thread thread2 = new Thread(new myTread(latch));
        thread1.start();
        thread2.start();
        System.out.println("waiting for the two thread !");
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("now the main thread running");
    }
}

class myTread implements Runnable {
    private final CountDownLatch countDownLatch;

    public myTread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            System.out.println("Thread : " + Thread.currentThread().getName() + " is running.");
            Thread.sleep(10000L);
            System.out.println("Thread : " + Thread.currentThread().getName() + " done.");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
